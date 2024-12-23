package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import commonMethod.AbstractMethod;

public class POC_1 {
	WebDriver driver;

	@Test(enabled = false)
	public void poc11() throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://sipserver.ivrobd.com/login");
		driver.manage().window().maximize();
		driver.findElement(By.id("mat-input-0")).sendKeys("bankbazar");
		driver.findElement(By.id("mat-input-1")).sendKeys("Admin@1234");
		driver.findElement(By.id("mat-input-2")).sendKeys("baar");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//mat-icon[text()='person']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[normalize-space()='Change Password']")).click();
		WebElement changePassword_Container = driver.findElement(By.xpath("//div[@class='mdc-dialog__container']"));
		WebElement changePasswordField = changePassword_Container.findElement(By.id("mat-input-6"));
		changePasswordField.sendKeys("Admin@1234");
		WebElement newPasswordField = changePassword_Container.findElement(By.id("mat-input-7"));
		newPasswordField.sendKeys("Admin@12345");
		WebElement confirmPasswordField = changePassword_Container.findElement(By.id("mat-input-8"));
		confirmPasswordField.sendKeys("Admin@12345");
	}

	@Test(enabled = false)
	public void getData() throws IOException {
		AbstractMethod m = new AbstractMethod(driver);
		//m.getDataFromExcel();
	}

	@Test
	public void createExcelsheet() throws IOException {
		AbstractMethod m = new AbstractMethod(driver);
		m.writeinExcelFile();
	}

	@Test
	public void generateDynamicExcelsheet() throws IOException {
		AbstractMethod m = new AbstractMethod(driver);

		m.CreateDynamicExcelFile();
	}

}
