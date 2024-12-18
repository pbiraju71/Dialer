package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;

public class HomePageTest  extends BaseTest{

	WebDriver driver;
	LoginPage loginpage;
	HomePage homepage;

	@Test()
	public void logoutFromApplication() throws InterruptedException, IOException {
		loginpage = launchApplication();
		homepage = loginpage.loginIntoApplication("bankbazar", "Admin@1234", "baar");
		homepage.logoutFromApplication();
		Thread.sleep(3000);
	}
}
