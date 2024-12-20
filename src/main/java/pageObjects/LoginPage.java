package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonMethod.AbstractMethod;

public class LoginPage extends AbstractMethod {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@id='mat-input-0']")
	private WebElement userName;

	@FindBy(xpath = "//input[@id='mat-input-1']")
	private WebElement userPassword;

	@FindBy(xpath = "//input[@id='mat-input-2']")
	private WebElement userDomain;

	@FindBy(xpath = "//button[text()='Login']")
	private WebElement loginButton;

	public String getUserName() {
		return userName.getText();
	}

	public String getUserPassword() {
		return userPassword.getText();
	}

	public String getUserDomain() {
		return userDomain.getText();
	}

	public HomePage loginIntoApplication(String uname, String upassword, String udomain) throws InterruptedException {
		sendValueIntoTextBox(userName, uname);
		sendValueIntoTextBox(userPassword, upassword);
		sendValueIntoTextBox(userDomain, udomain);
		clickOnButton(loginButton);
		HomePage homePage = new HomePage(driver);
		return homePage;
	}

}
