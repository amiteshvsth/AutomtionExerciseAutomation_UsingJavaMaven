package tests.base;


import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import utilities.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;
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
            File networkLogsFolder = new File("networkLogs");
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
            if (networkLogsFolder.exists()) {
                javaHelpers.deleteAllFilesFromFolder("networkLogs");
            }


        } catch (Exception e) {
            Reporter.log("Error in beforeSuiteSetup: " + e.getMessage());
        }
    }

    @BeforeMethod
    @Parameters({"browser", "browserMode", "URL"})
    public void setUp(@Optional("chrome") String browser, @Optional("normal") String browserMode,
                      @Optional("USA_ADMIN") String baseUrl, Method method) {
        try {


            Map<String, Object> customPrefs = new HashMap<>();

            if (method.getName().equals("verifyOutstandingInvoicesFunctionality") ||
                    method.getName().equals("verifyThatUserIsAbleToAddInvoiceMemoAndIsPrintedInPdfAndIsAbleToDownloadAndPrintPdfFromActionColumnSuccessfully")
                    || method.getName().equals("verifyThatUserIsAbleToDownloadAndPrintInvoicePdfOfSelectedInvoicesFromDownloadLinkAtTop")
                    || method.getName().equals("verifyUniqueInvoiceNumberGeneratedUserCanFinalizeThatInvoiceAndPerformReceivePaymentWithDownloadAndOpenInNewTabFunctionalityThroughClientPayerTab")) {
                customPrefs.put("plugins.always_open_pdf_externally", false);
            } else {
                customPrefs.put("plugins.always_open_pdf_externally", true);
            }

            driverManager = new DriverManager();
            driver = driverManager.setUp(browser, browserMode, customPrefs);
            selenium = new SeleniumHelpers(driver);
            Logger.reset();


        } catch (Exception e) {
            isSetupFailed.set(true);
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

}
