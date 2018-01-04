package hyman.study.ssh.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import hyman.study.ssh.service.usermanage.UserService;
import hyman.study.ssh.utils.Struts2Utils;

public class IndexAction extends ActionSupport{
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String enterIndex(){
		System.out.println("进入action");
		return "success";
	}
	
	public String login(){
		String username = Struts2Utils.getParameter("username");
		String password = Struts2Utils.getParameter("password");
		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			Struts2Utils.setAttribute("msg", "用户名或密码错误");
			return "fail";
		}
		
		int count=userService.isCorrectUser(username, password);
		if(count==1){
			Struts2Utils.setAttribute("username", username);
			return "success";
		}
		
		return "fail";
	}
}
