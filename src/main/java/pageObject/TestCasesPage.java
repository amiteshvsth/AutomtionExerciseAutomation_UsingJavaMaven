package pageObject;

import pageObject.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage extends BasePage {

    private final By testCasesHeader = By.xpath("//h2[contains(text(),'Test Cases')]");

    public TestCasesPage(WebDriver driver){
        super(driver);
    }

    public boolean isLoaded(){
        return selenium.isElementPresent(testCasesHeader);
    }
}
