package Functional.utilities;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Constants {

    public static final String PROPERTY_FILE = "src/main/resources/constants.properties";

    //Selenium constants
    public static final int WEBDRIVER_WAIT_DURATION = Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTY_FILE, "WebDriverWaitDuration"));
    public static final int PAGE_FACTORY_WAIT_DURATION = Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTY_FILE, "PageFactoryWaitDuration"));
    public static final String SCREENSHOT_LOCATION = JavaHelpers.getPropertyValue(PROPERTY_FILE, "ScreenshotLocation");
    public static final String LOGIN_PAGE_URL = JavaHelpers.getPropertyValue(PROPERTY_FILE, "LOGIN_PAGE_URL");

    //Path
    public static final String DOWNLOAD_FOLDER = System.getProperty("user.dir") + File.separator + "src\\main\\resources\\downloadFiles";
    public static final String UPLOAD_FOLDER = System.getProperty("user.dir") + File.separator + "src\\main\\resources\\uploadFiles";

    public static List<String> EXPECTED_PRODUCT_CATEGORIES = Arrays.asList("WOMEN","MEN", "KIDS");
    public static List<String> EXPECTED_BRANDS = Arrays.asList("POLO", "H&M" ,"MADAME", "MAST & HARBOUR", "BABYHUG" , "ALLEN SOLLY JUNIOR", "KOOKIE KIDS", "BIBA");

    public static Map<String, Integer> EXPECTED_BRANDS_COUNTS = Map.of(
            "Polo", 6,
            "H&M", 5,
            "Madame", 5,
            "Mast & Harbour", 3,
            "Babyhug", 4,
            "Allen Solly Junior", 3,
            "Kookie Kids", 3,
            "Biba", 5
    );

    public static Map<String, Integer> EXPECTED_SUBCATEGORIES_COUNT = Map.of(
            "Men", 2,
            "Women", 3,
            "Kids", 2
    );
    }
