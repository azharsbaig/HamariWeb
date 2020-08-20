package com.smoke.test;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.basecode.BaseCode;
import com.config.ExcelData;
import com.util.CaptureScreenShot;
import com.util.TakeAppScreenShot;


public class SmokeTestnewNG1500 {
	
	WebDriver driver;
//	HamariWebTestNG obj;
	@BeforeTest
	public void setup() throws Throwable {
		driver = BaseCode.setupBrowser("chrome");
	}
	
	@Test  //(enabled=false)
	public void checkResult1500() throws Throwable {
		//obj.testBond(driver, 1500);
		//int[] bondValue = { 1500 };
		int bondValue = 1500;
		ArrayList<String> myList = new ArrayList<>();	// we entered more then one value, we need array		
		myList = ExcelData.getExcelData(String.valueOf(bondValue));
	//	driver = BaseCode.setupBrowser();
		BaseCode.testData(driver, myList, 1500);
	}
	
	@AfterTest
	public void tearoff() {
		driver.quit();
	}
}
