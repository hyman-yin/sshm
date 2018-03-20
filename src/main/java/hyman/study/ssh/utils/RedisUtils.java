package hyman.study.ssh.utils;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public final class RedisUtils {
	private static String ADDR="192.168.106.129";
	private static int PORT=6379;
	
	private static int MAX_ACTIVE=3000;
	private static int MAX_WAIT=10000;
	private static int TIMEOUT=100000;
	
	private static boolean TEST_ON_BORROW=true;
	private static JedisPool pool = null;
	
	
	static{
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(MAX_ACTIVE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			pool = new JedisPool(config,ADDR,PORT,TIMEOUT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public synchronized static Jedis getJedis(){
		try{
			if(pool!=null){
				Jedis resource = pool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static void returnResource(final Jedis jedis){
		if(jedis!=null){
			jedis.close();
		}
	}
	
	
	
	public static void setObject(String key ,Object obj){  
	    try {  
	        obj = obj == null ? new Object():obj;  
	        getJedis().set(key.getBytes(), SerializeUtil.serialize(obj));  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	}  
	     
	   /** 
	    * 获取对象 
	    * @param key 
	    * @return Object 
	    */  
	public static Object getObject(String key){  
	    if(getJedis() == null || !getJedis().exists(key)){  
	        return null;  
	    }  
	    byte[] data = getJedis().get(key.getBytes());  
	    return (Object)SerializeUtil.unserialize(data);  
	}  
	  
	/** 
	    * 设置List集合 
	    * @param key 
	    * @param list 
	    */  
	   public static void setList(String key ,List<?> list){  
	    try {  
	          
	        if(list!=null && list.size()>0){  
	            getJedis().set(key.getBytes(), SerializeUtil.serializeList(list));  
	        }else{//如果list为空,则设置一个空  
	            getJedis().set(key.getBytes(), "".getBytes());  
	        }  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	}  
	  
	   /** 
	    * 获取List集合 
	    * @param key 
	    * @return 
	    */  
	public static List<?> getList(String key){  
		Jedis jedis = getJedis();
	    if(jedis == null || !jedis.exists(key)){  
	        return null;  
	    }  
	    byte[] data = getJedis().get(key.getBytes());  
	    returnResource(jedis);
	    return SerializeUtil.unserializeList(data);  
	}  
	
	
	
	
}
