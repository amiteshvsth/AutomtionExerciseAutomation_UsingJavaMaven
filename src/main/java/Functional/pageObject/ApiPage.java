package Functional.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ApiPage extends CommonPage {

    public ApiPage(WebDriver driver){
        super(driver);
    }

    private final By apiHeader = By.xpath("//h2/b[contains(text(),'APIs List')]");
    private final By apiDescription = By.xpath("//span[text()='Below is the list of APIs for you to practice the API testing in Automation. Click on the scenario for detailed API:']");
    private final By feedbackHeader = By.xpath("//a[@href='#feedback']");
    private final By feedbackDescriptionLine1 = By.xpath("//div[@id='feedback']/ul/li[text()= 'We have identified above scenarios and added in the list.']");
    private final By feedbackDescriptionLine2 = By.xpath("//div[@id='feedback']/ul/li[text()= 'You can explore more test cases in the website and if you find new test scenario that is not covered in above list, do let us know. We will definitely add that in above list.']");
    private final By feedbackDescriptionLine3 = By.xpath("//div[@id='feedback']/ul/li[text()= 'If you think, this website should cover up any particular feature, kindly share with us at ']");
    private final By apiList = By.xpath("//u[contains(text(),'API')]");

    public boolean isApiHeaderDisplayed(){return selenium.isElementPresent(apiHeader);}
    public boolean isApiDescriptionDisplayed(){
        return selenium.isElementPresent(apiDescription);
    }
    public boolean isFeedbackHeaderDisplayed(){
        return selenium.isElementPresent(feedbackHeader);
    }
    public boolean isFeedbackDescriptionLine1Displayed(){
        return selenium.isElementPresent(feedbackDescriptionLine1);
    }
    public boolean isFeedbackDescriptionLine2Displayed(){
        return selenium.isElementPresent(feedbackDescriptionLine2);
    }
    public boolean isFeedbackDescriptionLine3Displayed(){
        return selenium.isElementPresent(feedbackDescriptionLine3);
    }
    public int getApiCount(){return selenium.findElements(apiList).stream().toList().size();}
}

