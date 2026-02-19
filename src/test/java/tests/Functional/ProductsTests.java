package tests.Functional;

import Functional.dataFactory.CardDetailsDF;
import Functional.dataFactory.ReviewDF;
import Functional.dataFactory.UserDF;
import Functional.dataObject.ProductDetailsDO;
import Functional.dataObject.ReviewDO;
import Functional.pageObject.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

import java.util.List;

import static Functional.utilities.Constants.*;

public class ProductsTests extends BaseTest {

    @Test
    public void VerifyThatProductsPageLoadedSuccessfully() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        homePage.goToProductsPage();
        List<String> actualCategories = productsPage.getProductCategories();
        List<String> actualBrands = productsPage.getBrandNames();
        Assert.assertTrue(productsPage.isSaleImageDisplayed(), "SaleImage is not displayed");
        Assert.assertEquals(productsPage.getProductCategoriesCount(),3, "ProductCategories count is not correct.");

        Assert.assertEquals(actualCategories, expectedProductCategories,"ProductCategories are not correct.");
        Assert.assertEquals(actualBrands, expectedBrands, "Brands are not correct.");
        Assert.assertEquals(productsPage.getBrandsCount(),8, "Brands count is not correct.");
        Assert.assertTrue(productsPage.isProductsHeaderDisplayed(),"Products Header is not Displayed");
    }

    @Test
    public void VerifyThatSearchFunctionalityIsWorkingForProductsPage() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        homePage.goToProductsPage();
        productsPage.searchProduct("Blue Top");
        List<String> productNames = productsPage.getProductNames();
        Assert.assertEquals(productNames.stream().count(),1,"Product name is not correct.");
        Assert.assertEquals(productNames.getFirst(),"Blue Top","Product name is not correct.");
    }

    @Test
    public void VerifyThatProductCountIsCorrectForEachBrand() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        homePage.goToProductsPage();
        for (String brand : expectedBrandCounts.keySet()) {
            productsPage.clickOnBrandName(brand);
            int actualCount = productsPage.getProductNames().size();
            int expectedCount = expectedBrandCounts.get(brand);
            String actualProductHeaderText = productsPage.getProductsHeaderText();
            String expectedProductsHeaderText = "BRAND - "+brand.toUpperCase()+" PRODUCTS";
            String actualBrandNameInBreadcrumb = productsPage.getCategoryAndBrandNameInBreadCrumb();
            Assert.assertEquals(actualBrandNameInBreadcrumb,brand,"Brand Name does not match in breadcrumb");
            Assert.assertEquals(actualProductHeaderText,expectedProductsHeaderText,"Product Header are not correct.");
            Assert.assertEquals(actualCount, expectedCount,"Product count mismatch for brand: " + brand);
        }
    }

    @Test
    public void VerifyThatProductSubCategoriesCountIsCorrect() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        homePage.goToProductsPage();
        for (String category : expectedSubCategoriesCounts.keySet()) {
            int actualSubCategories = productsPage.getProductSubCategories(category).size();
            int expectedSubCategoriesCount = expectedSubCategoriesCounts.get(category);
            Assert.assertEquals(actualSubCategories, expectedSubCategoriesCount,"SubCategories count mismatch for category: " + category);
        }
    }

    @Test
    public void VerifyThatProductDetailsAreDisplayedOnProductDetailsPage() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        homePage.goToProductsPage();
        productsPage.clickViewProduct(1);
        ProductDetailsDO productDetails = productDetailPage.getProductDetails();
        Assert.assertEquals(productDetails.getName(),"Men Tshirt","Product name is not correct.");
        Assert.assertEquals(productDetails.getCategory(),"Category: Men > Tshirts","Product name is not correct.");
        Assert.assertEquals(productDetails.getPrice(),"Rs. 400","Product name is not correct.");
        Assert.assertEquals(productDetails.getAvailability(),"Availability: In Stock","Product name is not correct.");
        Assert.assertEquals(productDetails.getCondition(),"Condition: New","Product name is not correct.");
        Assert.assertEquals(productDetails.getBrand(),"Brand: H&M","Product name is not correct.");
    }

    @Test
    public void VerifyThatWeAreAbleToGiveReviewForProducts() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        homePage.goToProductsPage();
        productsPage.clickViewProduct(1);
        ReviewDO reviewDO = ReviewDF.fillReviewDetails();
        productDetailPage.submitReview(reviewDO);
        Assert.assertTrue(productDetailPage.isReviewSuccessAlertDisplayed(),"Success message is not displayed");
    }

    @Test
    public void VerifyEndToEndProductCheckoutFunctionality() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginPage = new LoginSignupPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage  checkoutPage = new CheckoutPage(driver);
        CardDetailsPage cardDetailsPage = new CardDetailsPage(driver);
        OrderCompletePage orderCompletePage = new OrderCompletePage(driver);
        homePage.goToProductsPage();
        productsPage.clickViewProduct(1);
        productDetailPage.clickAddToCart();
        productDetailPage.clickContinueShopping();
        productsPage.goToCartPage();
        Assert.assertTrue(cartPage.isCartTableDisplayed(),"Cart table is not displayed.");
        Assert.assertTrue(cartPage.isProductImageDisplayed(),"Product image is not displayed.");
        cartPage.clickProductDetails();
        ProductDetailsDO productDetails = productDetailPage.getProductDetails();
        Assert.assertEquals(productDetails.getName(),"Men Tshirt","Product name is not correct.");
        Assert.assertEquals(productDetails.getCategory(),"Category: Men > Tshirts","Product name is not correct.");
        Assert.assertEquals(productDetails.getPrice(),"Rs. 400","Product name is not correct.");
        Assert.assertEquals(productDetails.getAvailability(),"Availability: In Stock","Product name is not correct.");
        Assert.assertEquals(productDetails.getCondition(),"Condition: New","Product name is not correct.");
        Assert.assertEquals(productDetails.getBrand(),"Brand: H&M","Product name is not correct.");
        selenium.navigateToBackPage();
        cartPage.clickProceedToCheckout();
        Assert.assertTrue(cartPage.isCheckoutModalDisplayed(),"Checkout Modal is not displayed.");
        cartPage.clickContinueOnCartButton();
        Assert.assertTrue(cartPage.isCartTableDisplayed(),"Cart table is not displayed.");
        Assert.assertTrue(cartPage.isProductImageDisplayed(),"Product image is not displayed.");
        cartPage.clickDeleteProduct();
        Assert.assertTrue(cartPage.isEmptyCartMessageDisplayed(),"Empty Cart message is not displayed.");
        cartPage.clickBuyProductsLink();
        productsPage.clickViewProduct(1);
        productDetailPage.clickAddToCart();
        productDetailPage.clickContinueShopping();
        productsPage.goToCartPage();
        Assert.assertTrue(cartPage.isCartTableDisplayed(),"Cart table is not displayed.");
        Assert.assertTrue(cartPage.isProductImageDisplayed(),"Product image is not displayed.");
        cartPage.clickProceedToCheckout();
        Assert.assertTrue(cartPage.isCheckoutModalDisplayed(),"Checkout Modal is not displayed.");
        cartPage.clickRegisterLoginModalLink();

        loginPage.login(UserDF.fillValidUserLoginDetails());
        homePage.goToCartPage();
        Assert.assertTrue(cartPage.isCartTableDisplayed(),"Cart table is not displayed.");
        Assert.assertTrue(cartPage.isProductImageDisplayed(),"Product image is not displayed.");
        cartPage.clickProceedToCheckout();
        Assert.assertTrue(checkoutPage.isPlaceOrderButtonDisplayed(),"Place Order Button is not displayed.");
        Assert.assertEquals(checkoutPage.getOrderCommentLabel(),"If you would like to add a comment about your order, please write it in the field below.");
        Assert.assertTrue(checkoutPage.isOrderCommentTextareaDisplayed(),"Order comment text area is not displayed.");
        checkoutPage.setOrderComment("This is a order comment");
        Assert.assertEquals(checkoutPage.getProductTotalByIndex(1),"Rs. 400","Product total is not correct.");
        Assert.assertEquals(checkoutPage.getProductQuantityByIndex(1),"1","Product quantity is not correct.");
        Assert.assertEquals(checkoutPage.getProductPriceByIndex(1),"Rs. 400","Product price is not correct.");
        Assert.assertEquals(checkoutPage.getProductNameByIndex(1),"Men Tshirt","Product name is not correct.");
        Assert.assertEquals(checkoutPage.getNumberOfProductsInCart(),1,"Product rows is not correct.");
        Assert.assertEquals(checkoutPage.getTotalAmount(),"Rs. 400","Product total is not correct.");
        Assert.assertTrue(checkoutPage.isCartTableDisplayed(),"Cart table is not displayed.");
        Assert.assertTrue(checkoutPage.isReviewOrderHeadingDisplayed(),"Review Order heading is not displayed.");
        Assert.assertTrue(checkoutPage.isDeliveryAddressSectionDisplayed(),"Delivery Address Section is not displayed.");
        Assert.assertTrue(checkoutPage.isBillingAddressSectionDisplayed(),"Billing Address Section is not displayed.");
        Assert.assertTrue(checkoutPage.isAddressDetailsHeadingDisplayed(),"Address details heading is not displayed.");
        Assert.assertEquals(checkoutPage.getDeliveryAddressTitle(),"YOUR DELIVERY ADDRESS","Delivery Address title does not match");
        Assert.assertEquals(checkoutPage.getDeliveryAddressName(),"Mr. RwOnrAdfgd LzcZehGodfgd","Delivery Address Name does not match");
