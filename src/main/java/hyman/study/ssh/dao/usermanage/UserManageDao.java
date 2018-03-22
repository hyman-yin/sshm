package hyman.study.ssh.dao.usermanage;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("userManageDao")
public class UserManageDao {
	@Resource
	private SessionFactory sessionFactory;
	
	public int isCorrectUser(String username,String password){
		String sql="select count(0) from tuser t where t.password=? and t.username=?";
		BigInteger bigDecimal=(BigInteger) sessionFactory.getCurrentSession().createSQLQuery(sql)
				.setParameter(1, password).setParameter(2, username).uniqueResult();
		if(bigDecimal.intValue()>0){
			return 1;
		}
		
		return 0;
	}

	public void addUser() {
		String sql = "insert into tuser(username,password) values ('hyman','qwer1234')";
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}
}	
