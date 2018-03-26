package sshm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.commons.lang3.StringUtils;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		long t1  = System.currentTimeMillis();
		BufferedReader reader = null;
		String table_name="yh_yd_m170731_etl_com";
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver") ;   
			 String url = "jdbc:mysql://localhost:3306/sshm?serverTimezone=UTC" ;    
		     String username = "root" ;   
		     String password = "qwer1234" ;   
		     
		     Connection con = DriverManager.getConnection(url , username , password ) ;   
		     Statement stmt = con.createStatement();
		     
			//每次读取10000条，组装成sql插入
			String filePath="E:/data/pengli/yh_yd_m170731_etl_com.csv";

			reader = new BufferedReader(new FileReader(filePath));   
            String line = null;    
            reader.readLine();
            int i=0;
            StringBuilder sb = new StringBuilder();
            sb.append("insert into "+table_name+" values ");
            while((line=reader.readLine())!=null){
            	i++;
//            	sb.append("("+line+"),");
            	sb.append(getAFullLine(line, 9)).append(",");
            	if(i%20000==0){
            		sb = sb.replace(sb.length()-1, sb.length(), ";");
            		stmt.executeUpdate(sb.toString());
            		sb=new StringBuilder();
            		sb.append("insert into "+table_name+" values ");
            		System.out.println(i);
            	}
            }    
			if(StringUtils.isNotBlank(sb.toString())){
				sb = sb.replace(sb.length()-1, sb.length(), ";");
				System.out.println("the final sb: "+sb.toString());
				stmt.executeUpdate(sb.toString());
			}
			System.out.println(i);
			
			long t2  = System.currentTimeMillis();
			System.out.println("costed : "+ (t2-t1)+ " ms");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static String getAFullLine(String line,int len){
		StringBuilder sb  = new StringBuilder("(");
		
		if(StringUtils.isBlank(line)){
			for(int i=0;i<len;i++){
				sb.append("''").append(",");
			}
		} else {
			String[] str=line.split(",");
			for(int i=0;i<str.length;i++){
				if(StringUtils.isBlank(str[i])){
					sb.append("'',");
				} else {
					sb.append("'").append(str[i]).append("',");
				}
			}
			int le=0;
			if((le = (len-str.length))>0){
				for(int i=0;i<le;i++){
					sb.append("'',");
				}
			}
		}
		sb = sb.replace(sb.length()-1, sb.length(), ")");
		
		return sb.toString();
	}
	
}




