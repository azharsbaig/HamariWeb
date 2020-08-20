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


public class SmokeTestnewNG2 {
	
	WebDriver driver;
//	HamariWebTestNG obj;
	@BeforeTest
	public void setup() throws Throwable {
		driver = BaseCode.setupBrowser("chrome");
	}
	
	@Test //Whole Excell
	public void checkResultAll() throws Throwable {
		int[] bondValue = { 1500,15000,25000 };
		for (int i=0; i<bondValue.length;i++) {
			ArrayList<String> myList = new ArrayList<>();			
			myList = ExcelData.getExcelData(String.valueOf(bondValue[i]));
			System.out.println("XXXX:" +bondValue[i]+","+myList);
			
			BaseCode.testData(driver, myList, bondValue[i]);
			TakeAppScreenShot.emptyScreenShotFolder();
		}		
	}
	
	@AfterTest
	public void tearoff() {
		driver.quit();
	}
}
