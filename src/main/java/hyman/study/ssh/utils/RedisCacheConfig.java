package hyman.study.ssh.utils;

import java.lang.reflect.Method;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
	private volatile JedisConnectionFactory jedisConnectionFactory;
	private volatile RedisTemplate<String,String> redisTemplate;
	private volatile RedisCacheManager redisCacheManager;
	
	public RedisCacheConfig() {
		super();
	}
	
	public RedisCacheConfig(JedisConnectionFactory jedisConnectionFactory,RedisTemplate<String,String> redisTemplate,RedisCacheManager redisCacheManager){
		this.jedisConnectionFactory=jedisConnectionFactory;
		this.redisTemplate=redisTemplate;
		this.redisCacheManager=redisCacheManager;
	}

	public JedisConnectionFactory getJedisConnectionFactory() {
		return jedisConnectionFactory;
	}

	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	public RedisCacheManager getRedisCacheManager() {
		return redisCacheManager;
	}

	@Bean
	public KeyGenerator customkeyGenerator(){
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... objects) {
				StringBuffer sb = new StringBuffer();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for(Object object : objects){
					sb.append(object.toString());
				}
				return sb.toString();
			}
		};
	}
}
