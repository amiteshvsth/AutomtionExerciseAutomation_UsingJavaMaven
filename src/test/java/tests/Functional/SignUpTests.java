package tests.Functional;

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

        test.info("===== TEST START: Verify successful sign-up with new user =====");

        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        CommonPage commonPage = new CommonPage(driver);

        test.info("Step 1: Initiating sign-up with valid new user details");
        loginSignupPage.signup(UserDF.fillValidUserSignUpDetails());

        test.info("Step 2: Completing account registration form");
        signUpPage.createAccount(SignUpDF.fillSignUpDetails());

        test.info("Step 3: Validating account creation confirmation page");
        Assert.assertTrue(accountCreatedPage.isAccountCreatedTextDisplayed(),
                "Account created text is not displayed.");
        Assert.assertTrue(accountCreatedPage.isCongratulationsTextDisplayed(),
                "Congratulations text is not displayed.");
        Assert.assertTrue(accountCreatedPage.isPrivilegesTextDisplayed(),
                "Privileges text is not displayed.");
        Assert.assertTrue(accountCreatedPage.isContinueButtonDisplayed(),
                "Continue button is not displayed.");

        test.info("Step 4: Clicking continue and validating user is logged in");
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

        test.info("===== TEST PASSED: New user sign-up verified successfully =====");
    }


    @Test
    public void verifyThatSignUpFailsWithExistingUserEmail() throws Exception {

        test.info("===== TEST START: Verify sign-up fails with existing email =====");

        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        test.info("Step 1: Attempting sign-up with already registered email");
        loginSignupPage.signup(UserDF.fillExistingUserSignUpDetails());

        test.info("Step 2: Validating error message for existing email");
        Assert.assertEquals(loginSignupPage.getSignUpErrorMessage(),
                "Email Address already exist!",
                "Expected error message for existing email is not displayed.");

        test.info("===== TEST PASSED: Existing email correctly rejected during sign-up =====");
    }
}