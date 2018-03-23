package sshm;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		try {
			System.out.println(1/0);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		} finally {
			System.out.println("finally......");
		}
		
		System.out.println("after finally");
	}
	
}




