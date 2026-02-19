package Functional.pageObject.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import Functional.utilities.Constants;
import Functional.utilities.JavaHelpers;
import Functional.utilities.SeleniumHelpers;

public class BasePage {
    protected WebDriver driver;
    protected SeleniumHelpers selenium;
    protected JavaHelpers javaHelpers;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        javaHelpers = new JavaHelpers();
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
    }
}
