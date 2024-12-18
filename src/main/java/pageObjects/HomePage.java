package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonMethod.AbstractMethod;

public class HomePage extends AbstractMethod{

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

	public void clickOnPersonExpandMenu() {
		clickOnButton(personExpandMenu);
	}

	public ChangePasswordPage clickOnchangePassword() {
		clickOnPersonExpandMenu();
		clickOnButton(changePassword);
		ChangePasswordPage changepasswordpage = new ChangePasswordPage(driver);
		return changepasswordpage;
	}

	public void OpenProfilePage() {
		clickOnPersonExpandMenu();
		clickOnButton(profile);
	}

	public void logoutFromApplication() {
		clickOnPersonExpandMenu();
		clickOnButton(LogOutButton);
	}
}
