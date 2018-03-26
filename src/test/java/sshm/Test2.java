package sshm;

public class Test2 {
	public static void main(String[] args) {
		String string="1,2,,,5,6,7,";
		int len=8;
		
		System.out.println(Test.getAFullLine(string, len));
	}
}
