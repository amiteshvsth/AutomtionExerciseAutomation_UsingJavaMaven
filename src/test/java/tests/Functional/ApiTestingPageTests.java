package tests.Functional;

import Functional.pageObject.ApiPage;
import Functional.pageObject.LoginSignupPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

public class ApiTestingPageTests extends BaseTest {

    @Test
    public void verifySmokeTestsForApiTestingPage() throws InterruptedException {

        test.info("===== TEST START: Verify smoke test validations for API Testing page =====");

        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        ApiPage apiPage = new ApiPage(driver);

        test.info("Step 1: Navigating to API Testing page");
        loginSignupPage.goToApiTestingPage();

        test.info("Step 2: Validating API header is displayed");
        Assert.assertTrue(apiPage.isApiHeaderDisplayed(),
                "API header is not displayed.");

        test.info("Step 3: Validating API description is displayed");
        Assert.assertTrue(apiPage.isApiDescriptionDisplayed(),
                "API description is not displayed.");

        test.info("Step 4: Validating Feedback section header is displayed");
        Assert.assertTrue(apiPage.isFeedbackHeaderDisplayed(),
                "Feedback header is not displayed.");

        test.info("Step 5: Validating Feedback description lines are displayed");
        Assert.assertTrue(apiPage.isFeedbackDescriptionLine1Displayed(),
                "Feedback description line 1 is not displayed.");
        Assert.assertTrue(apiPage.isFeedbackDescriptionLine2Displayed(),
                "Feedback description line 2 is not displayed.");
        Assert.assertTrue(apiPage.isFeedbackDescriptionLine3Displayed(),
                "Feedback description line 3 is not displayed.");

        test.info("Step 6: Validating total API count displayed on page");
        Assert.assertEquals(apiPage.getApiCount(), 14,
                "API count displayed is incorrect.");

        test.info("===== TEST PASSED: API Testing page smoke validations successful =====");
    }
}