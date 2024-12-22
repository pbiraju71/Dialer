package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonMethod.AbstractMethod;

public class CampaignPage extends AbstractMethod {

	WebDriver driver;
	HomePage homePage;
	CampaignPage campaignPage;

	public CampaignPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[normalize-space()='Add Campaign']")
	private WebElement add_Campaignbtn;

	@FindBy(css = "#mat-input-6")
	private WebElement campaign_name;

	@FindBy(xpath = "//span[text()='Select Campaign Type']")
	private WebElement campaign_type;

	String commonXpath = "//span[contains(@class,'mdc-list-item__primary-text')]";

	@FindBy(xpath = "//mat-select[@placeholder='Select DID Scheme']")
	private WebElement DID_Scheme;

	@FindBy(xpath = "//input[@formcontrolname='startTime']")
	private WebElement starttime;

	@FindBy(xpath = "//input[@formcontrolname='endTime']")
	private WebElement endTime;

	@FindBy(xpath = "//mat-select[@id='mat-select-4']")
	private WebElement crmTypeddl;

	public void openCampaignForm() throws InterruptedException {
		clickOnButton(add_Campaignbtn);
	}

	public void createCampaign(String CampaignType, String campaignName, String campaignoption) throws InterruptedException {
		sendValueIntoTextBox(campaign_name, campaignName);
		clickOnButton(campaign_type);
		handleDynamicDDL(driver, commonXpath, campaignoption);
	}

}
