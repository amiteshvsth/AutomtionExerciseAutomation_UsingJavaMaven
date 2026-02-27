package tests.Functional;

import Functional.pageObject.HomePage;
import Functional.pageObject.LoginSignupPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

public class HomePageTests extends BaseTest {

    @Test
    public void verifyHomePageSmokeTest() throws InterruptedException {

        test.info("===== TEST START: Verify Home Page smoke validations =====");

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        test.info("Step 1: Navigating to Home page");
        loginSignupPage.goToHomePage();

        test.info("Step 2: Validating primary header elements");
        Assert.assertTrue(homePage.isHomePageLogoDisplayed(), "Home page logo is not displayed.");
        Assert.assertTrue(homePage.isApiListButtonDisplayed(), "API list button is not displayed.");
        Assert.assertTrue(homePage.isTestCasesButtonDisplayed(), "Test cases button is not displayed.");

        test.info("Step 3: Validating carousel and product section");
        Assert.assertTrue(homePage.isCarouselImageDisplayed(), "Carousel image is not displayed.");
        Assert.assertTrue(homePage.isProductDisplayed(), "Product section is not displayed.");
        Assert.assertTrue(homePage.isProductNameDisplayed(), "Product name is not displayed.");
        Assert.assertTrue(homePage.isProductImageDisplayed(), "Product image is not displayed.");
        Assert.assertTrue(homePage.isProductPriceDisplayed(), "Product price is not displayed.");
        Assert.assertTrue(homePage.isProductAddToCartButtonDisplayed(), "Add to cart button is not displayed.");

        test.info("Step 4: Validating product overlay details");
        Assert.assertTrue(homePage.isProductOverlayDisplayed(), "Product overlay is not displayed.");
        Assert.assertTrue(homePage.isOverlayPriceDisplayed(), "Overlay price is not displayed.");
        Assert.assertTrue(homePage.isOverlayNameDisplayed(), "Overlay name is not displayed.");
        Assert.assertTrue(homePage.isOverlayAddToCartButtonDisplayed(), "Overlay add to cart button is not displayed.");

        test.info("Step 5: Validating informational sections");
        Assert.assertTrue(homePage.isAutomationHeaderDisplayed(), "Automation header is not displayed.");
        Assert.assertTrue(homePage.isAutomationDescriptionDisplayed(), "Automation description is not displayed.");
        Assert.assertTrue(homePage.isAutomationTagLineDisplayed(), "Automation tagline is not displayed.");
        Assert.assertTrue(homePage.isCategoryHeaderDisplayed(), "Category header is not displayed.");
        Assert.assertTrue(homePage.isBrandsHeaderDisplayed(), "Brands header is not displayed.");
        Assert.assertTrue(homePage.isFeaturedItemsHeaderDisplayed(), "Featured items header is not displayed.");

        test.info("===== TEST PASSED: Home page smoke validations successful =====");
    }


    @Test
    public void verifySubscribeEmailInHomePage() throws InterruptedException {

        test.info("===== TEST START: Verify subscription functionality on Home page =====");

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        test.info("Step 1: Navigating to Home page");
        loginSignupPage.goToHomePage();

        test.info("Step 2: Validating subscription section elements");
        Assert.assertTrue(homePage.isSubscriptionFormDisplayed(), "Subscription form is not displayed.");
        Assert.assertTrue(homePage.isSubscriptionEmailInputDisplayed(), "Subscription email input is not displayed.");
        Assert.assertTrue(homePage.isEmailSubmitIconDisplayed(), "Email submit icon is not displayed.");
        Assert.assertTrue(homePage.isFooterTextDisplayed(), "Footer text is not displayed.");
        Assert.assertTrue(homePage.isCopyrightTextDisplayed(), "Copyright text is not displayed.");
        Assert.assertTrue(homePage.isScrollToTopIconDisplayed(), "Scroll to top icon is not displayed.");

        test.info("Step 3: Submitting subscription email form");
        homePage.submitSubscribeEmailForm();

        test.info("Step 4: Validating subscription success message");
        Assert.assertTrue(homePage.isSuccessMessageDisplayed(),
                "Subscription success message is not displayed.");

        test.info("===== TEST PASSED: Subscription functionality verified successfully =====");
    }


    @Test
    public void verifyScrollToTopIconInHomePage() throws InterruptedException {

        test.info("===== TEST START: Verify scroll-to-top functionality =====");

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        test.info("Step 1: Navigating to Home page");
        loginSignupPage.goToHomePage();

        test.info("Step 2: Validating subscription section is visible before scrolling");
        Assert.assertTrue(homePage.isSubscriptionFormDisplayed(),
                "Subscription section is not displayed.");

        test.info("Step 3: Clicking scroll-to-top icon");
        homePage.clickOnScrollToTopIcon();

        test.info("Step 4: Validating page scrolled to top");
        Assert.assertTrue(homePage.isHomePageLogoDisplayed(),
                "Home page logo is not visible after scrolling to top.");

        test.info("===== TEST PASSED: Scroll-to-top functionality verified successfully =====");
    }


    @Test
    public void verifyNavigationToVideoTutorialsPage() throws InterruptedException {

        test.info("===== TEST START: Verify navigation to Video Tutorials page =====");

        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        test.info("Step 1: Navigating to Video Tutorials page");
        loginSignupPage.goToVideoTutorialsPage();

        test.info("Step 2: Validating URL of Video Tutorials page");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("AutomationExercise"),
                "Video Tutorials page URL is incorrect.");

        test.info("===== TEST PASSED: Navigation to Video Tutorials page successful =====");
    }
}