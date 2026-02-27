package tests.Functional;

import Functional.pageObject.HomePage;
import Functional.pageObject.TestCasesPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

public class TestCasesPageTests extends BaseTest {

    @Test
    public void verifyThatTestCasesAreDisplayed() throws InterruptedException {

        test.info("===== TEST START: Verify Test Cases page content =====");

        HomePage homePage = new HomePage(driver);
        TestCasesPage testCasesPage = new TestCasesPage(driver);

        test.info("Step 1: Navigating to Test Cases page");
        homePage.goToTestCasesPage();

        test.info("Step 2: Validating Test Cases header is displayed");
        Assert.assertTrue(testCasesPage.isTestCasesHeaderDisplayed(),
                "Test cases header is not displayed.");

        test.info("Step 3: Validating total number of test cases");
        Assert.assertEquals(testCasesPage.getTestCasesCount(),
                26,
                "Test cases count mismatch.");

        test.info("===== TEST PASSED: Test Cases page validated successfully =====");
    }
}