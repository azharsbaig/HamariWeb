package com.smoke.test;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.basecode.BaseCode;
import com.config.ExcelData;
import com.util.CaptureScreenShot;
import com.util.TakeAppScreenShot;


public class SmokeTestnewNG {
	
	WebDriver driver;
//	HamariWebTestNG obj;
	
	
	@BeforeTest
	@Parameters("Browser")
	public void setup(String browserName) throws Throwable {
		driver = BaseCode.setupBrowser(browserName);
	}
	
	@Test
	@Parameters("BondValue")
	public void checkResult1500(String value) throws Throwable {
		//obj.testBond(driver, 1500);
		//int[] bondValue = { 1500 };
		int bondValue = Integer.parseInt(value);
		ArrayList<String> myList = new ArrayList<>();	// we entered more then one value, we need array		
		myList = ExcelData.getExcelData(String.valueOf(bondValue));
	//	driver = BaseCode.setupBrowser();
		BaseCode.testData(driver, myList, bondValue);
	}
	
	@AfterTest
	public void tearoff() {
		driver.quit();
	}
}
