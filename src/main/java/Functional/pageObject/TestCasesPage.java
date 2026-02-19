package Functional.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage extends CommonPage {

    private final By testCasesHeader = By.xpath("//h2/b[contains(text(),'Test Cases')]");
    private final By testCases = By.xpath("//h4[@class='panel-title']/a/u[contains(text(),'Test Case')]");

    public TestCasesPage(WebDriver driver){
        super(driver);
    }

    public boolean isTestCasesHeaderDisplayed(){
        return selenium.isElementPresent(testCasesHeader);
    }
    public int getTestCasesCount(){
        return selenium.findElements(testCases).size();
    }
}
