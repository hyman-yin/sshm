package hyman.study.ssh.service.impl.usermanage;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hyman.study.ssh.dao.usermanage.UserManageDao;
import hyman.study.ssh.service.usermanage.UserService;

@Service("userService")
@Transactional(rollbackOn=Exception.class)
public class UserServiceImpl implements UserService {
	@Autowired
	private UserManageDao userManageDao;
	
//	public UserManageDao getUserManageDao() {
//		return userManageDao;
//	}
//
//	public void setUserManageDao(UserManageDao userManageDao) {
//		this.userManageDao = userManageDao;
//	}

	public int isCorrectUser(String username, String password) {
		return userManageDao.isCorrectUser(username, password);
	}

}
