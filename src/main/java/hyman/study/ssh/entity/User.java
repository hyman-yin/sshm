package hyman.study.ssh.entity;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -2985072484236648790L;
	
	private String username;
	private String password;
	private Integer age;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
