package pageObject;

import pageObject.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApiPage extends BasePage {

    private final By apiHeader = By.xpath("//h2[contains(text(),'APIs List')]");

    public ApiPage(WebDriver driver){
        super(driver);
    }

    public boolean isLoaded(){
        return selenium.isElementPresent(apiHeader);
    }
}

