package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import tests.BaseTest;
import utils.ScreenshotUtil;

public class TestListener extends BaseTest implements ITestListener {

    public void onTestStart(ITestResult result) {

        System.out.println("STARTED: " + result.getName());
    }

    public void onTestSuccess(ITestResult result) {

        System.out.println("PASSED: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {

        System.out.println("FAILED: " + result.getName());

        ScreenshotUtil.captureScreenshot(driver, result.getName());
    }

    public void onStart(ITestContext context) {

        System.out.println("=== TEST EXECUTION STARTED ===");
    }

    public void onFinish(ITestContext context) {

        System.out.println("=== TEST EXECUTION FINISHED ===");
    }
}