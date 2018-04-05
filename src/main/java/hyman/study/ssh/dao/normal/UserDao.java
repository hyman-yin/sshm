package hyman.study.ssh.dao.normal;

import java.util.List;

import org.springframework.stereotype.Repository;

import hyman.study.ssh.model.TUser;

@Repository("userDao")
public class UserDao extends HibernateDao<TUser, Integer> {
	@SuppressWarnings("unchecked")
	public List<TUser> getUserList(){
		String hql="from TUser";
		return this.getSession().createQuery(hql).list();
	}
}
