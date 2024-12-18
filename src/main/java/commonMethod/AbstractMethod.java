package commonMethod;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractMethod {

	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	public AbstractMethod(WebDriver driver) {
		this.driver = driver;
	}

	public void WaitForElementTobeClickable(WebElement ele) {

		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void WaitForElementtoPresent(WebElement ele) {

		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void WaitForElementToInvisible(WebElement ele) {
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public void sendValueIntoTextBox(WebElement ele, String value) {
		WaitForElementtoPresent(ele);
		ele.clear();
		ele.sendKeys(value);
	}

	public void clickOnButton(WebElement ele) {
		WaitForElementTobeClickable(ele);
		ele.click();
	}

	public void sendKeysbyActionMethod(WebDriver driver, WebElement ele, String text) {
		WaitForElementtoPresent(ele);
		ele.clear();
		Actions act = new Actions(driver);
		WaitForElementTobeClickable(ele);
		act.sendKeys(ele, text).build().perform();
	}

}
