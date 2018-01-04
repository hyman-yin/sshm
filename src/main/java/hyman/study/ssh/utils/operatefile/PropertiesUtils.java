package hyman.study.ssh.utils.operatefile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesUtils {
	/**
	 * 获取文件的实际路径
	 * @param filePath 文件相对于根目录的路径
	 * @return
	 */
	public static String getRealPath(String filePath){
		ClassLoader classLoader=Thread.currentThread().getContextClassLoader();
		URL url=classLoader.getResource(filePath);
		return url.getPath();
	}
		
	
	/**
	 * 获得 Properties 对象
	 * @param filePath  文件相对于根目录的路径
	 * @return
	 * @throws IOException
	 */
	public static Properties getProperties(String filePath) throws IOException{
		Properties properties=new Properties();
		FileInputStream in=new FileInputStream(getRealPath(filePath));
		properties.load(in);
		return properties;
	}
	
	
	/**
	 * 获取所有的非空key
	 * @param filePath  文件相对于根目录的路径
	 * @return  非空key集合
	 * @throws IOException
	 */
	public static List<String> getAllKeys(String filePath) throws IOException{
		List<String> list=new ArrayList<String>();
		Properties props=getProperties(filePath);
		
		Iterator<Object> iterator=props.keySet().iterator();
		if(iterator!=null){
			while(iterator.hasNext()){
				String key=(String) iterator.next();
				if(StringUtils.isNotBlank(key) && !list.contains(key)){
					list.add(key);
				}
			}
		}
		return list;
	}
	
	
	/**
	 * 获取所有的 key value 集合
	 * @param filePath
	 * @return 
	 * @throws IOException
	 */
	public static Map<String,Object> getAllKeysAndValues(String filePath) throws IOException{
		Properties properties=getProperties(filePath);
		//初始化map时，最好指定初始值大小
		Map<String,Object> map=new HashMap<String, Object>(2);
		
		List<String> keys=getAllKeys(filePath);
		List<String> values=new ArrayList<String>();
		
		if(keys!=null){
			for(int i=0;i<keys.size();i++){
				String value=properties.getProperty(keys.get(i));
				values.add(value);
			}
		}
		
		map.put("keys", keys);
		map.put("values", values);
		return map;
	}
	
	
	/**
	 * 获取properties配置文件指定key对应的value值
	 * @param filePath  文件相对于根目录的路径
	 * @param propertyName  key
	 * @return value
	 * @throws IOException
	 */
	public static String getPropertyValue(String filePath,String propertyName) throws IOException{
		Properties props=getProperties(filePath);
		return props.getProperty(propertyName);
	}
	
	/**
	 * 更新配置文件的key值
	 * @param filePath  文件相对于根目录的路径
	 * @param propertyName  key
	 * @param value  value
	 * @param flag true或false，false代表覆盖，true代表追加 
	 * @throws IOException
	 */
	public static void setPropertyValue(String filePath,String propertyName,String value,boolean flag) throws IOException{
		Properties props=getProperties(filePath);
		props.setProperty(propertyName, value);
		String outPath=getRealPath(filePath);
		//true代表追加，false代表覆盖
		FileOutputStream out=new FileOutputStream(outPath,flag);
		props.store(out, "\nupdate "+propertyName);
		out.close();
		out.flush();
	}
	
	/**
	 * 批量更新配置文件的key值
	 * @param filePath  文件相对于根目录的路径
	 * @param propNames  key数组
	 * @param values  value数组
	 * @param flag  true或false，false代表覆盖，true代表追加
	 * @throws IOException 
	 */
	public static void setProperties(String filePath,String[] propNames,String[] values,boolean flag) throws IOException{
		if(propNames==null || values==null || propNames.length==0 || values.length==0 || propNames.length!=values.length){
			return;
		}
		Properties properties=getProperties(filePath);
		
		for(int i=0;i<values.length;i++){
			if(StringUtils.isBlank(propNames[i])){
				continue;
			}
			properties.setProperty(propNames[i], values[i]);
		}
		String outPath=getRealPath(filePath);
		//true代表追加，false代表覆盖
		FileOutputStream out=new FileOutputStream(outPath,flag);
		properties.store(out,"\nbatch update keys:"+Arrays.toString(propNames));
		out.close();
		out.flush();
	}
	
	
	/**
	 * 移除key
	 * @param filePath  文件相对于根目录的路径
	 * @param propertyName  key
	 * @throws IOException
	 */
	public static void removeProperty(String filePath,String propertyName) throws IOException{
		Properties props=getProperties(filePath);
		props.remove(propertyName);
		String outPath=getRealPath(filePath);
		FileOutputStream out=new FileOutputStream(outPath,false);
		props.store(out, "delete "+propertyName);
		out.close();
		out.flush();
	}
	
	
	/**
	 * 批量移除key
	 * @param filePath  文件相对于根目录的路径
	 * @param keys  key集合
	 * @throws IOException
	 */
	public static void removeProperties(String filePath,String[] keys) throws IOException{
		Properties props=getProperties(filePath);
		
		if(keys!=null){
			for(int i=0;i<keys.length;i++){
				if(StringUtils.isNotBlank(keys[i])){
					props.remove(keys[i]);
				}
			}
		}
		
		String outPath=getRealPath(filePath);
		FileOutputStream out=new FileOutputStream(outPath,false);
		props.store(out, "delete "+Arrays.toString(keys));
		out.close();
		out.flush();
	}
	
}