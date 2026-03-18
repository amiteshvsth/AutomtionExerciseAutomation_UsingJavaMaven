package tests.Functional;

import Functional.dataObject.UserDO;
import Functional.dataFactory.SignUpDF;
import Functional.dataFactory.UserDF;
import Functional.pageObject.AccountCreatedPage;
import Functional.pageObject.CommonPage;
import Functional.pageObject.LoginSignupPage;
import Functional.pageObject.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

public class SignUpTests extends BaseTest {

    @Test
    public void verifyThatSignUpSuccessfulWithNewUser() throws Exception {

        step("===== TEST START: Verify successful sign-up with new user =====");

        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        CommonPage commonPage = new CommonPage(driver);

        step("Initiating sign-up with valid new user details");
        loginSignupPage.signup(UserDF.getData());

        step("Completing account registration form");
        signUpPage.createAccount(SignUpDF.getData());

        step("Validating account creation confirmation page");
        Assert.assertTrue(accountCreatedPage.isAccountCreatedTextDisplayed(),
                "Account created text is not displayed.");
        Assert.assertTrue(accountCreatedPage.isCongratulationsTextDisplayed(),
                "Congratulations text is not displayed.");
        Assert.assertTrue(accountCreatedPage.isPrivilegesTextDisplayed(),
                "Privileges text is not displayed.");
        Assert.assertTrue(accountCreatedPage.isContinueButtonDisplayed(),
                "Continue button is not displayed.");

        step("Clicking continue and validating user is logged in");
        accountCreatedPage.clickOnContinueButton();

        Assert.assertTrue(commonPage.isHomePageLogoDisplayed(),
                "Home page logo is not displayed.");
        Assert.assertEquals(commonPage.getLoggedInUserName(),
                "Amitesh",
                "Logged-in username mismatch.");
        Assert.assertTrue(commonPage.isLogoutLinkDisplayed(),
                "Logout link is not displayed.");
        Assert.assertTrue(commonPage.isDeleteAccountLinkDisplayed(),
                "Delete account link is not displayed.");

        step("===== TEST PASSED: New user sign-up verified successfully =====");
    }


    @Test
    public void verifyThatSignUpFailsWithExistingUserEmail() throws Exception {

        step("===== TEST START: Verify sign-up fails with existing email =====");

        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        UserDO userData = UserDF.getData();
        userData.setEmail("amiteshvashishth@yopmail.com");

        step("Attempting sign-up with already registered email");
        loginSignupPage.signup(userData);

        step("Validating error message for existing email");
        Assert.assertEquals(loginSignupPage.getSignUpErrorMessage(),
                "Email Address already exist!",
                "Expected error message for existing email is not displayed.");

        step("===== TEST PASSED: Existing email correctly rejected during sign-up =====");
    }
}