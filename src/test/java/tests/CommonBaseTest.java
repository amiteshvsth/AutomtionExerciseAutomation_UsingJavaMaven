package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonBaseTest {

    protected static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    protected static ExtentReports extent;
    @BeforeSuite(alwaysRun = true)
    public void setupExtentReport() {

        if (extent != null) return;

        String reportFolderPath = System.getProperty("user.dir") + "/ExtentReport/reports/";
        String screenshotsFolderPath = System.getProperty("user.dir") + "/ExtentReport/screenshots/";
        File reportFolder = new File(reportFolderPath);
        File screenshotFolder = new File(screenshotsFolderPath);

        deleteFolder(reportFolder);
        deleteFolder(screenshotFolder);
        
        reportFolder.mkdirs();
        screenshotFolder.mkdirs();

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String reportPath = reportFolderPath + "ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Functional + API Automation Results");
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
            System.out.println("Extent report flushed successfully.");
        }
    }

    private void deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
            folder.delete();
        }
    }
}
