package hyman.study.ssh.service.usermanage;

import java.util.List;
import java.util.Map;

import hyman.study.ssh.model.TUser;

public interface UserService {
	public int isCorrectUser(String username,String password);
	public void callNoResultProc(String name);
	public void callHasResultProc(String name);
	public void callFunction(String name);
	public List<TUser> getUserList();
	public List<TUser> getUserById(Map<String, Object> map);
	public List<Map<String,Object>> getNamesAndPasswords();
	public List<Map<String,Object>> getNameAndPasswordById(Map<String, Object> map);
}
