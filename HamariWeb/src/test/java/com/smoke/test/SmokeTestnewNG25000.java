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


public class SmokeTestnewNG25000 {
	
	WebDriver driver;
//	HamariWebTestNG obj;
	@BeforeTest
	public void setup() throws Throwable {
		driver = BaseCode.setupBrowser("chrome");
	}
	

	@Test
	public void checkResult25000() throws Throwable {
		//obj.testBond(driver, 1500);
		//int[] bondValue = { 1500 };
		int bondValue = 25000;
		ArrayList<String> myList = new ArrayList<>();	// we entered more then one value, we need array		
		myList = ExcelData.getExcelData(String.valueOf(bondValue));
		//driver = BaseCode.setupBrowser();
		BaseCode.testData(driver, myList, 25000);
	}
	
	@AfterTest
	public void tearoff() {
		driver.quit();
	}
}
