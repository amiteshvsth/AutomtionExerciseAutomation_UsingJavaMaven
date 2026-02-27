package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonBaseTest {

    protected static ExtentReports extent;

    @BeforeSuite(alwaysRun = true)
    public void setupExtentReport() {

        if (extent != null) return;

        String reportFolderPath = System.getProperty("user.dir") + "/ExtentReport/reports/";
        File reportFolder = new File(reportFolderPath);

        if (!reportFolder.exists()) {
            reportFolder.mkdirs();
        }

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String reportPath = reportFolderPath + "ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("UI + API Automation Results");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
    }

    @AfterSuite(alwaysRun = true)
    public void flushExtentReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
