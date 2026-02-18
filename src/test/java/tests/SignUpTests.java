package tests;

import dataFactory.SignUpDF;
import dataFactory.UserDF;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.*;
import tests.base.BaseTest;

public class SignUpTests extends BaseTest {
    @Test
    public void VerifyThatSignUpSuccessfullWithNewUser() throws Exception
    {
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        CommonPage commonPage = new CommonPage(driver);
        loginSignupPage.signup(UserDF.fillValidUserSignUpDetails());
        signUpPage.createAccount(SignUpDF.fillSignUpDetails());
        Assert.assertTrue(accountCreatedPage.isAccountCreatedTextDisplayed(),"Account Created Text Not Displayed");
        Assert.assertTrue(accountCreatedPage.isCongratulationsTextDisplayed(),"Congratulations Text not Displayed");
        Assert.assertTrue(accountCreatedPage.isPrivilegesTextDisplayed(),"Privileges Text not Displayed");
        Assert.assertTrue(accountCreatedPage.isContinueButtonDisplayed(),"Continue Button Not Displayed");
        accountCreatedPage.clickOnContinueButton();
        Assert.assertTrue(commonPage.isHomePageLogoDisplayed(),"Home Page Logo Not Displayed");
        Assert.assertEquals(commonPage.getLoggedInUserName(),"Amitesh", "Logged In User Name Not Match");
        Assert.assertTrue(commonPage.isLogoutLinkDisplayed(),"Logout Link Not Displayed");
        Assert.assertTrue(commonPage.isDeleteAccountLinkDisplayed(),"Delete Account Link Not Displayed");
    }

    @Test
    public void VerifyThatSignUpFailedWithExistingUserEmail() throws Exception
    {
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        CommonPage commonPage = new CommonPage(driver);
        loginSignupPage.signup(UserDF.fillExistingUserSignUpDetails());
        Assert.assertEquals("Email Address already exist!", loginSignupPage.getSignUpErrorMessage(),"Sign Up should be failed with exisiting email");

    }
}
