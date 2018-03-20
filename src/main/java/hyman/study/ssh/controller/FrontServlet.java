package hyman.study.ssh.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hyman.study.ssh.testresource.TestParam;

@Controller
public class FrontServlet  {
	@RequestMapping({"/login/doLogin.do","login.do","myLogin.do"})
	public String login(@RequestParam(name="username2",defaultValue="admin",required=true) String username,
			@RequestParam(name="password2",defaultValue="12345678",required=true) String password,
			@RequestParam(name="age2",defaultValue="25",required=false) Integer age,Model model,Map<String,Object> map){
		map.put("username", username);
		map.put("password", password);
		map.put("age", age);

		if(username.equals("hyman"))
			return "success";
		return "fail";
	}
	
	@RequestMapping(value="register.do",method=RequestMethod.POST)
	public String register(TestParam param){
		return "haha";
	}
	
	
	@RequestMapping("test")
	public void test(){
		System.out.println(10/0);
	}

	@RequestMapping("test2")
	public void test2(){
//		TestParam param=null;
//		System.out.println(param.getUsername());
	}
	
	
	
	
	
	
	
}
