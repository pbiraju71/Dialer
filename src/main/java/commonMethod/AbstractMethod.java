package commonMethod;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractMethod {

	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	Actions act;

	public AbstractMethod(WebDriver driver) {
		this.driver = driver;
	}

	public void WaitForElementTobeClickable(WebElement ele) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void WaitForElementtoPresent(WebElement ele) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void WaitForElementToInvisible(WebElement ele) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public void sendValueIntoTextBox(WebElement ele, String value) {
		WaitForElementtoPresent(ele);
		ele.clear();
		ele.sendKeys(value);
	}

	public void clickOnButton(WebElement ele) throws InterruptedException {
		WaitForElementTobeClickable(ele);
		Thread.sleep(5000);
		ele.click();
	}

	public void sendKeysbyActionMethod(WebElement ele, String text) {
		WaitForElementtoPresent(ele);
		ele.clear();
		WaitForElementTobeClickable(ele);
		act = new Actions(driver);
		act.sendKeys(ele, text).build().perform();
	}

	public void getDataFromExcel() throws IOException {
		FileInputStream fis = new FileInputStream("D:\\Automation\\Data\\ExcelDataDriven.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int totalRows = sheet.getLastRowNum();
		short totalCells = sheet.getRow(0).getLastCellNum();
		System.out.println(totalRows);
		System.out.println(totalCells);
		for (int r = 0; r < totalRows; r++) {
			XSSFRow currentRow = sheet.getRow(r);
			for (int c = 0; c < totalCells; c++) {
				XSSFCell cell = currentRow.getCell(c);
				System.out.print(cell.toString() + "\t");
			}
			System.out.println();
		}
		workbook.close();
		fis.close();

	}

	public void writeinExcelFile() throws IOException {
		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\resources\\Data\\myfile.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		XSSFRow row1 = sheet.createRow(0);
		row1.createCell(0).setCellValue("TestCases");
		row1.createCell(1).setCellValue("Environment");
		row1.createCell(2).setCellValue("UserName");
		row1.createCell(3).setCellValue("Password");
		row1.createCell(4).setCellValue("Domain");

		XSSFRow row2 = sheet.createRow(1);
		row2.createCell(0).setCellValue("Admin_Login");
		row2.createCell(1).setCellValue("QA_Env");
		row2.createCell(2).setCellValue("bankbazar");
		row2.createCell(3).setCellValue("Admin@1234");
		row2.createCell(4).setCellValue("baar");

		XSSFRow row3 = sheet.createRow(2);
		row3.createCell(0).setCellValue("Agent_Login");
		row3.createCell(1).setCellValue("QA_Env");
		row3.createCell(2).setCellValue("agent1");
		row3.createCell(3).setCellValue("Admin@1234");
		row3.createCell(4).setCellValue("baar");

		XSSFRow row4 = sheet.createRow(3);
		row4.createCell(0).setCellValue("MIS_Login");
		row4.createCell(1).setCellValue("QA_Env");
		row4.createCell(2).setCellValue("MIS1");
		row4.createCell(3).setCellValue("Admin@1234");
		row4.createCell(4).setCellValue("baar");

		workbook.write(fos);
		workbook.close();
		fos.close();
		System.out.println("Excel Sheet Created with Test Data");
	}

	public void CreateDynamicExcelFile() throws IOException {
		FileOutputStream fos = new FileOutputStream(
				System.getProperty("user.dir") + "\\resources\\Data\\myfile_Dynamic.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Dynamic");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter how many rows your want to generate?");
		int numberofRows = sc.nextInt();
		System.out.println("Enter how many cells your want to generate?");
		int numberofCells = sc.nextInt();
		for (int r = 0; r < numberofRows; r++) {
			XSSFRow currentRow = sheet.createRow(r);
			for (int c = 0; c < numberofCells; c++) {
				XSSFCell cell = currentRow.createCell(c);
				cell.setCellValue(sc.next());
			}
		}
		
		workbook.write(fos);
		workbook.close();
		fos.close();
		System.out.println("Dynamic Excel Sheet Created with Test Data");
	}
}
