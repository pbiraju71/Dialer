package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonMethod.AbstractMethod;

public class ChangePasswordPage extends AbstractMethod{
	
	WebDriver driver;

	public ChangePasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//label[text()='Old Password']")
	private WebElement OldPasswordFieldName;

	@FindBy(xpath = "//input[@id='mat-input-6']")
	private WebElement OldPasswordField;

	@FindBy(xpath = "//label[text()=' New Password']")
	private WebElement NewPasswordFieldName;

	@FindBy(xpath = "//input[@id='mat-input-7']")
	private WebElement NewPasswordField;

	@FindBy(xpath = "//label[text()='Confirm Password']")
	private WebElement ConfirmPasswordfieldName;

	@FindBy(xpath = "//input[@id='mat-input-8']")
	private WebElement ConfirmPasswordField;

	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement cancelButton;

	@FindBy(xpath = "//button[text()='Submit']")
	private WebElement submitButton;

	public void changePasswordofUser(String oldpwd, String newpwd, String Confirmpwd) {
		sendValueIntoTextBox(OldPasswordField, oldpwd);
		sendValueIntoTextBox(NewPasswordField, newpwd);
		sendValueIntoTextBox(ConfirmPasswordField, Confirmpwd);
		clickOnButton(submitButton);
	}

	public void ChangeUserPassword(String oldpasswor, String newpassword, String confirmpassword) {
		sendKeysbyActionMethod(driver, OldPasswordField, null);
		sendKeysbyActionMethod(driver, ConfirmPasswordField, confirmpassword);
		sendKeysbyActionMethod(driver, NewPasswordField, confirmpassword);
		clickOnButton(submitButton);
	}

}
