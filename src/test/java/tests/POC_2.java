package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class POC_2 {

	public static void main(String[] args) {
		WebDriver driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.navigate().to("https://tutorialsninja.com/demo/index.php?route=account/register");
		long startTime, endTime;
		startTime = System.currentTimeMillis();

		String userName = driver.findElement(By.id("input-firstname")).getDomProperty("placeholder");
		System.out.println(userName);
		endTime = System.currentTimeMillis();

		System.out.println("Time taken by ID locator for same field: " + (endTime - startTime) + " milliseconds");

		long startTime1, endTime1;
		startTime1 = System.currentTimeMillis();

		String userName1 = driver.findElement(By.cssSelector("#input-firstname")).getDomProperty("placeholder");
		System.out.println(userName1);
		endTime1 = System.currentTimeMillis();

		System.out.println("Time taken by CSS locator for same field: " + (endTime1 - startTime1) + " milliseconds");

		long startTime2 = 0, endTime2 = 0;
		startTime2 = System.currentTimeMillis();

		String userName2 = driver.findElement(By.xpath("//input[@id='input-firstname']")).getDomProperty("placeholder");
		System.out.println(userName2);
		endTime2 = System.currentTimeMillis();

		System.out.println("Time taken by xpath locator for same field: " + (endTime2 - startTime2) + " milliseconds");

		long startTime3 = 0, endTime3 = 0;
		startTime3 = System.currentTimeMillis();

		List<WebElement> userName3 = driver.findElements(By.className("form-control"));
		for (WebElement value : userName3) {
			if (value.getDomProperty("placeholder").equalsIgnoreCase("First Name")) {
				// System.out.println(value.getDomProperty("placeholder"));
				break;
			}

		}

		endTime3 = System.currentTimeMillis();

		System.out.println(
				"Time taken by className locator for same field: " + (endTime3 - startTime3) + " milliseconds");

		long startTime4 = 0, endTime4 = 0;
		startTime4 = System.currentTimeMillis();

		String userName4 = driver.findElement(By.name("firstname")).getDomProperty("placeholder");
		System.out.println(userName4);
		endTime4 = System.currentTimeMillis();

		System.out.println("Time taken by Name locator for same field: " + (endTime4 - startTime4) + " milliseconds");
		driver.quit();
	}

}
