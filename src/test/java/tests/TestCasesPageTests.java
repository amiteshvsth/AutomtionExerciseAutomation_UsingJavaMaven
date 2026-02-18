package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.TestCasesPage;
import tests.base.BaseTest;

public class TestCasesPageTests extends BaseTest {

    @Test
    public void VerifyThatTestCasesAreDisplayed() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        TestCasesPage testCasesPage = new TestCasesPage(driver);
        homePage.goToTestCasesPage();
        Assert.assertTrue(testCasesPage.isTestCasesHeaderDisplayed(), "Test Cases Header is not displayed");
        Assert.assertEquals(testCasesPage.getTestCasesCount(), 26, "Test Cases Count does not match");
    }
}
