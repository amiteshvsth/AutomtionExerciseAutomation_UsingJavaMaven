package Functional.pageObject;

import Functional.dataObject.UserDO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginSignupPage extends CommonPage {
    public LoginSignupPage(WebDriver driver){
        super(driver);
    }


    private final By loginEmail = By.cssSelector("[data-qa='login-email']");
    private final By loginPassword = By.cssSelector("[data-qa='login-password']");
    private final By loginBtn = By.cssSelector("[data-qa='login-button']");

    private final By signupName = By.cssSelector("[data-qa='signup-name']");
    private final By signupEmail = By.cssSelector("[data-qa='signup-email']");
    private final By signupBtn = By.cssSelector("[data-qa='signup-button']");

    private final By loginErrorMessage = By.xpath("//p[text()='Your email or password is incorrect!']");
    private final By signUpErrorMessage = By.xpath("//p[text()='Email Address already exist!']");

    public HomePage login(UserDO user) throws InterruptedException {
        selenium.enterText(loginEmail,user.getEmail(),true);
        selenium.enterText(loginPassword,user.getPassword(),true);
        selenium.clickOn(loginBtn);
        return new HomePage(driver);
    }

    public void signup(UserDO user) throws InterruptedException {
        selenium.enterText(signupName,user.getName(),true);
        selenium.enterText(signupEmail,user.getEmail(),true);
        selenium.clickOn(signupBtn);
    }

    public String getLoginErrorMessage(){
        return selenium.getText(loginErrorMessage);
    }

    public String getSignUpErrorMessage(){
        return selenium.getText(signUpErrorMessage);
    }
}