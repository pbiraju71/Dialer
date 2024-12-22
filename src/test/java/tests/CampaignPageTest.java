package tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import commonMethod.ExcelUtility;
import pageObjects.CampaignPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class CampaignPageTest extends BaseTest {

	public WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	CampaignPage campaignPage;

	@Test
	public void createCampaign() throws IOException, InterruptedException {
		loginPage = launchApplication();
		homePage = loginPage.loginIntoApplication("bankbazar", "Admin@1234", "baar");
		Thread.sleep(5000);
		campaignPage = homePage.clickonCampaignMenu();
		campaignPage.openCampaignForm();
		String xlfilePath = System.getProperty("user.dir") + "\\resources\\Data\\TestCases.xlsx";
		int rows = ExcelUtility.getRowCount(xlfilePath, "Sheet1");
		for (int i = 1; i <= rows; i++) {
			String Campaign_Name = ExcelUtility.getCellData(xlfilePath, "Sheet1", i, 1);
			String Campaign_Type = ExcelUtility.getCellData(xlfilePath, "Sheet1", i, 2);
			// String DID_Scheme = ExcelUtility.getCellData(xlfilePath, "Sheet1", i, 3);
			campaignPage.createCampaign("Manual", Campaign_Name, Campaign_Type);

		}

	}

}
