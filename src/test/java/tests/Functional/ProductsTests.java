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
    public void verifyThatProductsPageLoadedSuccessfully() throws InterruptedException {

        test.info("===== TEST START: Verify Products page loads successfully =====");

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        test.info("Step 1: Navigating to Products page");
        homePage.goToProductsPage();

        test.info("Step 2: Validating categories and brands");
        List<String> actualCategories = productsPage.getProductCategories();
        List<String> actualBrands = productsPage.getBrandNames();

        Assert.assertTrue(productsPage.isSaleImageDisplayed(), "Sale image is not displayed.");
        Assert.assertEquals(productsPage.getProductCategoriesCount(), 3, "Product categories count mismatch.");
        Assert.assertEquals(actualCategories, EXPECTED_PRODUCT_CATEGORIES, "Product categories mismatch.");
        Assert.assertEquals(actualBrands, EXPECTED_BRANDS, "Brand names mismatch.");
        Assert.assertEquals(productsPage.getBrandsCount(), 8, "Brand count mismatch.");
        Assert.assertTrue(productsPage.isProductsHeaderDisplayed(), "Products header is not displayed.");

        test.info("===== TEST PASSED: Products page loaded successfully =====");
    }


    @Test
    public void verifyThatSearchFunctionalityIsWorkingForProductsPage() throws InterruptedException {

        test.info("===== TEST START: Verify product search functionality =====");

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        homePage.goToProductsPage();

        test.info("Step 1: Searching for product 'Blue Top'");
        productsPage.searchProduct("Blue Top");

        test.info("Step 2: Validating search results");
        List<String> productNames = productsPage.getProductNames();

        Assert.assertEquals(productNames.size(), 1, "Unexpected number of search results.");
        Assert.assertEquals(productNames.getFirst(), "Blue Top", "Product name mismatch.");

        test.info("===== TEST PASSED: Product search verified successfully =====");
    }


    @Test
    public void verifyThatProductCountIsCorrectForEachBrand() throws InterruptedException {

        test.info("===== TEST START: Verify product count for each brand =====");

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        homePage.goToProductsPage();

        for (String brand : EXPECTED_BRANDS_COUNTS.keySet()) {

            test.info("Validating brand: "+ brand);

            productsPage.clickOnBrandName(brand);

            int actualCount = productsPage.getProductNames().size();
            int expectedCount = EXPECTED_BRANDS_COUNTS.get(brand);

            String expectedHeader = "BRAND - " + brand.toUpperCase() + " PRODUCTS";

            Assert.assertEquals(productsPage.getCategoryAndBrandNameInBreadCrumb(),
                    brand, "Brand name mismatch in breadcrumb.");
            Assert.assertEquals(productsPage.getProductsHeaderText(),
                    expectedHeader, "Products header mismatch.");
            Assert.assertEquals(actualCount, expectedCount,
                    "Product count mismatch for brand: " + brand);
        }

        test.info("===== TEST PASSED: Brand-wise product count verified =====");
    }


    @Test
    public void verifyThatProductSubCategoriesCountIsCorrect() throws InterruptedException {

        test.info("===== TEST START: Verify sub-category count for each category =====");

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        homePage.goToProductsPage();

        for (String category : EXPECTED_SUBCATEGORIES_COUNT.keySet()) {

            test.info("Validating sub-categories for category: "+ category);

            int actualSubCategories = productsPage.getProductSubCategories(category).size();
            int expectedSubCategoriesCount = EXPECTED_SUBCATEGORIES_COUNT.get(category);

            Assert.assertEquals(actualSubCategories, expectedSubCategoriesCount,
                    "Sub-category count mismatch for category: " + category);
        }

        test.info("===== TEST PASSED: Sub-category count validated successfully =====");
    }


    @Test
    public void verifyThatProductDetailsAreDisplayedOnProductDetailsPage() throws InterruptedException {

        test.info("===== TEST START: Verify product details page =====");

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);

        homePage.goToProductsPage();
        productsPage.clickViewProduct(1);

        test.info("Step 1: Fetching product details");
        ProductDetailsDO productDetails = productDetailPage.getProductDetails();

        Assert.assertEquals(productDetails.getName(), "Men Tshirt", "Product name mismatch.");
        Assert.assertEquals(productDetails.getCategory(), "Category: Men > Tshirts", "Category mismatch.");
        Assert.assertEquals(productDetails.getPrice(), "Rs. 400", "Price mismatch.");
        Assert.assertEquals(productDetails.getAvailability(), "Availability: In Stock", "Availability mismatch.");
        Assert.assertEquals(productDetails.getCondition(), "Condition: New", "Condition mismatch.");
        Assert.assertEquals(productDetails.getBrand(), "Brand: H&M", "Brand mismatch.");

        test.info("===== TEST PASSED: Product details validated successfully =====");
    }


    @Test
    public void verifyThatWeAreAbleToGiveReviewForProducts() throws InterruptedException {

        test.info("===== TEST START: Verify product review submission =====");

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);

        homePage.goToProductsPage();
        productsPage.clickViewProduct(1);

        test.info("Step 1: Submitting product review");
        ReviewDO reviewDO = ReviewDF.fillReviewDetails();
        productDetailPage.submitReview(reviewDO);

        Assert.assertTrue(productDetailPage.isReviewSuccessAlertDisplayed(),
                "Review success message is not displayed.");

        test.info("===== TEST PASSED: Product review submitted successfully =====");
    }


    @Test
    public void verifyEndToEndProductCheckoutFunctionality() throws InterruptedException {

        test.info("===== TEST START: Verify end-to-end product checkout flow =====");

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginPage = new LoginSignupPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        CardDetailsPage cardDetailsPage = new CardDetailsPage(driver);
        OrderCompletePage orderCompletePage = new OrderCompletePage(driver);

        test.info("Step 1: Add product to cart");
        homePage.goToProductsPage();
        productsPage.clickViewProduct(1);
        productDetailPage.clickAddToCart();
        productDetailPage.clickContinueShopping();
        productsPage.goToCartPage();

        Assert.assertTrue(cartPage.isCartTableDisplayed(), "Cart table is not displayed.");
        Assert.assertTrue(cartPage.isProductImageDisplayed(), "Product image is not displayed.");

        test.info("Step 2: Proceed to checkout and validate modal");
        cartPage.clickProceedToCheckout();
        Assert.assertTrue(cartPage.isCheckoutModalDisplayed(), "Checkout modal is not displayed.");
        cartPage.clickRegisterLoginModalLink();

        test.info("Step 3: Logging in before checkout");
        loginPage.login(UserDF.fillValidUserLoginDetails());
        homePage.goToCartPage();

        cartPage.clickProceedToCheckout();
        Assert.assertTrue(checkoutPage.isPlaceOrderButtonDisplayed(), "Place order button is not displayed.");

        test.info("Step 4: Validating order summary");
        Assert.assertEquals(checkoutPage.getTotalAmount(), "Rs. 400", "Total amount mismatch.");
        Assert.assertEquals(checkoutPage.getNumberOfProductsInCart(), 1, "Unexpected number of products in cart.");

        test.info("Step 5: Completing checkout");
        checkoutPage.completeCheckout("Order needs to be placed immediately");

        test.info("Step 6: Completing payment");
        cardDetailsPage.completePayment(CardDetailsDF.fillContactUsDetails());
        Assert.assertTrue(cardDetailsPage.isSuccessMessageDisplayed(), "Payment success message not displayed.");

        test.info("Step 7: Validating order confirmation");
        Assert.assertTrue(orderCompletePage.isOrderPlacedTitleDisplayed(), "Order placed title not displayed.");
        Assert.assertEquals(orderCompletePage.getOrderPlacedTitle(), "ORDER PLACED!", "Order placed title mismatch.");
        Assert.assertEquals(orderCompletePage.getCongratulationsText(),
                "Congratulations! Your order has been confirmed!",
                "Congratulations message mismatch.");

        orderCompletePage.clickContinue();

        test.info("Step 8: Validating user returned to home page");
        Assert.assertTrue(homePage.isHomePageLogoDisplayed(), "Home page logo is not displayed.");
        Assert.assertEquals(homePage.getLoggedInUserName(),
                "Amitesh Vashishth",
                "Logged-in username mismatch.");

        test.info("===== TEST PASSED: End-to-end checkout flow verified successfully =====");
    }
}