package sshm;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import hyman.study.ssh.utils.operatefile.PropertiesUtils;

public class PropertiesUtilsTest {

	@Test
	public void testGetAllKeys() {
		try {
			String filePath = "properties/test.properties";
			List<String> keys=PropertiesUtils.getAllKeys(filePath);
			if(keys!=null){
				for(int i=0;i<keys.size();i++){
					System.out.println(keys.get(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllKeysAndValues(){
		try {
			String filePath = "properties/test.properties";
			Map<String, Object> map=PropertiesUtils.getAllKeysAndValues(filePath);
			if(map!=null){
				List<String> keys=(List<String>) map.get("keys");
				List<String> values=(List<String>) map.get("values");
				if(keys!=null){
					for(int i=0;i<keys.size();i++){
						System.out.println(keys.get(i)+" : "+values.get(i));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testRemoveProperty(){
		try {
			String filePath = "properties/test.properties";
			PropertiesUtils.removeProperty(filePath, "username2");
			PropertiesUtils.removeProperty(filePath, "password2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveProperties(){
		try {
			String filePath = "properties/test.properties";
			String[] keys={"username2","password2"};
			PropertiesUtils.removeProperties(filePath, keys);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateBatch(){
		String filePath = "properties/test.properties";
		String keys[]={"username","username2","password","password2"};
		String values[]={"admin","sys","3333","4444"};
		try {
			PropertiesUtils.setProperties(filePath, keys, values, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
