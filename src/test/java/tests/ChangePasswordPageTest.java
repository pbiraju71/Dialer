package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pageObjects.ChangePasswordPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class ChangePasswordPageTest  extends BaseTest{
	
	WebDriver driver;
	LoginPage loginpage;
	HomePage homepage;
	ChangePasswordPage changepasswordpage;

	@Test()
	public void clickOnchangePassword() throws InterruptedException, IOException {
		Thread.sleep(3000);
		loginpage = launchApplication();
		homepage = loginpage.loginIntoApplication("bankbazar", "Admin@1234", "baar");
		Thread.sleep(5000);
		changepasswordpage = homepage.clickOnchangePassword();
		Thread.sleep(3000);
		// changepasswordpage.changePasswordofUser("Admin@1234", "Admin@12345",
		// "Admin@12345");
		changepasswordpage.ChangeUserPassword("Admin@1234", "Admin@123456", "Admin@123456");
	}

	@Test(dependsOnMethods = "clickOnchangePassword")
	public void changeUserPassword() throws InterruptedException {
		Thread.sleep(3000);
		changepasswordpage = new ChangePasswordPage(driver);
		changepasswordpage.changePasswordofUser("Admin@1234", "Admin@12345", "Admin@12345");
	}

}
