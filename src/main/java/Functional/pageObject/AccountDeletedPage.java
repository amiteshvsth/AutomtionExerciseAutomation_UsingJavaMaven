package Functional.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage extends CommonPage {
    public AccountDeletedPage(WebDriver driver){ super(driver);  }

    private final By accountDeletedText = By.xpath("//h2[@data-qa='account-deleted']/b[text()='Account Deleted!']");
    private final By permanentlyDeletedText = By.xpath("//p[text()='Your account has been permanently deleted!']");
    private final By privilegesText = By.xpath("//p[text()='You can create new account to take advantage of member privileges to enhance your online shopping experience with us.']");
    private final By continueButton = By.cssSelector("[data-qa='continue-button']");


    public boolean isAccountDeletedTextDisplayed(){
        return selenium.isElementPresent(accountDeletedText);
    }

    public boolean isPermanentlyDeletedTextDisplayed(){
        return selenium.isElementPresent(permanentlyDeletedText);
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
