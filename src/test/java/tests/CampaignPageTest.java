package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.IRetryAnalyzer;
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

	@Test(retryAnalyzer = Retry.class)
	public void createCampaign() throws IOException, InterruptedException {
		loginPage = launchApplication();
		homePage = loginPage.loginIntoApplication("bankbazar", "Admin@1234", "baar");
		Thread.sleep(5000);
		campaignPage = homePage.clickonCampaignMenu();
		campaignPage.openCampaignForm();
		String xlfilePath = System.getProperty("user.dir") + "\\resources\\Data\\TestCases.xlsx";
		ArrayList<String> data = ExcelUtility.getDataFromExcel("CreateCampaign",xlfilePath);
		campaignPage.createCampaign(data.get(1),data.get(2), "Manual");
	}

}
