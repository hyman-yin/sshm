package sshm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler {
	private Object target;
	
	public MyHandler(Object target) {
		super();
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("在调用实际方法前执行一定的逻辑");
		method.invoke(target, args);
		System.out.println("在调用实际方法之后执行一定的逻辑");
		return null;
	}
	
}
