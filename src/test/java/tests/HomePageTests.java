package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginSignupPage;
import tests.base.BaseTest;

public class HomePageTests extends BaseTest {

    @Test
    public void VerifyHomePageSmokeTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        loginSignupPage.goToHomePage();
        Assert.assertTrue(homePage.isHomePageLogoDisplayed(), "HomePage logo is not displayed");
        Assert.assertTrue(homePage.isApiListButtonDisplayed(), "Api List button is not displayed");
        Assert.assertTrue(homePage.isTestCasesButtonDisplayed(), "TestCases button is not displayed");
        Assert.assertTrue(homePage.isCarouselImageDisplayed(), "Carousel Image is not displayed");
        Assert.assertTrue(homePage.isProductDisplayed(), "Product is not displayed");
        Assert.assertTrue(homePage.isProductNameDisplayed(), "Product Name is not displayed");
        Assert.assertTrue(homePage.isProductImageDisplayed(), "Product Image is not displayed");
        Assert.assertTrue(homePage.isProductPriceDisplayed(), "Product Price is not displayed");
        Assert.assertTrue(homePage.isProductAddToCartButtonDisplayed(), "Add to Cart Button is not displayed");
        Assert.assertTrue(homePage.isProductOverlayDisplayed(), "Product overlay is not displayed");
        Assert.assertTrue(homePage.isOverlayPriceDisplayed(), "Overlay price is not displayed");
        Assert.assertTrue(homePage.isOverlayNameDisplayed(), "Overlay name is not displayed");
        Assert.assertTrue(homePage.isOverlayAddToCartButtonDisplayed(), "Overlay add to cart button is not displayed");
        Assert.assertTrue(homePage.isAutomationHeaderDisplayed(), "Automation Header is not displayed");
        Assert.assertTrue(homePage.isAutomationDescriptionDisplayed(), "Automation Description is not displayed");
        Assert.assertTrue(homePage.isAutomationTagLineDisplayed(), "Automation TagLine is not displayed");
        Assert.assertTrue(homePage.isCategoryHeaderDisplayed(), "Category Header is not displayed");
        Assert.assertTrue(homePage.isBrandsHeaderDisplayed(), "Brands Header is not displayed");
        Assert.assertTrue(homePage.isFeaturedItemsHeaderDisplayed(), "Featured Items header is not displayed");
    }

    @Test
    public void VerifySubscribeEmailInHomePage() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        loginSignupPage.goToHomePage();
        Assert.assertTrue(homePage.isSubscriptionFormDisplayed(), "Subscription Form is not displayed");
        Assert.assertTrue(homePage.isSubscriptionEmailInputDisplayed(), "Subscription Email Input is not displayed");
        Assert.assertTrue(homePage.isEmailSubmitButtonDisplayed(), "Email Submit Button is not displayed");
        Assert.assertTrue(homePage.isFooterTextDisplayed(), "Footer Text is not displayed");
        Assert.assertTrue(homePage.isCopyrightTextDisplayed(),"Copyright text is not displayed");
        Assert.assertTrue(homePage.isScrollToTopIconDisplayed(), "Scroll to Top is not displayed");
        homePage.submitSubscribeEmailForm();
        Assert.assertTrue(homePage.isSuccessMessageDisplayed(),"Success message is not displayed");
    }
}
