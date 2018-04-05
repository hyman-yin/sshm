package sshm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test {
	private static final String TABLE_NAME="user_";
	private static final int model_num = 3;
	private static final String username = "root";
	private static final String password = "qwer1234";
	private static final String url = "jdbc:mysql://localhost:3306/sshm?serverTimezone=UTC";
	
	public static void main(String[] args) {
		String string="abcccdefccc";
		String fromString="cc";
		String toString="c";
		
		System.out.println(string.replace(fromString, toString));
		System.out.println(replaceStr(string, fromString, toString));
	}
	
	
	public static String replaceStr(String strSource, String strFrom, String strTo) {
		// 如果要替换的子串为空，则直接返回源串
		if (strFrom == null || strFrom.equals("")) {
			return strSource;
		}
		String strDest = "";
		// 要替换的子串长度
		int intFromLen = strFrom.length();
		int intPos;
		// 循环替换字符串
		while ((intPos = strSource.indexOf(strFrom)) != -1) {
			// 获取匹配字符串的左边子串
			strDest = strDest + strSource.substring(0, intPos);
			// 加上替换后的子串
			strDest = strDest + strTo;
			// 修改源串为匹配子串后的子串
			strSource = strSource.substring(intPos + intFromLen);
		}
		// 加上没有匹配的子串
		strDest = strDest + strSource;
		// 返回
		return strDest;
	}
	
}

