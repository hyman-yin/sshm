package hyman.study.ssh.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class Struts2Utils {
	public static HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public static HttpSession getSession(){
		return getRequest().getSession();
	}
	
	public static String getParameter(String name){
		return getRequest().getParameter(name);
	}
	
	public static HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}
	
	public static void setAttribute(String name,Object value){
		getRequest().setAttribute(name, value);
	}
	
	
}
