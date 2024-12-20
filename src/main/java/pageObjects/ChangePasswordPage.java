package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonMethod.AbstractMethod;

public class ChangePasswordPage extends AbstractMethod {

	WebDriver driver;

	public ChangePasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, Duration.ofSeconds(10));
	}

	@FindBy(xpath = "//div[@class='mdc-dialog__container']")
	private WebElement changePassword_Container;

	public WebElement getChangePassword_Container() {
		return changePassword_Container;
	}

	public WebElement getOldPasswordField() {
		return getChangePassword_Container().findElement(By.id("mat-input-6"));
	}

	public WebElement getNewPasswordField() {
		return getChangePassword_Container().findElement(By.id("mat-input-7"));
	}

	public WebElement getConfirmPasswordField() {
		return getChangePassword_Container().findElement(By.id("mat-input-8"));
	}

	@FindBy(xpath = "//label[text()='Old Password']")
	private WebElement OldPasswordFieldName;

	@FindBy(css = "mat-form-field input[placeholder$='Old Password']")
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

	public void changePasswordofUser(String oldpwd, String newpwd, String Confirmpwd) throws InterruptedException {
		sendValueIntoTextBox(OldPasswordField, oldpwd);
		sendValueIntoTextBox(NewPasswordField, newpwd);
		sendValueIntoTextBox(ConfirmPasswordField, Confirmpwd);
		clickOnButton(submitButton);
	}

	public void ChangeUserPassword(String oldpasswor, String newpassword, String confirmpassword)
			throws InterruptedException {
		sendKeysbyActionMethod(OldPasswordField, oldpasswor);
		sendKeysbyActionMethod(ConfirmPasswordField, confirmpassword);
		sendKeysbyActionMethod(NewPasswordField, confirmpassword);
		clickOnButton(submitButton);
	}

	public void changeuserPasswordfinally(String oldpwd, String newpwd, String confpwd) {
		WebElement mm = getOldPasswordField();
		mm.sendKeys(oldpwd);
		WebElement ww = getNewPasswordField();
		ww.sendKeys(newpwd);
		WebElement zz = getConfirmPasswordField();
		zz.sendKeys(confpwd);
	}

}
