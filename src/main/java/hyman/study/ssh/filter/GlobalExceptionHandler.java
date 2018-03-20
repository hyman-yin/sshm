package hyman.study.ssh.filter;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	Logger logger=Logger.getLogger(GlobalExceptionHandler.class);
	
	//异常处理
	@ExceptionHandler(Exception.class)
	public String catchException(Exception e){
		System.out.println("进入统一异常管理");
		e.printStackTrace();
		
		logger.error(e.getMessage(),e);
		return "error";
	}
}
