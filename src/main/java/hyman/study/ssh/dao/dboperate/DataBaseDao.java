package hyman.study.ssh.dao.dboperate;

import java.sql.SQLException;

import javax.persistence.ParameterMode;

import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.stereotype.Repository;

@Repository("dataBaseDao")
public class DataBaseDao {
//	@Autowired
	private SessionFactory sessionFactory;
	
	//调用无返回值存储过程
	public void callProcNoResult(String name){
		String sql="{call test_proc_no_result(?)}";
		sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(1, name).executeUpdate();
	}
	
	
	//调用有返回值存储过程
	public Object callProcHasResult(String name) throws SQLException{
		ProcedureCall call=sessionFactory.getCurrentSession()
				.createStoredProcedureCall("test_proc_has_result");
		call.registerParameter(1, String.class, ParameterMode.IN).bindValue(name);
		call.registerParameter(2, String.class, ParameterMode.OUT);
		
		return call.getOutputs().getOutputParameterValue(2);
	}
	
	//调用函数
	public Object callFunction(String name){
		String sql="select test_func(?) from dual";
		return sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setParameter(1, name).uniqueResult();
	}
	
}
