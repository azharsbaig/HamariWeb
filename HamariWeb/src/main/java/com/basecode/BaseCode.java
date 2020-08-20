package com.basecode;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.config.BaseConfig;
import com.page.model.BondListPage;
import com.util.CaptureScreenShot;
import com.util.Highlighter;
import com.util.TakeAppScreenShot;

public class BaseCode {
	public static WebDriver setupBrowser(String driverName) throws Throwable {
		WebDriver driver=null;
	
		if (driverName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
	
			driver = new ChromeDriver();	
		} else if(driverName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
			driver = new FirefoxDriver();	
		}
		
		return driver;
	}
	
	public static void testData(WebDriver driver, ArrayList<String> bondNumber, int bondValue) throws Throwable {
		
		driver.get(BaseConfig.getconfig("URL"));		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		BondListPage pfs = new BondListPage(driver);
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		Thread.sleep(2000);
		pfs.getBusiness().click();		
		
		Thread.sleep(2000);
		
		Actions acFinance = new Actions(driver);		
		
		acFinance.moveToElement(pfs.getFinanceLink()).build().perform();
		
		Thread.sleep(2000);
		Actions bondTemp = new Actions(driver);
		Actions prizeBond = new Actions(driver);
		
		prizeBond.moveToElement(pfs.getPrizeBondList()).build().perform();	
		Thread.sleep(2000);	
		
		if (bondValue == 100) {			
			pfs.getHPB().click();
		} else if (bondValue == 200) {			
			pfs.getTHPB().click();
		} else if (bondValue == 750) {			
			pfs.getSFPBp().click();
		} else if (bondValue == 1500) {			
			Thread.sleep(3000);			
			pfs.getFHPB().click();
		} else if (bondValue == 7500) {			
			Thread.sleep(3000);			
			pfs.getSFHPB().click();
		} else if (bondValue == 15000) {			
			Thread.sleep(3000);			
			pfs.getFTPB().click();
		} else if (bondValue == 25000) {			
			Thread.sleep(3000);			
			pfs.getTFTPBp().click();
		} else if (bondValue == 40000) {			
			Thread.sleep(3000);			
			pfs.getFTDPB();
		}	
		
		List<WebElement> rows = pfs.getTabrow();
	//	String[] arrData = new String[5];
		System.out.println("Total Rows: " + rows.size());
		DateFormat format;
		Date drawDate = null, currDate;
		currDate = new Date();
		int priceValue = 0;
		String temp = null;
		WebElement viewDetail;
		int lineClick = 0;
		
		format = new SimpleDateFormat("d MMMM, yyyy", Locale.ENGLISH);
		// for (int i = 1; i < rows.size(); i++) { // runs 32 times (rows)// to 
		for (int i = 1; i < 5; i++) { // to
			for (int j = 1; j <= 6; j++) { // Column
				
				String x = pfs.madhuri(i, j).getText();
				
				if (j == 3) {
					drawDate = format.parse(x);
					System.out.println("Date: " + drawDate);
				}
				else if (j == 6) {
					System.out.println("View Detail: " + x);
					
					if (drawDate.compareTo(currDate) < 0) {
						lineClick++;
						System.out.println("Draw Date: " + drawDate + " Line Click: " + lineClick);
						
						viewDetail = pfs.Sushmeta(lineClick);
						Actions ac = new Actions(driver);
						ac.moveToElement(viewDetail).build().perform();
						Highlighter.getcolor(driver, viewDetail, "red");
						//TakeAppScreenShot.captureScreenShot(driver, "View Detail");
						CaptureScreenShot.getScreenShot(driver, "View Detail");
						ac.click().build().perform();
						for (int k = 0; k < bondNumber.size(); k++) {							
							pfs.getRowChange().clear();							
							pfs.getRowChange().sendKeys(bondNumber.get(k).toString());
							Thread.sleep(2000);							
							pfs.getRowChange().sendKeys(Keys.ENTER);
							Thread.sleep(2000);							
							temp = pfs.getTemp().getText();
							if (temp.equalsIgnoreCase(bondNumber.get(k).toString())) {								
								System.out.println("xxxxxxx: "+pfs.getpValue().getText());								
								priceValue = Integer.parseInt(pfs.getpValue().getText());
								System.out.println("Draw Date: " + drawDate + " won the price: "
										+ bondNumber.get(k).toString() + " Amount= " + priceValue);
							} 
							else {
								System.out.println("Draw Date: " + drawDate + "Bond #: " + bondNumber.get(k).toString()
										+ " " + temp);
							}
						}
						Thread.sleep(2000);
						driver.navigate().back();
						Thread.sleep(2000);
					}
				}
			}
		}
	}
}
