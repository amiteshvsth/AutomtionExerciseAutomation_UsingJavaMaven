package tests.Functional;

import Functional.dataFactory.SignUpDF;
import Functional.dataFactory.UserDF;
import Functional.pageObject.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

public class LoginTests extends BaseTest {

    @Test
    public void verifyThatLoginSuccessfulWithValidCredentials() throws InterruptedException {

        test.info("===== TEST START: Verify login with valid credentials =====");

        LoginSignupPage loginPage = new LoginSignupPage(driver);
        CommonPage commonPage = new CommonPage(driver);

        test.info("Step 1: Logging in with valid user credentials");
        loginPage.login(UserDF.fillValidUserLoginDetails());

        test.info("Step 2: Validating user is successfully logged in");
        Assert.assertTrue(commonPage.isHomePageLogoDisplayed(),
                "Home page logo is not displayed.");
        Assert.assertEquals(commonPage.getLoggedInUserName(),
                "Amitesh Vashishth",
                "Logged-in username does not match.");
        Assert.assertTrue(commonPage.isLogoutLinkDisplayed(),
                "Logout link is not displayed.");
        Assert.assertTrue(commonPage.isDeleteAccountLinkDisplayed(),
                "Delete account link is not displayed.");

        test.info("===== TEST PASSED: Login successful with valid credentials =====");
    }


    @Test
    public void verifyThatLoginFailsWithInvalidCredentials() throws InterruptedException {

        test.info("===== TEST START: Verify login failure with invalid credentials =====");

        LoginSignupPage loginPage = new LoginSignupPage(driver);

        test.info("Step 1: Attempting login with invalid credentials");
        loginPage.login(UserDF.fillInvalidUserLoginDetails());

        test.info("Step 2: Validating error message is displayed");
        Assert.assertEquals(loginPage.getLoginErrorMessage(),
                "Your email or password is incorrect!",
                "Expected error message is not displayed.");

        test.info("===== TEST PASSED: Invalid login attempt correctly rejected =====");
    }


    @Test
    public void verifyThatLogoutSuccessful() throws InterruptedException {

        test.info("===== TEST START: Verify logout functionality =====");

        LoginSignupPage loginPage = new LoginSignupPage(driver);
        CommonPage commonPage = new CommonPage(driver);

        test.info("Step 1: Logging in with valid credentials");
        loginPage.login(UserDF.fillValidUserLoginDetails());

        test.info("Step 2: Performing logout action");
        loginPage.logoutUser();

        test.info("Step 3: Validating user is logged out");
        Assert.assertFalse(commonPage.isLogoutLinkDisplayed(),
                "Logout link should not be displayed after logout.");
        Assert.assertFalse(commonPage.isDeleteAccountLinkDisplayed(),
                "Delete account link should not be displayed after logout.");

        test.info("===== TEST PASSED: Logout functionality verified successfully =====");
    }


    @Test
    public void verifyThatAccountDeletedSuccessfully() throws InterruptedException {

        test.info("===== TEST START: Verify account creation and deletion workflow =====");

        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        HomePage homePage = new HomePage(driver);
        AccountDeletedPage accountDeletedPage = new AccountDeletedPage(driver);

        test.info("Step 1: Signing up with valid user details");
        var userDetails = UserDF.fillValidUserSignUpDetails();
        loginSignupPage.signup(userDetails);

        test.info("Step 2: Completing account registration");
        signUpPage.createAccount(SignUpDF.fillSignUpDetails());

        test.info("Step 3: Validating account creation confirmation");
        Assert.assertTrue(accountCreatedPage.isAccountCreatedTextDisplayed(),
                "Account created text is not displayed.");
        Assert.assertTrue(accountCreatedPage.isCongratulationsTextDisplayed(),
                "Congratulations text is not displayed.");
        Assert.assertTrue(accountCreatedPage.isPrivilegesTextDisplayed(),
                "Privileges text is not displayed.");
        Assert.assertTrue(accountCreatedPage.isContinueButtonDisplayed(),
                "Continue button is not displayed.");

        accountCreatedPage.clickOnContinueButton();

        test.info("Step 4: Validating user is logged in after account creation");
        Assert.assertTrue(homePage.isHomePageLogoDisplayed(),
                "Home page logo is not displayed.");
        Assert.assertEquals(homePage.getLoggedInUserName(),
                "Amitesh",
                "Logged-in username does not match.");
        Assert.assertTrue(homePage.isLogoutLinkDisplayed(),
                "Logout link is not displayed.");
        Assert.assertTrue(homePage.isDeleteAccountLinkDisplayed(),
                "Delete account link is not displayed.");

        test.info("Step 5: Deleting the newly created account");
        homePage.deleteUserAccount();

        test.info("Step 6: Validating account deletion confirmation");
        Assert.assertTrue(accountDeletedPage.isAccountDeletedTextDisplayed(),
                "Account deleted text is not displayed.");
        Assert.assertTrue(accountDeletedPage.isPermanentlyDeletedTextDisplayed(),
                "Permanently deleted text is not displayed.");
        Assert.assertTrue(accountDeletedPage.isContinueButtonDisplayed(),
                "Continue button is not displayed.");
        Assert.assertTrue(accountDeletedPage.isPrivilegesTextDisplayed(),
                "Privileges text is not displayed.");

        accountDeletedPage.clickOnContinueButton();

        test.info("Step 7: Validating user is logged out after deletion");
        Assert.assertFalse(homePage.isLogoutLinkDisplayed(),
                "Logout link should not be displayed.");
        Assert.assertFalse(homePage.isDeleteAccountLinkDisplayed(),
                "Delete account link should not be displayed.");

        test.info("Step 8: Attempting login with deleted account credentials");
        homePage.goToLoginPage();
        loginSignupPage.login(userDetails);

        Assert.assertEquals(loginSignupPage.getLoginErrorMessage(),
                "Your email or password is incorrect!",
                "Expected error message is not displayed for deleted account.");

        test.info("===== TEST PASSED: Account deletion workflow verified successfully =====");
    }
}