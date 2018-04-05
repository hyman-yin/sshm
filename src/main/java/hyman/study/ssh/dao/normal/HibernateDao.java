package hyman.study.ssh.dao.normal;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.SqlProvider;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.util.Assert;

/**
 * dao的基类
 * @author hyman
 *
 * @param <T>
 * @param <PK>
 */
public class HibernateDao<T, PK extends Serializable> extends BaseHibernateDao<T, PK> {
	public HibernateDao() {
		super();
	}

	public HibernateDao(final SessionFactory sessionFactory, final Class<T> entityClass) {
		super(sessionFactory, entityClass);
	}


	@SuppressWarnings("rawtypes")
	public List queryForListByMap(String sql, Map<String, Object> objects) {
		Session session = this.getSession();

		SQLQuery sqlQuery = (SQLQuery) session.createSQLQuery(sql).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);

		if (objects != null && !objects.isEmpty()) {
			sqlQuery.setProperties(objects);
		}

		return sqlQuery.list();
	}

	public int[] batchUpdate(final String[] sql) throws SQLException {

		Assert.notEmpty(sql, "SQL array must not be empty");
		if (logger.isDebugEnabled()) {
			logger.debug("Executing SQL batch update of " + sql.length + " statements");
		}
		/**
		 * 批量更新参数处理内部类.
		 * 
		 */
		@SuppressWarnings("rawtypes")
		class BatchUpdateStatementCallback implements StatementCallback, SqlProvider {
			private String currSql;

			@Override
			public Object doInStatement(Statement stmt) throws SQLException, DataAccessException {
				int[] rowsAffected = new int[sql.length];
				if (JdbcUtils.supportsBatchUpdates(stmt.getConnection())) {
					for (String element : sql) {
						this.currSql = element;
						stmt.addBatch(element);
					}
					rowsAffected = stmt.executeBatch();
				} else {
					for (int i = 0; i < sql.length; i++) {
						this.currSql = sql[i];
						if (!stmt.execute(sql[i])) {
							rowsAffected[i] = stmt.getUpdateCount();
						} else {
							throw new InvalidDataAccessApiUsageException("Invalid batch SQL statement: " + sql[i]);
						}
					}
				}
				return rowsAffected;
			}

			@Override
			public String getSql() {
				return currSql;
			}
		}
		try {
			return (int[]) execute(new BatchUpdateStatementCallback());
		} catch (DataAccessException e) {
			throw new SQLException(e.getMessage() + "批处理SQL失败");
		} catch (SQLException e) {
			throw new SQLException(e.getMessage() + "批处理SQL失败");
		}
	}

	
	
	@SuppressWarnings("rawtypes")
	private Object execute(StatementCallback action) throws DataAccessException, SQLException {
		Assert.notNull(action, "Callback object must not be null");
		Connection con=SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			Object result = action.doInStatement(stmt);
			return result;
		} catch (SQLException ex) {
			stmt.close();
			stmt = null;
			con.close();
			con = null;
			throw new SQLException(ex.getMessage() + "批处理SQL失败");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}

		}

	}

	
	public Query createQuery(String hql, List<Object> paramList) {
		return createQuery(hql, paramList.toArray());
	}

	public SQLQuery createSqlQuery(String sql, List<Object> paramList) {
		SQLQuery query = getSession().createSQLQuery(sql);
		Iterator<Object> iterator = paramList.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			query.setParameter(i++, iterator.next());
		}
		return query;
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDataList(String sql, Map<String, Object> paramMap) {
		Query query = this.getSession().createSQLQuery(sql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setReadOnly(true);
		query.setProperties(paramMap);
		return query.list();
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map<String, String>> convertList(ResultSet rs) throws SQLException {
		List list = new ArrayList();
		ResultSetMetaData md = rs.getMetaData();
		int columnCount = md.getColumnCount();
		while (rs.next()) {
			Map rowData = new HashMap();
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i).toUpperCase(), rs.getObject(i));
			}
			list.add(rowData);
		}
		return list;
	}
}
