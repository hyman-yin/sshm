package hyman.study.ssh.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import hyman.study.ssh.model.TUser;

@Mapper
public interface TUserMapper {
	public List<TUser> getUserList();
	
	public List<TUser> getUserById(Map<String,Object> map); 
	
	public List<Map<String,Object>> getNamesAndPasswords();
	
	public List<Map<String,Object>> getNameAndPasswordById(Map<String,Object> map);
}
