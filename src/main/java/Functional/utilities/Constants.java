package Functional.utilities;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Constants {

    public static final String PROPERTY_FILE = "src/main/resources/constants.properties";

    //Selenium constants
    public static final int WEBDRIVER_WAIT_DURATION = Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTY_FILE, "WebDriverWaitDuration"));
    public static final int PAGEFACTORY_WAIT_DURATION = Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTY_FILE, "PageFactoryWaitDuration"));
    public static final String SCREENSHOT_LOCATION = JavaHelpers.getPropertyValue(PROPERTY_FILE, "ScreenshotLocation");
    public static final String EXTENT_REPORT = System.getProperty("user.dir") + File.separator + "ExtentReport";
    public static final String LOGIN_PAGE_URL = JavaHelpers.getPropertyValue(PROPERTY_FILE, "loginPageUrl");

    //Path
    public static final String DOWNLOAD_FOLDER = System.getProperty("user.dir") + File.separator + "src\\main\\resources\\downloadFiles";
    public static final String UPLOAD_FOLDER = System.getProperty("user.dir") + File.separator + "src\\main\\resources\\uploadFiles";

    public static List<String> expectedProductCategories = Arrays.asList("WOMEN","MEN", "KIDS");
    public static List<String> expectedBrands = Arrays.asList("POLO", "H&M" ,"MADAME", "MAST & HARBOUR", "BABYHUG" , "ALLEN SOLLY JUNIOR", "KOOKIE KIDS", "BIBA");

    public static Map<String, Integer> expectedBrandCounts = Map.of(
            "Polo", 6,
            "H&M", 5,
            "Madame", 5,
            "Mast & Harbour", 3,
            "Babyhug", 4,
            "Allen Solly Junior", 3,
            "Kookie Kids", 3,
            "Biba", 5
    );

    public static Map<String, Integer> expectedSubCategoriesCounts = Map.of(
            "Men", 2,
            "Women", 3,
            "Kids", 2
    );
    }
