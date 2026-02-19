package tests.Functional;

import org.testng.Assert;
import org.testng.annotations.Test;
import Functional.pageObject.HomePage;
import Functional.pageObject.TestCasesPage;
import tests.Functional.base.BaseTest;

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
