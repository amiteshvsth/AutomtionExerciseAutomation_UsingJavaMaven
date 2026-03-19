package tests.Functional;

import Functional.dataFactory.SignUpDF;
import Functional.dataFactory.UserDF;
import Functional.dataObject.SignUpDO;
import Functional.dataObject.UserDO;
import Functional.enums.TopNavLinks;
import Functional.pageObject.*;
import Functional.utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

public class LoginTests extends BaseTest {

    @Test
    public void verifyThatLoginSuccessfulWithValidCredentials() throws InterruptedException {


        LoginSignupPage loginPage = new LoginSignupPage(driver);
        CommonPage commonPage = new CommonPage(driver);

        UserDO userDetails = UserDF.getData();
        userDetails.setEmail(Constants.EXISTING_EMAIL);

        step("Logging in with valid user credentials");
        loginPage.login(userDetails);

        step("Validating user is successfully logged in");
        Assert.assertTrue(commonPage.isHomePageLogoDisplayed(),
                "Home page logo is not displayed.");
        Assert.assertEquals(commonPage.getLoggedInUserName(),"Amitesh Vashishth", "Logged-in username does not match.");
        Assert.assertTrue(commonPage.isLogoutLinkDisplayed(),"Logout link is not displayed.");
        Assert.assertTrue(commonPage.isDeleteAccountLinkDisplayed(),"Delete account link is not displayed.");

    }


    @Test
    public void verifyThatLoginFailsWithInvalidCredentials() throws InterruptedException {

        LoginSignupPage loginPage = new LoginSignupPage(driver);

        step("Attempting login with invalid credentials");
        loginPage.login(UserDF.getData());

        step("Validating error message is displayed");
        Assert.assertEquals(loginPage.getLoginErrorMessage(), "Your email or password is incorrect!", "Expected error message is not displayed.");

    }


    @Test
    public void verifyThatLogoutSuccessful() throws InterruptedException {

        LoginSignupPage loginPage = new LoginSignupPage(driver);
        CommonPage commonPage = new CommonPage(driver);

        UserDO userData = UserDF.getData();
        userData.setEmail(Constants.EXISTING_EMAIL);
        userData.setPassword(Constants.CONSTANT_PASSWORD);
        step("Logging in with valid credentials");
        loginPage.login(userData);

        step("Performing logout action");
        loginPage.clickOnTopNavLink(TopNavLinks.LOGOUT_USER);

        step("Validating user is logged out");
        Assert.assertFalse(commonPage.isLogoutLinkDisplayed(),
                "Logout link should not be displayed after logout.");
        Assert.assertFalse(commonPage.isDeleteAccountLinkDisplayed(),
                "Delete account link should not be displayed after logout.");

    }


    @Test
    public void verifyThatAccountDeletedSuccessfully() throws InterruptedException {

        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        HomePage homePage = new HomePage(driver);
        AccountDeletedPage accountDeletedPage = new AccountDeletedPage(driver);

        step("Signing up with valid user details");
        UserDO userDetails = UserDF.getData();
        loginSignupPage.signup(userDetails);

        step("Completing account registration");
        SignUpDO signupDetails = SignUpDF.getData();
        signUpPage.createAccount(signupDetails);

        step("Validating account creation confirmation");
        Assert.assertTrue(accountCreatedPage.isAccountCreatedTextDisplayed(),
                "Account created text is not displayed.");
        Assert.assertTrue(accountCreatedPage.isCongratulationsTextDisplayed(),
                "Congratulations text is not displayed.");
        Assert.assertTrue(accountCreatedPage.isPrivilegesTextDisplayed(),
                "Privileges text is not displayed.");
        Assert.assertTrue(accountCreatedPage.isContinueButtonDisplayed(),
                "Continue button is not displayed.");

        accountCreatedPage.clickOnContinueButton();

        step("Validating user is logged in after account creation");
        Assert.assertTrue(homePage.isHomePageLogoDisplayed(),
                "Home page logo is not displayed.");
        Assert.assertEquals(homePage.getLoggedInUserName(),
                signupDetails.getFirstName(),
                "Logged-in username does not match.");
        Assert.assertTrue(homePage.isLogoutLinkDisplayed(),
                "Logout link is not displayed.");
        Assert.assertTrue(homePage.isDeleteAccountLinkDisplayed(),
                "Delete account link is not displayed.");

        step("Deleting the newly created account");
        homePage.deleteUserAccount();

        step("Validating account deletion confirmation");
        Assert.assertTrue(accountDeletedPage.isAccountDeletedTextDisplayed(),
                "Account deleted text is not displayed.");
        Assert.assertTrue(accountDeletedPage.isPermanentlyDeletedTextDisplayed(),
                "Permanently deleted text is not displayed.");
        Assert.assertTrue(accountDeletedPage.isContinueButtonDisplayed(),
                "Continue button is not displayed.");
        Assert.assertTrue(accountDeletedPage.isPrivilegesTextDisplayed(),
                "Privileges text is not displayed.");

        accountDeletedPage.clickOnContinueButton();

        step("Validating user is logged out after deletion");
        Assert.assertFalse(homePage.isLogoutLinkDisplayed(),
                "Logout link should not be displayed.");
        Assert.assertFalse(homePage.isDeleteAccountLinkDisplayed(),
                "Delete account link should not be displayed.");

        step("Attempting login with deleted account credentials");
        homePage.clickOnTopNavLink(TopNavLinks.LOGIN_PAGE);
        loginSignupPage.login(userDetails);

        Assert.assertEquals(loginSignupPage.getLoginErrorMessage(),
                "Your email or password is incorrect!",
                "Expected error message is not displayed for deleted account.");

    }
}