package hyman.study.ssh.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hyman.study.ssh.model.TUser;
import hyman.study.ssh.service.usermanage.UserService;

@Controller
@RequestMapping("/user/")
public class UserController4Restful {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/userApi",method=RequestMethod.GET)
	@ResponseBody
	public TUser getUser(String id){
		if(StringUtils.isBlank(id)){
			return null;
		}
		TUser user = userService.getUser(Integer.parseInt(id));
		return user;
	}
	
	
	@RequestMapping(value="/userApi",method=RequestMethod.DELETE)
	@ResponseBody
	public void delUser(String id){
		if(StringUtils.isBlank(id)){
			return;
		}
		
		userService.deleteUser(Integer.valueOf(id));
	}
	
	
	@RequestMapping(value="/userApi",method=RequestMethod.POST)
	@ResponseBody
	public void addUser(TUser user){
		userService.saveUser(user);
	}
	
	@RequestMapping(value="/userApi",method=RequestMethod.PUT)
	@ResponseBody
	public void updateUser(TUser user){
		userService.updateUser(user);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
