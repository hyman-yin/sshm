package hyman.study.ssh.dao.usermanage;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import hyman.study.ssh.model.TUser;

@Repository
public class UserDao {
//	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<TUser> getUserList(){
		String hql="from TUser";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
}
