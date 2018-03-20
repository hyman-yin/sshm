package hyman.study.ssh.testresource;

import java.io.Serializable;

public class TestParam implements Serializable {
	private static final long serialVersionUID = 3324451237000480910L;
	
	private String username;
	private Long password;
	private Integer age;
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getPassword() {
		return password;
	}
	public void setPassword(Long password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return username+" --- "+password+" --- "+age;
	}
	
	
}
