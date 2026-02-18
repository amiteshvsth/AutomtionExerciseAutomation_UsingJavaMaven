package pageObject;

import dataObject.ContactUsDO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends CommonPage {

    private final By name = By.cssSelector("[data-qa='name']");
    private final By email = By.cssSelector("[data-qa='email']");
    private final By subject = By.cssSelector("[data-qa='subject']");
    private final By message = By.cssSelector("[data-qa='message']");
    private final By submit = By.name("submit");
    private final By uploadFile = By.name("upload_file");
    private final By feedBackHeader = By.xpath("//div[@class='contact-info']/h2[@class='title text-center']");
    private final By feedbackDescription = By.xpath("//p[text()='We really appreciate your response to our website.']");
    private final By feedbackEmail = By.xpath("//p[normalize-space()='Kindly share your feedback with us at feedback@automationexercise.com.']");
    private final By suggestionText = By.xpath("//p[text()='If you have any suggestion areas or improvements, do let us know. We will definitely work on it.']");
    private final By thankyouText = By.xpath("//p[text()='Thank you']");
    private final By contactUsHeader = By.xpath("//div[@class='bg']//div/h2");
    private final By note = By.xpath("//div[@class='contact-form']/div/b");
    private final By getInTouchText = By.xpath("//h2[text()='Get In Touch']");
    private final By successText = By.cssSelector(".status.alert.alert-success");
    private final By homeButton = By.xpath("//i[@class='fa fa-angle-double-left']/parent::span");

    public ContactUsPage(WebDriver driver){
        super(driver);
    }

    public void submitForm(ContactUsDO data) throws InterruptedException {
        selenium.enterText(name, data.getName(),true);
        selenium.enterText(email, data.getEmail(),true);
        selenium.enterText(subject, data.getSubject(),true);
        selenium.enterText(message, data.getMessage(),true);
        selenium.clickOn(submit);
        selenium.acceptAlert();
    }

    public void clickOnSubmitButton() throws InterruptedException {
        selenium.clickOn(submit);
        wait(5000);
    }

    public boolean isfileUploadOptionVisible(){
        return selenium.isElementPresent(uploadFile);
    }

    public boolean isFeedbackHeadervisible(){
        return selenium.isElementPresent(feedBackHeader);
    }

    public boolean isFeedbackDescriptionvisible(){
        return selenium.isElementPresent(feedbackDescription);
    }

    public boolean isFeedbackEmailvisible(){
        return selenium.isElementPresent(feedbackEmail);
    }

    public boolean isSuggestionTextvisible(){
        return selenium.isElementPresent(suggestionText);
    }

    public boolean isThankyouTextvisible(){
        return selenium.isElementPresent(thankyouText);
    }

    public boolean isContactUsHeadervisible(){
        return selenium.isElementPresent(contactUsHeader);
    }

    public boolean isNotevisible(){
        return selenium.isElementPresent(note);
    }

    public boolean isGetInTouchTextvisible(){
        return selenium.isElementPresent(getInTouchText);
    }

    public String getSuccessText(){
        return selenium.getText(successText);
    }

    public boolean isHomeButtonvisible(){
        return selenium.isElementPresent(homeButton);
    }

    public void clickOnHomeButton() throws InterruptedException {
        selenium.clickOn(homeButton);
    }

}

