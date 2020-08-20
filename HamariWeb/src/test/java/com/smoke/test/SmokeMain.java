package com.smoke.test;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

import com.basecode.BaseCode;
import com.config.ExcelData;
import com.util.TakeAppScreenShot;

public class SmokeMain {
	public static void main(String[] args) throws Throwable {	
		int[] bondValue = { 1500,15000,25000 }; // ,200,500,1000,1500,  , 15000, 25000
		for (int i = 0; i < bondValue.length; i++) {
			ArrayList<String> myList = new ArrayList<>();			
			myList = ExcelData.getExcelData(String.valueOf(bondValue[i]));
			System.out.println(myList);
			TakeAppScreenShot.emptyScreenShotFolder();
			WebDriver driver = BaseCode.setupBrowser("chrome");
			BaseCode.testData(driver, myList, bondValue[i]);
			
			}
//		SmokeMain.test(bondValue);
	}
	public static void test(int[] x) throws Throwable {
		for (int i = 0; i < x.length; i++) {
			ArrayList<String> myList = new ArrayList<>();			
			myList = ExcelData.getExcelData(String.valueOf(x[i]));
			System.out.println(myList);
			TakeAppScreenShot.emptyScreenShotFolder();
			WebDriver driver = BaseCode.setupBrowser("chrome");
			BaseCode.testData(driver, myList, x[i]);
			
			}
	}

}
