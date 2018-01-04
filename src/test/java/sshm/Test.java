package sshm;

import hyman.study.ssh.utils.operatefile.PropertiesUtils;

public class Test {
	public static void main(String[] args) {
		try {
			String filePath = "properties/test.properties";
			System.out.println("username: " + PropertiesUtils.getPropertyValue(filePath, "username"));
			System.out.println("password: " + PropertiesUtils.getPropertyValue(filePath, "password"));

			
			String keys[]={"username","usename2","password","password2"};
			String values[]={"root11","root22","r1111","r2222"};
			PropertiesUtils.setProperties(filePath, keys, values, true);

			System.out.println("username: " + PropertiesUtils.getPropertyValue(filePath, "username"));
			System.out.println("password: " + PropertiesUtils.getPropertyValue(filePath, "password"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
