package hyman.study.ssh.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.commons.lang3.StringUtils;

/**
 * @Desc csv文件直接导入mysql太慢，因此在java中处理，把2万条记录组装成一条sql然后插入mysql
 * 注意：
 * 	（1）最好事先修改my.ini的配置，添加或者修改max_allowed_packet属性为一个比较大的值，例如：
 * 		max_allowed_packet=16M
 *	（2）事先把表字段的类型设置为varchar类型，这样就不需要判断csv文件的每一列，可以提高程序运行效率
 *
 * @author Administrator
 *
 * 2018年3月26日 下午4:43:20
 */
public class ImportCSV2MySQLUtils {
	private static final String driver_name="com.mysql.cj.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/ili?serverTimezone=UTC";
	private static final String username="root";
	private static final String password="qwer1234";
	private static final String table_name="billcnt_month";//要插入的表名
	private static final int column_count=4;//要插入的表的列数，因为csv可能出现中间或者结尾没有数据的情况，此种情况下直接插入会报错
	private static final String filepath="H:/data/billcnt_month.csv";//要插入的csv文件位置
	
	public static void main(String[] args) throws InterruptedException {
		long t1  = System.currentTimeMillis();
		BufferedReader reader = null;
		try {
			 Class.forName(driver_name) ;   
		     Connection con = DriverManager.getConnection(url , username , password ) ;   
		     Statement stmt = con.createStatement();
		     
			reader = new BufferedReader(new FileReader(filepath));   
            String line = null;    
            reader.readLine();
            int i=0;
            StringBuilder sb = new StringBuilder();
            sb.append("insert into "+table_name+" values ");
            while((line=reader.readLine())!=null){
            	i++;
            	sb.append(getAFullLine(line, column_count)).append(",");
            	if(i%20000==0){
            		sb = sb.replace(sb.length()-1, sb.length(), ";");
            		stmt.executeUpdate(sb.toString());
            		sb=new StringBuilder();
            		sb.append("insert into "+table_name+" values ");
            		System.out.println("第 "+i+" 条记录");
            	}
            }    
			if(StringUtils.isNotBlank(sb.toString())){
				sb = sb.replace(sb.length()-1, sb.length(), ";");
				System.out.println("the final sb: "+sb.toString());
				stmt.executeUpdate(sb.toString());
			}
			System.out.println("总共插入了 "+i+" 条记录");
			
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
	
	/**
	 * 把传入的一行中的空格补齐，否则插入会出错
	 * @param line csv中的一行
	 * @param len 要插入的表的列数
	 * @return
	 */
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
					sb.append(str[i]).append(",");
				}
			}
			int le=0;
			if((le = (len-str.length))>0){//后面几列可能为空
				for(int i=0;i<le;i++){
					sb.append("'',");
				}
			}
		}
		sb = sb.replace(sb.length()-1, sb.length(), ")");
		
		return sb.toString();
	}
}
