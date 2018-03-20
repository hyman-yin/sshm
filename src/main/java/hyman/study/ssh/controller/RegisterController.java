package hyman.study.ssh.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import hyman.study.ssh.entity.RegisterInfo;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@RequestMapping("/enterRegister.do")
	public String enterRegister(){
		System.out.println(1/0);
		return "register";
	}
	
	@RequestMapping("/register.do")
	public String register(@Valid RegisterInfo info,BindingResult errorResult,Map<String,Object> map){
		map.put("info", info);
		if(errorResult.hasErrors()){
			List<FieldError> errors = errorResult.getFieldErrors();
			for(FieldError error : errors){
				map.put(error.getField()+"_error", error.getDefaultMessage());
				System.out.println(error.getField()+" "+error.getDefaultMessage());
			}
			
			return "register";
		} else {
			return "register_success";
		}
	}
}
