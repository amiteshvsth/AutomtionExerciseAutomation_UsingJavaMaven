package tests.Functional;

import Functional.enums.TopNavLinks;
import Functional.pageObject.ApiPage;
import Functional.pageObject.LoginSignupPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

public class ApiTestingPageTests extends BaseTest {

    @Test
    public void verifySmokeTestsForApiTestingPage() throws InterruptedException {

        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        ApiPage apiPage = new ApiPage(driver);

        step("Navigating to API Testing page");
        loginSignupPage.clickOnTopNavLink(TopNavLinks.API_LIST_PAGE);

        step("Validating API header is displayed");
        Assert.assertTrue(apiPage.isApiHeaderDisplayed(),
                "API header is not displayed.");

        step("Validating API description is displayed");
        Assert.assertTrue(apiPage.isApiDescriptionDisplayed(),
                "API description is not displayed.");

        step("Validating Feedback section header is displayed");
        Assert.assertTrue(apiPage.isFeedbackHeaderDisplayed(),
                "Feedback header is not displayed.");

        step("Validating Feedback description lines are displayed");
        Assert.assertTrue(apiPage.isFeedbackDescriptionLine1Displayed(),
                "Feedback description line 1 is not displayed.");
        Assert.assertTrue(apiPage.isFeedbackDescriptionLine2Displayed(),
                "Feedback description line 2 is not displayed.");
        Assert.assertTrue(apiPage.isFeedbackDescriptionLine3Displayed(),
                "Feedback description line 3 is not displayed.");

        step("Validating total API count displayed on page");
        Assert.assertEquals(apiPage.getApiCount(), 14,
                "API count displayed is incorrect.");
    }
}