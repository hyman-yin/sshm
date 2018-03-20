package sshm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UnhandleException {
	public static void main(String[] args) {
		try {
			MyThread myThread = new MyThread();
			Thread thread = new Thread(myThread);
			thread.setUncaughtExceptionHandler(new MyExceptionHandler());
			
			ExecutorService service = Executors.newFixedThreadPool(3);
			service.execute(myThread);
			service.execute(myThread);
			service.execute(myThread);
		} catch (Exception e) {
			System.out.println("捕获到异常！");
			e.printStackTrace();
		}
	}

	static class MyThread implements Runnable {
		@Override
		public void run() {
			System.out.println(1 / 0);
		}
	}
}
