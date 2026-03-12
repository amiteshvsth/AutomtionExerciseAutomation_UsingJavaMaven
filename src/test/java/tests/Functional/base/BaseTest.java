package tests.Functional.base;

import Functional.utilities.*;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import tests.CommonBaseTest;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseTest extends CommonBaseTest {

    protected WebDriver driver;
    protected ExtentTest test;
    DriverManager driverManager;
    protected SeleniumHelpers selenium;
    protected ThreadLocal<Boolean> isSetupFailed = ThreadLocal.withInitial(() -> false);

    @BeforeMethod
    @Parameters({"browser", "browserMode"})
    public void setUp(@Optional("chrome") String browser,
                      @Optional("normal") String browserMode,
                      Method method) {

        try {
            String className = this.getClass().getSimpleName();
            String methodName = method.getName();
            extentTest.set(
                    extent.createTest(methodName).assignCategory(className)
            );
            test = extentTest.get();

            Map<String, Object> customPrefs = new HashMap<>();

            driverManager = new DriverManager();
            driver = driverManager.setUp(browser, browserMode, customPrefs);
            selenium = new SeleniumHelpers(driver);

            selenium.navigateToPage(Constants.LOGIN_PAGE_URL);
            Logger.reset();
            test.info("Browser setup successful");

        } catch (Exception e) {
            isSetupFailed.set(true);
            throw new RuntimeException("Browser setup failed", e);
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        try {

            if (isSetupFailed.get()) {
                result.setStatus(ITestResult.FAILURE);
                result.setThrowable(new Exception("Test aborted due to setup failure"));
            }

            captureTestResult(result);

        } catch (Exception e) {
            throw new RuntimeException("Error in tearDown: " + e.getMessage(), e);
        } finally {
            DriverManager.tearDown();
            isSetupFailed.remove();
            Logger.remove();
            extentTest.remove();
        }
    }

    private void captureTestResult(ITestResult result) {

        if (test == null) {
            test = extent.createTest(result.getMethod().getMethodName());
        }

        Reporter.getOutput(result).forEach(test::info);
        attachScreenshot(result.getName());

        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                test.fail(result.getThrowable());
                break;
            case ITestResult.SUCCESS:
                test.pass("Test passed");
                break;
            case ITestResult.SKIP:
                test.skip(result.getThrowable());
                break;
        }
    }

    private void attachScreenshot(String testName) {
        try {
            String browser = System.getProperty("browser");
            String screenshotPath = selenium.takeScreenshot(testName);
            String relativePath = "../screenshots/"  + browser + new File(screenshotPath).getName();
            test.addScreenCaptureFromPath(relativePath);
        } catch (Exception e) {
            test.warning("Screenshot capture failed: " + e.getMessage());
        }
    }
}