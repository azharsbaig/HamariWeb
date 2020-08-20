package com.basecode;	

	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
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
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.PageFactory;

	import com.config.BaseConfig;
	import com.page.model.BondListPage;

	import com.util.ExplicitWait;
	import com.util.Highlighter;
import com.util.TakeAppScreenShot;
	
	public class BasePBCodeDND {
		
		public static void main(String[] args) throws Throwable {	
			int[] bondValue = { 1500 }; // ,200,500,1000,1500,  , 15000, 25000

			for (int i = 0; i < bondValue.length; i++) {
				ArrayList<String> myList = new ArrayList<>();
				//ArrayList<String> myList = new ArrayList<>();			
				myList = BasePBCodeDND.getExcelData(String.valueOf(bondValue[i]));
				System.out.println(myList);
				TakeAppScreenShot.emptyScreenShotFolder();
				BasePBCodeDND.testData(myList, bondValue[i]);
				}
		}
		public static void testData(ArrayList<String> bondNumber, int bondValue) throws Throwable {
			System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

			WebDriver driver = new ChromeDriver();				
			
			driver.get(BaseConfig.getconfig("URL"));
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			
			BondListPage pfs = new BondListPage(driver);
			System.out.println(driver.getCurrentUrl());
			System.out.println(driver.getTitle());
			
			//driver.findElement(By.xpath("(//*[text()='BUSINESS'])[2]")).click();
			pfs.getBusiness().click();		
			
			Thread.sleep(3000);
			
			Actions acFinance = new Actions(driver);
			
			//acFinance.moveToElement(driver.findElement(By.xpath("//*[@class='menu_active']"))).build().perform();		
			acFinance.moveToElement(pfs.getFinanceLink()).build().perform();
			
			Thread.sleep(2000);
			Actions bondTemp = new Actions(driver);
			Actions prizeBond = new Actions(driver);
			//prizeBond.moveToElement(driver.findElement(By.xpath("(//*[text()='Prize Bond Results'])[2]"))).build().perform();
			prizeBond.moveToElement(pfs.getPrizeBondList()).build().perform();	
			Thread.sleep(3000);
			
			//pfs.getPopup().click();
			//driver.findElement(By.xpath("//*[@id='pe_close_btn']")).click();
			if (bondValue == 100) {
				//driver.findElement(By.xpath("(//*[text()='Prize Bond RS. 100/-'])[2]")).click();
				pfs.getHPB().click();
			} 
			
			else if (bondValue == 200) {
				//driver.findElement(By.xpath("(//*[text()='Prize Bond RS. 200/-'])[2]")).click();
				pfs.getTHPB().click();
			} 
			
			else if (bondValue == 750) {
				//driver.findElement(By.xpath("(//*[text()='Prize Bond RS. 750/-'])[2]")).click();
				pfs.getSFPBp().click();
			} 
			
			else if (bondValue == 1500) {
				//bondTemp.moveToElement(driver.findElement(By.xpath("(//*[text()='Prize Bond RS. 1,500/-'])[2]"))).build().perform();
				//driver.findElement(By.xpath("(//*[text()='Prize Bond RS. 1,500/-'])[2]")).click();
				Thread.sleep(3000);
				
				pfs.getFHPB().click();
			} 
			
			else if (bondValue == 7500) {
				//bondTemp.moveToElement(driver.findElement(By.xpath("(//*[text()='Prize Bond RS. 7,500/-'])[2]"))).build().perform();
				//driver.findElement(By.xpath("(//*[text()='Prize Bond RS. 7,500/-'])[2]")).click();
				Thread.sleep(3000);
				
				pfs.getSFHPB().click();
			} 
			
			else if (bondValue == 15000) {
				//bondTemp.moveToElement(driver.findElement(By.xpath("(//*[text()='Prize Bond RS. 15,000/-'])[2]"))).build().perform();
				Thread.sleep(3000);
				//driver.findElement(By.xpath("(//*[text()='Prize Bond RS. 15,000/-'])[2]")).click();
				pfs.getFTPB().click();
			} 
			
			else if (bondValue == 25000) {
				//bondTemp.moveToElement(driver.findElement(By.xpath("(//*[text()='Prize Bond RS. 25,000/-'])[2]"))).build().perform();
				Thread.sleep(3000);
				//driver.findElement(By.xpath("(//*[text()='Prize Bond RS. 25,000/-'])[2]")).click();
				pfs.getTFTPBp().click();
			} 
			
			else if (bondValue == 40000) {
				//bondTemp.moveToElement(driver.findElement(By.xpath("(//*[text()='Prize Bond RS. 40,000/-'])[2]"))).build().perform();
				Thread.sleep(3000);
				//driver.findElement(By.xpath("(//*[text()='Prize Bond RS. 40,000/-'])[2]")).click();
				pfs.getFTDPB();
			}
			
			//driver.findElements(By.xpath("(//table)[2]/tbody/tr"));
			List<WebElement> rows = pfs.getTabrow();
			String[] arrData = new String[5];
			System.out.println("Total Rows: " + rows.size());
			DateFormat format;
			Date drawDate = null, currDate;
			currDate = new Date();
			int priceValue = 0;
			String temp = null;
			WebElement viewDetail;
			int lineClick = 0;
			
			format = new SimpleDateFormat("d MMMM, yyyy", Locale.ENGLISH);
			// for (int i = 1; i < rows.size(); i++) { // runs 32 times (rows)
			for (int i = 1; i < 5; i++) {
				for (int j = 1; j <= 6; j++) { // Column
					
					//need to create pfs
					//String x = driver.findElement(By.xpath("(//table)[2]/tbody/tr[" + (i) + "]/td[" + (j) + "]")).getText();
					String x = pfs.madhuri(i, j).getText();
					//String x = pfs.getTabrow()driver.findElement(By.xpath("(//table)[2]/tbody/tr[" + (i) + "]/td[" + (j) + "]")).getText();
					// System.out.println("X==>: "+x);
					//System.out.println("(//table)[2]/tbody/tr[" + (i) + "]/td[" + (j) + "]");
					if (j == 3) {
						drawDate = format.parse(x);
						System.out.println("Date: " + drawDate);
					}
					else if (j == 6) {
						System.out.println("View Detail: " + x);
						
						if (drawDate.compareTo(currDate) < 0) {
							lineClick++;
							System.out.println("Draw Date: " + drawDate + " Line Click: " + lineClick);
							//System.out.println("(//*[text()='View Result'])[" + lineClick + "]");
							
							//Can not be done need to creat pfs
							//viewDetail = driver.findElement(By.xpath("(//*[text()='View Result'])[" + lineClick + "]"));
							viewDetail = pfs.Sushmeta(lineClick);
							Actions ac = new Actions(driver);
							ac.moveToElement(viewDetail).build().perform();
							Highlighter.getcolor(driver, viewDetail, "red");
							//ScreenShot.getScreenShot(driver, "View Detail.png");
							//ScreenShot.getScreenShot(driver, "View Detail.png");
							ac.click().build().perform();
							for (int k = 0; k < bondNumber.size(); k++) {
								//driver.findElement(By.xpath("//input[@id='txtId']")).clear();
								pfs.getRowChange().clear();

								//driver.findElement(By.xpath("//input[@id='txtId']")).sendKeys(bondNumber.get(k).toString());
								pfs.getRowChange().sendKeys(bondNumber.get(k).toString());
								Thread.sleep(2000);
								//driver.findElement(By.xpath("//input[@id='txtId']")).sendKeys(Keys.ENTER);
								pfs.getRowChange().sendKeys(Keys.ENTER);
								Thread.sleep(2000);

								//temp = driver.findElement(By.xpath("//*[@id='results']/table/tbody/tr/td[1]")).getText();
								temp = pfs.getTemp().getText();
								if (temp.equalsIgnoreCase(bondNumber.get(k).toString())) {
									//priceValue = Integer.parseInt(driver.findElement(By.xpath("//*[@id='results']/table/tbody/tr/td[2]")).getText());
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
		public static ArrayList getExcelData(String sheetName) throws Throwable {
			FileInputStream fis = new FileInputStream("./DataDriven/BondNumbers.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			ArrayList<String> list = new ArrayList<>();

			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
				if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
					XSSFSheet sheet = workbook.getSheetAt(i);
					Iterator<Row> rows = sheet.iterator();
					Row firstRow = rows.next();
					Iterator<Cell> columns = firstRow.cellIterator();
					int k = 0;
					int columnNum = 0;
					while (columns.hasNext()) {
						Cell value = columns.next();
						if (value.getStringCellValue().equalsIgnoreCase("BondNum"))
							;
						{
							columnNum = k;
						}
						k++;
					}
					while (rows.hasNext()) {
						Row rowData = rows.next();					
						Iterator<Cell> cv = rowData.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellType() == CellType.STRING) {
								list.add(c.getStringCellValue());
							} else if (c.getCellType() == CellType.NUMERIC) {
								list.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}
				}
			}
			return list;
		}
	}


