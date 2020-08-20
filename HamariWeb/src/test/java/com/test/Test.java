package com.test;

import org.openqa.selenium.WebDriver;

import com.cucumber.code.BaseCode;

public class Test {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		
		WebDriver driver = BaseCode.setupBrowser("chrome");
		BaseCode.clickBusinessLink(driver);
		BaseCode.clickFinanceLink(driver);
		BaseCode.clickBondResult(driver, 1500);
		BaseCode.testBondResult(driver, 1500);
		
	}

}
