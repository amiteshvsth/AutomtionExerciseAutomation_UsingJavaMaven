package tests;

import dataFactory.SignUpDF;
import dataFactory.UserDF;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.*;
import tests.base.BaseTest;

@Slf4j
public class LoginTests extends BaseTest {

    @Test
    public void VerifyThatLoginSuccessfullWithValidCredentials() throws InterruptedException {
        LoginSignupPage loginPage = new LoginSignupPage(driver);
        CommonPage commonPage = new CommonPage(driver);
        log.info("Login tests started with valid credentials");
        loginPage.login(UserDF.fillValidUserLoginDetails());
        Assert.assertTrue(commonPage.isHomePageLogoDisplayed(),"Home page logo not displayed");
        Assert.assertEquals(commonPage.getLoggedInUserName(),"Amitesh Vashishth", "Logged In User Name Not Match");
        Assert.assertTrue(commonPage.isLogoutLinkDisplayed(),"Logout Link Not Displayed");
        Assert.assertTrue(commonPage.isDeleteAccountLinkDisplayed(),"Delete Account Link Not Displayed");
    }

    @Test
    public void VerifyThatLoginFailedWithInvalidCredentials() throws InterruptedException {
        LoginSignupPage loginPage = new LoginSignupPage(driver);
        log.info("Login tests started with  invalid credentials");
        loginPage.login(UserDF.fillInvalidUserLoginDetails());
        Assert.assertEquals(loginPage.getLoginErrorMessage(),"Your email or password is incorrect!","Error message not displayed");
    }

    @Test
    public void VerifyThatLogoutSuccessfull() throws InterruptedException {
        LoginSignupPage loginPage = new LoginSignupPage(driver);
        CommonPage commonPage = new CommonPage(driver);
        log.info("Logout tests started ");
        loginPage.login(UserDF.fillValidUserLoginDetails());
        loginPage.logoutUser();
        Assert.assertFalse(commonPage.isLogoutLinkDisplayed(),"Logout Link should not be Displayed");
        Assert.assertFalse(commonPage.isDeleteAccountLinkDisplayed(),"Delete Account Link should Not be Displayed");
    }

    @Test
    public void VerifyThatAccountDeletedSuccessfully() throws InterruptedException {
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        HomePage homePage = new HomePage(driver);
        AccountDeletedPage accountDeletedPage = new AccountDeletedPage(driver);

        var userDetails = UserDF.fillValidUserSignUpDetails();
        loginSignupPage.signup(userDetails);
        signUpPage.createAccount(SignUpDF.fillSignUpDetails());
        Assert.assertTrue(accountCreatedPage.isAccountCreatedTextDisplayed(),"Account Created Text Not Displayed");
        Assert.assertTrue(accountCreatedPage.isCongratulationsTextDisplayed(),"Congratulations Text not Displayed");
        Assert.assertTrue(accountCreatedPage.isPrivilegesTextDisplayed(),"Privileges Text not Displayed");
        Assert.assertTrue(accountCreatedPage.isContinueButtonDisplayed(),"Continue Button Not Displayed");
        accountCreatedPage.clickOnContinueButton();
        Assert.assertTrue(homePage.isHomePageLogoDisplayed(),"Home Page Logo Not Displayed");
        Assert.assertEquals(homePage.getLoggedInUserName(),"Amitesh", "Logged In User Name Not Match");
        Assert.assertTrue(homePage.isLogoutLinkDisplayed(),"Logout Link Not Displayed");
        Assert.assertTrue(homePage.isDeleteAccountLinkDisplayed(),"Delete Account Link Not Displayed");
        homePage.deleteUserAccount();

        Assert.assertTrue(accountDeletedPage.isAccountDeletedTextDisplayed(),"Account Deleted Text Not Displayed");
        Assert.assertTrue(accountDeletedPage.isPermanentlyDeletedTextDisplayed(),"Permanently Deleted Text Not Displayed");
        Assert.assertTrue(accountDeletedPage.isContinueButtonDisplayed(),"Continue Button Not Displayed");
        Assert.assertTrue(accountDeletedPage.isPrivilegesTextDisplayed(),"Privileges Text Not Displayed");
        accountDeletedPage.clickOnContinueButton();

        Assert.assertFalse(homePage.isLogoutLinkDisplayed(),"Logout Link Not Displayed");
        Assert.assertFalse(homePage.isDeleteAccountLinkDisplayed(),"Delete Account Link Not Displayed");
        homePage.goToLoginPage();
        loginSignupPage.login(userDetails);
        Assert.assertEquals(loginSignupPage.getLoginErrorMessage(),"Your email or password is incorrect!","Error message not displayed");
    }
}
