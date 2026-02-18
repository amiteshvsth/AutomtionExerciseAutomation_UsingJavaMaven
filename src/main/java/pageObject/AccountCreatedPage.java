package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends CommonPage {
    public AccountCreatedPage(WebDriver driver){ super(driver);  }

    private final By accountCreatedText = By.xpath("//h2[@data-qa='account-created']/b[text()='Account Created!']");
    private final By congratulationsText = By.xpath("//p[text()='Congratulations! Your new account has been successfully created!']");
    private final By privilegesText = By.xpath("//p[text()='You can now take advantage of member privileges to enhance your online shopping experience with us.']");
    private final By continueButton = By.cssSelector("[data-qa='continue-button']");


    public boolean isAccountCreatedTextDisplayed(){
        return selenium.isElementPresent(accountCreatedText);
    }

    public boolean isCongratulationsTextDisplayed(){
        return selenium.isElementPresent(congratulationsText);
    }

    public boolean isPrivilegesTextDisplayed(){
        return selenium.isElementPresent(privilegesText);
    }

    public boolean isContinueButtonDisplayed(){
        return selenium.isElementPresent(continueButton);
    }

    public void clickOnContinueButton() throws InterruptedException {
        selenium.clickOn(continueButton);
    }
}
