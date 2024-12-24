package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import pageObjects.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginpage;

	public WebDriver getDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\resources\\Data\\GlobalData.properties");
		prop.load(fis);
		 String browserName = System.getProperty("browser") !=null ? System.getProperty("browser") :prop.getProperty("browser");
		
		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;

		case "safari":
			driver = new SafariDriver();
			break;
		default:
			throw new IllegalArgumentException("Browser Not Supported: " + browserName);

		}
		driver.manage().window().maximize();
		return driver;
	}

	
	public LoginPage launchApplication() throws IOException {
		driver = getDriver();
		driver.get("https://sipserver.ivrobd.com/login");
		loginpage = new LoginPage(driver);
		return loginpage;
	}

	@AfterClass(enabled = false)
	public void tearDown() {
		driver.quit();
	}
	
	public String captureScreenshot(String testCaseName, WebDriver driver) throws IOException {
	    if (driver == null) {
	        throw new IllegalArgumentException("WebDriver instance is null");
	    }

	    // Take a screenshot
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);

	    // Ensure the reports directory exists
	    File reportsDir = new File(System.getProperty("user.dir") + File.separator + "reports");
	    if (!reportsDir.exists()) {
	        reportsDir.mkdirs();
	    }

	    // Generate a unique file name
	    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    String filePath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + testCaseName + "_" + timestamp + ".png";

	    // Save the screenshot
	    File destination = new File(filePath);
	    FileUtils.copyFile(source, destination);

	    return filePath;
	}

}
