package sshm;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		Test test = new Test();
		test.waitForSignal();
	}
	
	void waitForSignal() throws InterruptedException{
		Object object = new Object();
		synchronized (Thread.currentThread()) {
			object.wait();
			object.notify();
		}
	}
	
	
}




