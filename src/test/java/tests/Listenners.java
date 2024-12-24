package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import commonMethod.AbstractMethod;

public class Listenners extends BaseTest implements ITestListener {

    ExtentReports extent = AbstractMethod.extentReportObject();
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Failed");
        extentTest.get().fail(result.getThrowable());

        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            extentTest.get().fail("Failed to retrieve WebDriver instance: " + e.getMessage());
        }

        if (driver != null) {
            try {
                String filePath = captureScreenshot(result.getMethod().getMethodName(), driver);
                if (filePath != null) {
                    extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
                }
            } catch (IOException e) {
                extentTest.get().fail("Failed to capture screenshot: " + e.getMessage());
            }
        } else {
            extentTest.get().log(Status.WARNING, "Driver is null, screenshot could not be captured");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Implement if needed
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result); // Treat timeout as failure
    }

    @Override
    public void onStart(ITestContext context) {
        // Optional: Code to execute before any tests run
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
