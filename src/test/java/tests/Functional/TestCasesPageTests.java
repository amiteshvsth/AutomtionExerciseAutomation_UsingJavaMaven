package tests.Functional;

import Functional.enums.TopNavLinks;
import Functional.pageObject.HomePage;
import Functional.pageObject.TestCasesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

public class TestCasesPageTests extends BaseTest {

    @Test
    public void verifyThatTestCasesAreDisplayed() throws InterruptedException {

        step("===== TEST START: Verify Test Cases page content =====");

        HomePage homePage = new HomePage(driver);
        TestCasesPage testCasesPage = new TestCasesPage(driver);

        step("Navigating to Test Cases page");
        homePage.clickOnTopNavLink(TopNavLinks.TEST_CASES_PAGE);

        step("Validating Test Cases header is displayed");
        Assert.assertTrue(testCasesPage.isTestCasesHeaderDisplayed(),
                "Test cases header is not displayed.");

        step("Validating total number of test cases");
        Assert.assertEquals(testCasesPage.getTestCasesCount(),
                26,
                "Test cases count mismatch.");

        step("===== TEST PASSED: Test Cases page validated successfully =====");
    }
}