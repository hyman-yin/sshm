package hyman.study.ssh.service.impl.dboperate;

import org.springframework.stereotype.Service;

import hyman.study.ssh.service.dboperate.DataBaseService;

@Service("dataBaseService")
public class DataBaseServiceImpl implements DataBaseService{
//	@Autowired
//	private DataBaseDao dataBaseDao;
	
	public void callProcNoResult(String name) {
		//dataBaseDao.callProcNoResult(name);
		System.out.println("hello world");
	}

}
