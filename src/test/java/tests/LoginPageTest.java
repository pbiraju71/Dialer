package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonMethod.ExcelUtility;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class LoginPageTest extends BaseTest {

	public WebDriver driver;

	LoginPage loginpage;
	HomePage homepage;

	@Test(priority = 0, enabled = false)
	public void loginIntoApplication() throws InterruptedException, IOException {
		loginpage = launchApplication();
		homepage =loginpage.loginIntoApplication("bankbazar", "Admin@1234", "baar");
		Thread.sleep(9000);
		String loggedInUserName = homepage.getuserNameText().getText();
		 String[] ll = loggedInUserName.split(",");
		System.out.println(ll[0].toString());
	}

	@Test(enabled=true)
	public void verifyLoginFunctionalityforAllRoles() throws IOException, InterruptedException {
		loginpage = launchApplication();
		String xlfilePath = System.getProperty("user.dir") + "\\resources\\Data\\ExcelDataDriven.xlsx";
		int rows = ExcelUtility.getRowCount(xlfilePath, "Sheet1");
		for (int i = 1; i <= rows; i++) {
			String UserName = ExcelUtility.getCellData(xlfilePath, "Sheet1", i, 2);
			String Password = ExcelUtility.getCellData(xlfilePath, "Sheet1", i, 3);
			String Domain = ExcelUtility.getCellData(xlfilePath, "Sheet1", i, 4);
			homepage = loginpage.loginIntoApplication(UserName, Password, Domain);
			Thread.sleep(9000);
			String loggedInUserName = homepage.getuserNameText().getText();
			 String[] ll = loggedInUserName.split(",");
			String loggedInUserNameText = ll[0].toString();
			if (loggedInUserNameText.equalsIgnoreCase(UserName)) {
				System.out.println("Test Passed");
				ExcelUtility.setCellData(xlfilePath, "Sheet1", i, 6, "Passed");
				ExcelUtility.fillGreenColor(xlfilePath, "Sheet1", i, 6);
			} else {
				System.out.println("Test Failed");
				ExcelUtility.setCellData(xlfilePath, "Sheet1", i, 6, "Failed");
				ExcelUtility.fillRedColor(xlfilePath, "Sheet1", i, 6);
			}
			Thread.sleep(5000);
			homepage.logoutFromApplication();
			Thread.sleep(5000);
		}

	}

	@BeforeMethod
	public void setUp() {
		this.homepage = new HomePage(driver); // Ensure 'driver' is initialized too
	}

}
