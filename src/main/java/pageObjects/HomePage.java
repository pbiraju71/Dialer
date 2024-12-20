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

	public WebElement getSpinner() {
		return spinner;
	}

	public void waitForSpinnertoInvisible() {
		WaitForElementToInvisible(getSpinner());
	}

	public void clickOnPersonExpandMenu() throws InterruptedException {
		clickOnButton(personExpandMenu);
	}

	public ChangePasswordPage clickOnchangePassword() throws InterruptedException {
		waitForSpinnertoInvisible();
		//Thread.sleep(5000);
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
