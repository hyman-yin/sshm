package hyman.study.ssh.service.impl.usermanage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hyman.study.ssh.dao.dboperate.DataBaseDao;
import hyman.study.ssh.dao.usermanage.UserManageDao;
import hyman.study.ssh.mapper.TUserMapper;
import hyman.study.ssh.model.TUser;
import hyman.study.ssh.service.usermanage.UserService;

/**
 * @Desc 缓存机制说明：所有的查询结果都放进了缓存，也就是把MySQL查询的结果放到了redis中去，
 * 然后第二次发起该条查询时就可以从redis中读取查询的结果，从而不用与MySQL打交道，从而达到性能优化的效果
 * redis的查询速度至于MySQL的查询速度相当于 内存读写速度/硬盘读写速度
 * @Cacheable("a") 注解的意义就是把该方法的查询结果放到redis中去，下一次再发起查询时就去redis中去取，存在redis中的数据的key就是a
 * @CacheEvict(value={"a","b"}",allEntries=true)的意思就是执行该方法后要清除redis中的key名称为a和b的数据，否则会造成数据不一致
 *
 *  缓存主要在service层进行，查询的结果会缓存，把对象序列号存到redis中去，
 *  key就是注解中的参数，例如@Cacheable("findUsers"): 存在redis中的key就是findUsers。
 *  缓存了这个结果之后再次请求这个方法就不会去数据库中查，而是从redis缓存中读取数据，这样就减少了跟数据库之间的交互。
 *  然后修改、删除、增加操作就需要清除缓存，保持数据的一致性。
 *
 * @author Administrator
 *
 * 2018年3月21日 下午9:18:04
 */
@Service("userService")
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UserServiceImpl implements UserService {
	@Autowired
	private UserManageDao userManageDao;
	
	public final static String USER_LIST_KEY = "allUserList";
	
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
	
	@Cacheable(key="#root.target.USER_LIST_KEY",cacheNames="hyman")
	@Override
	public List<TUser> getUserList() {
		List<TUser> userList = tUserMapper.getUserList();
		return userList;
	}

	@CacheEvict(value= {"#root.target.USER_LIST_KEY"},allEntries=true)//清空缓存，allEntries变量表示所有对象的缓存都清除
	public int addUser(){
		return userManageDao.addUser();
	}
	
	
	
	@Override
	public List<TUser> getUserByMap(Map<String, Object> map) {
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
