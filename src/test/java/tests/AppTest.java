package tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import globalUtilities.Xls_Reader;

/**
 * Unit test for simple App.
 * 
 */

public class AppTest{
	
	@Test(enabled = true, groups={"testttt"})
	public void print(){
		System.out.println("hello yasir");
	/*	Xls_Reader reader = new Xls_Reader("/home/yasir/workspace/JavaAutomationFramework/src/main/resources/dataSheet.xlsx");
		
		int count = reader.getRowCount("EmployeeRecords");
		
		for (int rowNum = 2; rowNum < count; rowNum++) {
			
			String empNum = reader.getCellData("EmployeeRecords", "EmployeeNumber", rowNum);
			System.out.println(empNum);
			String empfName = reader.getCellData("EmployeeRecords", "Fname", rowNum);
			System.out.println(empfName);
			String empLname = reader.getCellData("EmployeeRecords", "Lname", rowNum);
			System.out.println(empLname);
			String empLocal = reader.getCellData("EmployeeRecords", "Locality", rowNum);
			System.out.println(empLocal);
			String empPin = reader.getCellData("EmployeeRecords", "Pin", rowNum);
			System.out.println(empPin);
			
		}*/
		
		
	}
	@Test(enabled = true, groups = {"testtt1t", "testttt"})
	public void print2(){
		System.out.println("hello tassi---retry");
		assertEquals(1, 2);
	}
	@Test(enabled = true, groups = {"testttt", "test"})
	public void print3(){
		System.out.println("hello yasir--1");
	}
	@Test(enabled = true, groups = {"testttt", "test"})
	public void print4(){
		System.out.println("hello yasirrrrrrrrrr");
	}
	
}
