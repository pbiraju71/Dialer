package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class LoginPageTest extends BaseTest {
	
	public WebDriver driver;

	LoginPage loginpage;

	@Test(priority = 0, enabled = true)
	public void loginIntoApplication() throws InterruptedException, IOException {
		loginpage = launchApplication();
		loginpage.loginIntoApplication("bankbazar", "Admin@1234", "baar");
		Thread.sleep(5000);
	}

}