//        Assert.assertEquals(checkoutPage.getDeliveryAddressLine1(),"","Delivery Address Line 1 does not match");
        Assert.assertEquals(checkoutPage.getDeliveryAddressLine2(),"B4baqrMaklfgdf","Delivery Address Line 2 does not match");
        Assert.assertEquals(checkoutPage.getDeliveryAddressLine3(),"HmjlptLd8hsdfg","Delivery Address Line 3 does not match");
        Assert.assertEquals(checkoutPage.getDeliveryAddressCityStateZip(),"dafIOsdfg AUMyJsgdf 993576sdf","Delivery Address city state zip does not match");
        Assert.assertEquals(checkoutPage.getDeliveryAddressCountry(),"India","Delivery Address country does not match");
        Assert.assertEquals(checkoutPage.getDeliveryAddressPhone(),"932552526","Delivery Address phone does not match");
        Assert.assertEquals(checkoutPage.getBillingAddressTitle(),"YOUR BILLING ADDRESS","Billing Address title does not match");
        Assert.assertEquals(checkoutPage.getBillingAddressName(),"Mr. RwOnrAdfgd LzcZehGodfgd","Billing Address Name does not match");
//        Assert.assertEquals(checkoutPage.getBillingAddressLine1(),"","Billing Address Line 1 does not match");
        Assert.assertEquals(checkoutPage.getBillingAddressLine2(),"B4baqrMaklfgdf","Billing Address Line 2 does not match");
        Assert.assertEquals(checkoutPage.getBillingAddressLine3(),"HmjlptLd8hsdfg","Billing Address Line 3 does not match");
        Assert.assertEquals(checkoutPage.getBillingAddressCityStateZip(),"dafIOsdfg AUMyJsgdf 993576sdf","Billing Address City State Zip does not match");
        Assert.assertEquals(checkoutPage.getBillingAddressCountry(),"India","Billing Address Country does not match");
        Assert.assertEquals(checkoutPage.getBillingAddressPhone(),"932552526","Billing Address Phone does not match");
        checkoutPage.completeCheckout("Order Needs to Placed Immediately");
        cardDetailsPage.completePayment(CardDetailsDF.fillContactUsDetails());
        Assert.assertTrue(orderCompletePage.isOrderPlacedTitleDisplayed(),"Order Placed Title is not displayed.");
        Assert.assertEquals(orderCompletePage.getOrderPlacedTitle(),"ORDER PLACED!","Title does not match");
        Assert.assertEquals(orderCompletePage.getCongratulationsText(),"Congratulations! Your order has been confirmed!","Congratulations text does not match");
        orderCompletePage.clickDownloadInvoice();
//        Assert.assertTrue(orderCompletePage.isInvoiceDownloaded(),"Invoice is not downloaded");
        orderCompletePage.clickContinue();
        Assert.assertTrue(homePage.isHomePageLogoDisplayed(),"Home Page Logo is not displayed.");
        Assert.assertTrue(homePage.isLogoutLinkDisplayed(),"Logout Link is not displayed.");
        Assert.assertTrue(homePage.isDeleteAccountLinkDisplayed(),"Logout Link is not displayed.");
        Assert.assertEquals(homePage.getLoggedInUserName(),"Amitesh Vashishth","Username does not match");
    }
}
