package Functional.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.awt.Toolkit;
import java.awt.Dimension;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public WebDriver setUp(String browserName, String browserMode, Map<String, Object> customPrefs) {

        switch (browserName.toLowerCase()) {

            case "chrome":
                WebDriverManager.chromedriver().setup();

                Map<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
                chromePrefs.put("profile.default_content_setting_values.notifications", 2);
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", Constants.DOWNLOAD_FOLDER);
                chromePrefs.put("download.prompt_for_download", false);
                chromePrefs.put("download.directory_upgrade", true);
                chromePrefs.putAll(customPrefs);

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("prefs", chromePrefs);

                chromeOptions.addArguments(
                        "--host-resolver-rules=MAP googlesyndication.com 127.0.0.1, " +
                                "MAP doubleclick.net 127.0.0.1, " +
                                "MAP googleads.g.doubleclick.net 127.0.0.1"
                );

                if (browserMode.equalsIgnoreCase("incognito")) {
                    chromeOptions.addArguments("--incognito");
                }

                driver.set(new ChromeDriver(chromeOptions));
                break;


            case "chrome-headless":
                WebDriverManager.chromedriver().setup();

                Map<String, Object> headlessChromePrefs = new HashMap<>();
                headlessChromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
                headlessChromePrefs.put("profile.default_content_setting_values.notifications", 2);
                headlessChromePrefs.put("profile.default_content_settings.popups", 0);
                headlessChromePrefs.put("download.default_directory", Constants.DOWNLOAD_FOLDER);
                headlessChromePrefs.put("download.prompt_for_download", false);
                headlessChromePrefs.put("download.directory_upgrade", true);
                headlessChromePrefs.putAll(customPrefs);

                ChromeOptions headlessOptions = new ChromeOptions();
                headlessOptions.setExperimentalOption("prefs", headlessChromePrefs);

                headlessOptions.addArguments("--headless=new");
                headlessOptions.addArguments("--no-sandbox");
                headlessOptions.addArguments("--disable-dev-shm-usage");
                headlessOptions.addArguments("--window-size=1920,1080");

                driver.set(new ChromeDriver(headlessOptions));
                break;


            case "firefox-headless":
                WebDriverManager.firefoxdriver().setup();

                FirefoxOptions firefoxOptions = new FirefoxOptions();

                firefoxOptions.addArguments("-headless");
                firefoxOptions.addArguments("--width=1920");
                firefoxOptions.addArguments("--height=1080");

                firefoxOptions.addPreference("browser.download.folderList", 2);
                firefoxOptions.addPreference("browser.download.dir", Constants.DOWNLOAD_FOLDER);
                firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk",
                        "application/pdf,application/octet-stream");
                firefoxOptions.addPreference("browser.download.manager.showWhenStarting", false);
                firefoxOptions.addPreference("pdfjs.disabled", true);

                driver.set(new FirefoxDriver(firefoxOptions));
                break;


            default:
                throw new IllegalArgumentException(
                        "Valid browser names: chrome, chrome-headless, firefox-headless");
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        getDriver().manage().window().setSize(
                new org.openqa.selenium.Dimension(
                        (int) screenSize.getWidth(),
                        (int) screenSize.getHeight()
                )
        );
        getDriver().manage().deleteAllCookies();

        return getDriver();
    }

    public static void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}