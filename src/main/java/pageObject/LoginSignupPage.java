package pageObject;

import dataObject.UserDO;
import pageObject.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginSignupPage extends BasePage {
    public LoginSignupPage(WebDriver driver){
        super(driver);
    }


    private final By loginEmail = By.cssSelector("[data-qa='login-email']");
    private final By loginPassword = By.cssSelector("[data-qa='login-password']");
    private final By loginBtn = By.cssSelector("[data-qa='login-button']");

    private final By signupName = By.cssSelector("[data-qa='signup-name']");
    private final By signupEmail = By.cssSelector("[data-qa='signup-email']");
    private final By signupBtn = By.cssSelector("[data-qa='signup-button']");


    public void login(UserDO user) throws InterruptedException {
        selenium.enterText(loginEmail,user.getEmail(),true);
        selenium.enterText(loginPassword,user.getPassword(),true);
        selenium.clickOn(loginBtn);
    }

    public void signup(UserDO user) throws InterruptedException {
        selenium.enterText(signupEmail,user.getEmail(),true);
        selenium.enterText(signupName,user.getPassword(),true);
        selenium.clickOn(signupBtn);
    }
}