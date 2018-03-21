package hyman.study.ssh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hyman.study.ssh.model.TUser;
import hyman.study.ssh.service.usermanage.UserService;

@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUserList.do")
	@ResponseBody
	public List<TUser> getUserList(){
		long t1 = System.currentTimeMillis();
		
		List<TUser> list = userService.getUserList();
		if(list!=null){
			for(TUser user : list){
				System.out.println(user);
			}
		}
		long t2 = System.currentTimeMillis();
		System.out.println("costed: "+(t2-t1) + " ms!");
		
		return list;
	}

	@RequestMapping("/getUserById.do")
	@ResponseBody
	public List<TUser> getUserById(String id){
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		
		List<TUser> list = userService.getUserById(map);
		if(list!=null){
			for(TUser user : list){
				System.out.println(user);
			}
		}
		return list;
	}

	@RequestMapping("/getNames.do")
	@ResponseBody
	public List<Map<String,Object>> getNames(){
		List<Map<String,Object>> list = userService.getNamesAndPasswords();
		printListMap(list);
		return list;
	}

	@RequestMapping("/getNameById.do")
	@ResponseBody
	public List<Map<String,Object>> getNameById(String id){
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		
		List<Map<String,Object>> list = userService.getNameAndPasswordById(map);
		printListMap(list);
		return list;
	}
	
	
	public void printListMap(List<Map<String,Object>> list){
		if(list!=null){
			for(Map<String,Object> map : list){
				Set<Entry<String, Object>> set = map.entrySet();
				for(Entry<String,Object> entry : set){
					System.out.print(entry.getKey()+": "+entry.getValue()+", ");
				}
				
				System.out.println();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
