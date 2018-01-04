package hyman.study.ssh.dao.usermanage;

import java.math.BigDecimal;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userManageDao")
public class UserManageDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public int isCorrectUser(String username,String password){
		String sql="select count(0) from sys_user t where t.password=? and (t.login_name=? or t.email=?)";
		BigDecimal bigDecimal=(BigDecimal) sessionFactory.getCurrentSession().createNativeQuery(sql)
				.setParameter(1, password).setParameter(2, username).setParameter(3, username).uniqueResult();
		if(bigDecimal.intValue()>0){
			return 1;
		}
		
		return 0;
	}
}	
