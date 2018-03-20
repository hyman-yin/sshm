package hyman.study.ssh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hyman.study.ssh.entity.User;

@Controller
@RequestMapping("/json/")
public class JsonController {
	@RequestMapping("jsonTest.do")
	@ResponseBody
	public Long jsonTest(){
		System.out.println("json test controller....");
		return 121L;
	}
	
	@RequestMapping("beanToJson.do")
	@ResponseBody
	public User beanToJson(){
		User user=new User();
		user.setUsername("龙成");
		user.setPassword("ad");
		user.setAge(25);
		
		return user;
	}
	
	@RequestMapping("mapToJson.do")
	@ResponseBody
	public Map<String,Object> mapToJson(){
		Map<String,Object> map=new HashMap<>();
		map.put("first", "1222");
		map.put("ps", "2342342342");
		return map;
	}
	
	
	
	@RequestMapping("listmapToJson.do")
	@ResponseBody
	public List<Map<String,Object>> listmapToJson(){
		List<Map<String,Object>> list=new ArrayList<>();
		
		Map<String,Object> map=new HashMap<>();
		map.put("first", "1222");
		map.put("ps", "2342342342");
		
		Map<String,Object> map2=new HashMap<>();
		map2.put("first", "2222");
		map2.put("ps", "2322222");
		
		list.add(map);
		list.add(map2);
		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
