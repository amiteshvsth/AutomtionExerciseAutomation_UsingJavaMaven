package Functional.utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    private WebDriver driver;

    public WebDriver setUp(String browserName, String browserMode, Map<String, Object> customPrefs) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
                prefs.put("profile.default_content_setting_values.notifications", 2);
                prefs.put("profile.default_content_settings.popups", 0);
                prefs.put("download.default_directory", Constants.DOWNLOAD_FOLDER);
                prefs.put("download.prompt_for_download", false);
                prefs.put("download.directory_upgrade", true);
                prefs.putAll(customPrefs);
                ChromeOptions cOptions = new ChromeOptions();
                cOptions.setExperimentalOption("prefs", prefs);
                if (browserMode.equalsIgnoreCase("incognito")) {
                    cOptions.addArguments("--incognito");
                }
                driver = new ChromeDriver(cOptions);
                break;
            case "chrome-headless":
                Map<String, Object> prefs1 = new HashMap<>();
                prefs1.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
                prefs1.put("profile.default_content_setting_values.notifications", 2);
                prefs1.put("profile.default_content_settings.popups", 0);
                prefs1.put("download.default_directory", Constants.DOWNLOAD_FOLDER);
                prefs1.put("download.prompt_for_download", false);
                prefs1.put("download.directory_upgrade", true);
                prefs1.putAll(customPrefs);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("headless=new");
                chromeOptions.addArguments("window-size=1440x900");
                chromeOptions.addArguments("--proxy-server='direct://'");
                chromeOptions.addArguments("--proxy-bypass-list=*");
                chromeOptions.setExperimentalOption("prefs", prefs1);
                if (browserMode.equalsIgnoreCase("incognito")) {
                    chromeOptions.addArguments("--incognito");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
            default:
                throw new IllegalArgumentException("Please specify valid browser name. Valid browser names are: chrome, chrome-headless");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().deleteAllCookies();
        return driver;
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
