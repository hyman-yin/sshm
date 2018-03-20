package sshm;

import java.util.Comparator;

public class MyComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer o1, Integer o2) {
		if(o1==null && o2!=null){
			return -1;
		}
		if(o1!=null && o2==null){
			return 1;
		} 
		if(o1==null && o2==null){
			return 0;
		}
		
		return o1.intValue()>o2.intValue()? -1:(o1.intValue()==o2.intValue()?0:1);
	}
}


