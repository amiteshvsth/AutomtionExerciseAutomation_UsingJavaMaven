package tests.Functional.base;


import Functional.utilities.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;
    DriverManager driverManager;
    protected SeleniumHelpers selenium;
    protected JavaHelpers javaHelpers = new JavaHelpers();
    protected ThreadLocal<Boolean> isSetupFailed = ThreadLocal.withInitial(() -> false);




    @BeforeSuite
    public void beforeSuiteSetup() {
        try {
            String reportName = System.getProperty("user.dir") + "/ExtentReport/reports/";

            File extentReportFolder = new File(Constants.EXTENT_REPORT);
            File screenshotFolder = new File(Constants.SCREENSHOT_LOCATION);
            File downloadFolder = new File(Constants.DOWNLOAD_FOLDER);
            if (!extentReportFolder.exists()) {
                extentReportFolder.mkdirs();
                screenshotFolder.mkdirs();
            } else {
                if (!screenshotFolder.exists()) {
                    screenshotFolder.mkdirs();
                }
            }
            if (!downloadFolder.exists()) {
                downloadFolder.mkdirs();
            }
            javaHelpers.deleteAllFilesFromFolder(Constants.SCREENSHOT_LOCATION);
            javaHelpers.deleteAllFilesFromFolder(reportName);
            javaHelpers.deleteAllFilesFromFolder(Constants.DOWNLOAD_FOLDER);
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
                    reportName + "ExtentReport_" + javaHelpers.getTimeStamp("yyyyMMdd_HHmmss") + ".html");
            sparkReporter.config().setDocumentTitle("Automation Exercise - Automation Report");
            sparkReporter.config().setReportName("UI Automation Report");
            sparkReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User", System.getProperty("user.name"));


        } catch (Exception e) {
            Reporter.log("Error in beforeSuiteSetup: " + e.getMessage());
        }
    }

    @BeforeMethod
    @Parameters({"browser", "browserMode"})
    public void setUp(@Optional("chrome") String browser, @Optional("normal") String browserMode, Method method) {
        try {
            String className = this.getClass().getSimpleName();
            String methodName = method.getName();
            test = extent.createTest(methodName).assignCategory(className);

            Map<String, Object> customPrefs = new HashMap<>();

            driverManager = new DriverManager();
            driver = driverManager.setUp(browser, browserMode, customPrefs);
            driver.manage().window().maximize();
            selenium = new SeleniumHelpers(driver);

            selenium.navigateToPage(Constants.LOGIN_PAGE_URL);
            Logger.reset();
            test.info("Browser setup successful");
        } catch (Exception e) {
            isSetupFailed.set(true);
            test.fail("Browser setup failed: " + e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (isSetupFailed.get()) {
                result.setStatus(ITestResult.FAILURE);
                result.setThrowable(new Exception("Test aborted due to " + result.getThrowable().getMessage()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in tearDown: " + e.getMessage(), e);
        } finally {
            driverManager.tearDown();
            isSetupFailed.remove();
            Logger.remove();
        }
    }

    @AfterSuite
    public void tearDownExtent() {
        if (extent != null) {
            extent.flush();
        }
    }

    private void captureTestResult(ITestResult result) throws IOException, InterruptedException {
        if (test == null) {
            test = extent.createTest(result.getMethod().getMethodName());
        }
        Reporter.getOutput(result).forEach(test::info);
        attachScreenshot(result.getName());
        selenium.hardWait(5);

        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                test.fail("Test failed: " + result.getThrowable().getMessage());
                break;
            case ITestResult.SUCCESS:
                test.pass("Test passed");
                break;
            case ITestResult.SKIP:
                Throwable cause = result.getThrowable();
                if (cause != null) {
                    test.skip("Test skipped due to: " + cause.getMessage());
                } else {
                    test.skip("Test was skipped automatically by TestNG");
                }
                break;
            default:
                test.info("Test case did not report pass/fail/skip");
        }
    }

    private void attachScreenshot(String testName) {
        try {
            String screenshotPath = selenium.takeScreenshot(testName);
            String relativePath = "../screenshots/" + new File(screenshotPath).getName();
            test.addScreenCaptureFromPath(relativePath);
        } catch (Exception e) {
            test.warning("Screenshot capture failed: " + e.getMessage());
        }
    }

}
