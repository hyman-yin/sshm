package hyman.study.ssh.entity;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Email;

import com.sun.istack.internal.NotNull;

@SuppressWarnings("deprecation")
public class RegisterInfo implements Serializable {
	private static final long serialVersionUID = -6890367656430880357L;
	
	@NotNull
	@NotBlank(message="用户名不能为空")
	private String username;
	
	private String password;
	private String password2;
	
	private String tel;
	
	@Email(message="email is wrong")
	private String email;
	
	@Min(value=0,message="age>0")
	@Max(value=200,message="age<200")
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
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	@Override
	public String toString() {
		String str = username+": "+password+":\n "+password2
				+": "+email+": "+tel+": "+age+": ";
		return str;
	}
	
	
	
}
