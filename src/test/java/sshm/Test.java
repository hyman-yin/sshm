package sshm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		ArrayList<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("2");
		list.add("1");
		
		Set<String> set = new HashSet<>(list);
		list.clear();
		list.addAll(set);
		System.out.println(list);
	}
	
	
}




