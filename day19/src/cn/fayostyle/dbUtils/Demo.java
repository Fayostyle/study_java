package cn.fayostyle.dbUtils;

import org.junit.Test;

public class Demo {

	public Demo() {
		// TODO Auto-generated constructor stub
	}
	public void sum(int... num) {
		int total = 0;
		for(int i = 0; i < num.length; i++) {
			total = total + num[i];
		}
		System.out.println(total);
	}
	
	
	@Test
	public void test() {
		int[] num = {3213,2,232,12323,231};
		sum(num);
		sum(2,3);
	}
}
