package hyman.study.ssh.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("cleanTask")
public class CleanTask {
	@Scheduled(cron="0/50 5 * * * ? ")
	public void print(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		System.out.println("hello world!"+format.format(new Date()));
	}
}
