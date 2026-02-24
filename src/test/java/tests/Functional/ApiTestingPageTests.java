package tests.Functional;

import Functional.pageObject.ApiPage;
import Functional.pageObject.LoginSignupPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

public class ApiTestingPageTests extends BaseTest {

    @Test
    public void VerifySmokeTestsForApiTestingPage() throws InterruptedException {
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        ApiPage  apiPage = new ApiPage(driver);
        loginSignupPage.goToApiTestingPage();
        Assert.assertTrue(apiPage.isApiHeaderDisplayed(),"Api Header is not displayed");
        Assert.assertTrue(apiPage.isApiDescriptionDisplayed(),"Api Description is not displayed");
        Assert.assertTrue(apiPage.isFeedbackHeaderDisplayed(),"Feedback Header is not displayed");
        Assert.assertTrue(apiPage.isFeedbackDescriptionLine1Displayed(),"Feedback Description Line 1 is not displayed");
        Assert.assertTrue(apiPage.isFeedbackDescriptionLine2Displayed(),"Feedback Description Line 2 is not displayed");
        Assert.assertTrue(apiPage.isFeedbackDescriptionLine3Displayed(),"Feedback Description Line 3 is not displayed");
        Assert.assertEquals(apiPage.getApiCount(),14,"Api Count is incorrect");

    }
}
