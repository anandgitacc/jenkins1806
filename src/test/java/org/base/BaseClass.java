package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public static void browserLaunch(String url) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
	}
	public static void loadUrl(String url) {
		driver.get(url);
	}
	
	public static void maximize() {
		driver.manage().window().maximize();
	}
	
	public String getDataFromTable(int rownum, int cellnum) throws IOException {
		String data = null;
		File file = new File("C:\\Users\\Dell\\eclipse-workspace\\MavenFrst\\Excel\\Framework1.xlsx");
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet("Data");
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		int cellType = cell.getCellType();
		// 1 - String
		if (cellType == 1) {
			data = cell.getStringCellValue();
		}
		if (cellType == 0) {   //		// 0 --> numeric or date format
			if (DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
				String data1 = dateFormat.format(dateCellValue);
				// data = new SimpleDateFormat("dd-MMM-yy").format(cell.getDateCellValue());
			}
			else { // numeric value
				double numericCellValue = cell.getNumericCellValue();
				// type
				long l = (long) numericCellValue;
				String valueOf = String.valueOf(l);
				// data = String.valueOf((long) cell.getNumericCellValue());
			}
		}
		return data;
	}

	public String getData(String SheetName, int rowNo, int cellNo) throws IOException {
		String data = null;
		File file = new File("C:\\Users\\Dell\\eclipse-workspace\\MavenFrst\\Excel\\Framework1.xlsx");
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(SheetName);
		Row row = sheet.getRow(rowNo);
		Cell cell = row.getCell(cellNo);
		String cellValue = cell.getStringCellValue();

		int type = cell.getCellType();

		if (type == 1) {
			data = cell.getStringCellValue();
		}
		if (type == 0) {

			if (DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yy");
				data = dateformat.format(dateCellValue);
			} else {
				double d = cell.getNumericCellValue();
				long l = (long) d;
				data = String.valueOf(l);
			}
		}
		return data;
	}

	public void updateData(String SheetName, int rowNo, int cellNo, String oldData, String newData) throws IOException {
		String data = null;
		File file = new File("C:\\Users\\Dell\\eclipse-workspace\\MavenFrst\\Excel\\Framework1.xlsx");
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet(SheetName);
		Row row = sheet.getRow(rowNo);
		Cell cell = row.getCell(cellNo);

		String value = cell.getStringCellValue();

		if (value.equals(oldData)) {
			cell.setCellValue(newData);
		}

		FileOutputStream str1 = new FileOutputStream(file);
		workbook.write(str1);
		System.out.println("done***");
	}

	private List<String> getOptionsText(WebElement element) {

		List<String> allOptions = new ArrayList<String>();
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (WebElement eachElement : options) {
			String data = eachElement.getText();
			System.out.println(data);
		}
		return allOptions;
	}

	public String getdatafromtable(int rownum, int cellnum) throws Exception {
		String data = null;
		File file = new File("C:\\Users\\Dell\\eclipse-workspace\\MavenFrst\\Excel\\Framework1.xlsx");
		FileInputStream stream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(stream);
		Sheet sheet = workbook.getSheet("data");
		
		Row row = sheet.getRow(rownum);
		Cell cell = row.getCell(cellnum);
		
		int type = cell.getCellType();
		
		if(type==1) {
			data = cell.getStringCellValue();
		}
		if(type==0) {
			if(DateUtil.isCellDateFormatted(cell)) {
				data = new SimpleDateFormat("dd-MMM-yy").format(cell.getDateCellValue());
			}else {
				data = String.valueOf((long)cell.getNumericCellValue());
			}
		}
		return data;
	}
	
	public void print(String data) {
		System.out.println(data);
	}
	
	
	public String getAttribute(WebElement element) {
		String attribute = element.getAttribute("value");
		return attribute;
	}

	public String getText(WebElement element) {
		String text = element.getText();
		return text;
	}
	
	public static void closeAllWind() {
		driver.quit();
	}
	public void closeWind() {
		driver.close();
	}
	
	public void click(WebElement element) {
		element.click();
	}
	
	public void type(WebElement element, String data) {
		element.sendKeys(data);
	}
	

	public static WebElement findElementid(String id) {
		WebElement elementid = driver.findElement(By.id(id));
		return elementid;
	}
	
	public static WebElement findElementName(String name) {
		WebElement elementname = driver.findElement(By.name(name));
		return elementname;
	}
	
	public WebElement findElementClass(String className) {
		WebElement elementClass = driver.findElement(By.className(className));
		return elementClass;
	}
	
	public WebElement findElementXpath(String xpath) {
		WebElement elementXpath = driver.findElement(By.xpath(xpath));
		return elementXpath;
	}
	
	public static void sendValues(WebElement element, String name) {
		element.sendKeys(name);
	}
	
	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}
	
	public Navigation navigate() {
		Navigation navigate = driver.navigate();
		return navigate;
	}
	
	public String navigateto(String toUrl) {
		navigate().to(toUrl);
		return toUrl;
	}
	public void back() {
		navigate().back();
	}
	
	public void forward() {
		navigate().forward();
	}
	
	public void refresh() {
		navigate().refresh();
	}
	
	public  String getUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}
	
	public Actions action() {
		Actions action = new Actions(driver);
		return action;
	}

}
