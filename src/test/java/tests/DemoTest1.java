package tests;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

public class DemoTest1 {

	
	@Test(enabled = false)
	public void print(){
		System.out.println("hello Tassi");
		String browser = System.getProperty("browser");
//		System.out.println("browsersssssssssss"+browser);
		
		if(browser.equals("chrome")){
			System.out.println("Its chrome baby");
		}
	}
	@Test(enabled = false)
	public void print2(){
		System.out.println("hello Tassi");
	}
	@Test(enabled = false)
	public void print3(){
		System.out.println("hello Tassi--1");
	}
	@Test(enabled = false)
	public void print4(){
		System.out.println("hello Tassirrrrrrrrrr");
	}
}
