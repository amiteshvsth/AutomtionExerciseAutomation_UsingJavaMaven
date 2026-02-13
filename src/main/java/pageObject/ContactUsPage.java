package pageObject;

import dataObject.ContactUsDO;
import pageObject.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage {

    private final By name = By.cssSelector("[data-qa='name']");
    private final By email = By.cssSelector("[data-qa='email']");
    private final By subject = By.cssSelector("[data-qa='subject']");
    private final By message = By.cssSelector("[data-qa='message']");
    private final By submit = By.cssSelector("[data-qa='submit-button']");

    public ContactUsPage(WebDriver driver){
        super(driver);
    }

    public void submitForm(ContactUsDO data) throws InterruptedException {
        selenium.enterText(name, data.getName(),true);
        selenium.enterText(email, data.getEmail(),true);
        selenium.enterText(subject, data.getSubject(),true);
        selenium.enterText(message, data.getMessage(),true);
        selenium.clickOn(submit);
    }
}

