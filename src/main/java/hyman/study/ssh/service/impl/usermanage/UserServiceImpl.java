package hyman.study.ssh.service.impl.usermanage;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hyman.study.ssh.dao.dboperate.DataBaseDao;
import hyman.study.ssh.dao.usermanage.UserManageDao;
import hyman.study.ssh.mapper.TUserMapper;
import hyman.study.ssh.model.TUser;
import hyman.study.ssh.service.usermanage.UserService;
import hyman.study.ssh.utils.RedisUtils;

@Service("userService")
@Transactional(rollbackOn=Exception.class)
public class UserServiceImpl implements UserService {
	@Autowired
	private UserManageDao userManageDao;
	
	@Autowired
	private DataBaseDao dataBaseDao;
	
//	@Autowired
//	private UserDao userDao;
	
	@Autowired
	private TUserMapper tUserMapper;
	
	public int isCorrectUser(String username, String password) {
		return userManageDao.isCorrectUser(username, password);
	}

	public void callNoResultProc(String name) {
		dataBaseDao.callProcNoResult(name);
	}

	public void callHasResultProc(String name) {
		try {
			dataBaseDao.callProcHasResult(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void callFunction(String name) {
		dataBaseDao.callFunction(name);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TUser> getUserList() {
		//首先从redis中获取list，如果有，直接从redis中取值。如果没有，从数据库中查，然后放到redis中
		List<TUser> userList = (List<TUser>) RedisUtils.getList("userList");
		if(userList==null){
			userList = tUserMapper.getUserList();
			System.out.println("redis中没有值，数据库list size："+userList.size());
			RedisUtils.setList("userList", userList);
			return userList;
		} else {
			System.out.println("======== 有======,size: "+userList.size());
			return userList;
		}
	}

	@Override
	public List<TUser> getUserById(Map<String, Object> map) {
		return tUserMapper.getUserById(map);
	}

	@Override
	public List<Map<String,Object>> getNamesAndPasswords() {
		return tUserMapper.getNamesAndPasswords();
	}

	@Override
	public List<Map<String,Object>> getNameAndPasswordById(Map<String, Object> map) {
		return tUserMapper.getNameAndPasswordById(map);
	}
}
