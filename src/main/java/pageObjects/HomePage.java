package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonMethod.AbstractMethod;

public class HomePage extends AbstractMethod {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'d-flex flex-row align-items-center')]/span")
	private WebElement usernametext;

	@FindBy(xpath = "//mat-icon[text()='person']")
	private WebElement personExpandMenu;

	@FindBy(xpath = "//span[text()='Change Password ']")
	private WebElement changePassword;

	@FindBy(xpath = "//span[normalize-space()='Profile']")
	private WebElement profile;

	@FindBy(xpath = "//span[normalize-space()='LogOut']")
	private WebElement LogOutButton;

	@FindBy(css = ".spinner-container")
	private WebElement spinner;

	@FindBy(xpath = "//span[text()='Campaign']")
	private WebElement campaign_Menu;

	public CampaignPage clickonCampaignMenu() throws InterruptedException {
		clickOnButton(campaign_Menu);
		CampaignPage campaignPage = new CampaignPage(driver);
		return campaignPage;
	}

	public WebElement getSpinner() {
		return spinner;
	}

	public void waitForSpinnertoInvisible() {
		WaitForElementToInvisible(getSpinner());
	}

	public void clickOnPersonExpandMenu() throws InterruptedException {
		clickOnButton(personExpandMenu);
	}

	public WebElement getuserNameText() {
		return usernametext;
	}

	public ChangePasswordPage clickOnchangePassword() throws InterruptedException {
		waitForSpinnertoInvisible();
		// Thread.sleep(5000);
		clickOnPersonExpandMenu();
		clickOnButton(changePassword);
		ChangePasswordPage changepasswordpage = new ChangePasswordPage(driver);
		return changepasswordpage;
	}

	public void OpenProfilePage() throws InterruptedException {
		clickOnPersonExpandMenu();
		clickOnButton(profile);
	}

	public void logoutFromApplication() throws InterruptedException {
		clickOnPersonExpandMenu();
		clickOnButton(LogOutButton);
	}
}
