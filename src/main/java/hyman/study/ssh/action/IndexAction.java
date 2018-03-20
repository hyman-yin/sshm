package hyman.study.ssh.action;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import hyman.study.ssh.model.TUser;
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
		System.out.println("����action");
		return "success";
	}
	
	public String login(){
		String username = Struts2Utils.getParameter("username");
		String password = Struts2Utils.getParameter("password");
		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			Struts2Utils.setAttribute("msg", "�û������������");
			return "fail";
		}
		long t1 = System.currentTimeMillis();
		List<TUser> list = userService.getUserList();
		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i).getUsername()+" : "+list.get(i).getPassword());
		}
		long t2 = System.currentTimeMillis();
		System.out.println("costed time: "+(t2-t1)+" ms! ");
		
//		int count=userService.isCorrectUser(username, password);
//		if(count==1){
//			Struts2Utils.setAttribute("username", username);
//			return "success";
//		}
		
		return "fail";
	}
}
