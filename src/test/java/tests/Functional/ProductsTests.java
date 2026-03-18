package tests.Functional;

import Functional.dataFactory.CardDetailsDF;
import Functional.dataFactory.ReviewDF;
import Functional.dataFactory.UserDF;
import Functional.dataObject.ProductDetailsDO;
import Functional.dataObject.ReviewDO;
import Functional.dataObject.UserDO;
import Functional.enums.TopNavLinks;
import Functional.pageObject.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

import java.util.List;

import static Functional.utilities.Constants.*;

public class ProductsTests extends BaseTest {

    @Test
    public void verifyThatProductsPageLoadedSuccessfully() throws InterruptedException {


        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        step("Navigating to Products page");
        homePage.clickOnTopNavLink(TopNavLinks.PRODUCTS);

        step("Validating categories and brands");
        List<String> actualCategories = productsPage.getProductCategories();
        List<String> actualBrands = productsPage.getBrandNames();

        Assert.assertTrue(productsPage.isSaleImageDisplayed(), "Sale image is not displayed.");
        Assert.assertEquals(productsPage.getProductCategoriesCount(), 3, "Product categories count mismatch.");
        Assert.assertEquals(actualCategories, EXPECTED_PRODUCT_CATEGORIES, "Product categories mismatch.");
        Assert.assertEquals(actualBrands, EXPECTED_BRANDS, "Brand names mismatch.");
        Assert.assertEquals(productsPage.getBrandsCount(), 8, "Brand count mismatch.");
        Assert.assertTrue(productsPage.isProductsHeaderDisplayed(), "Products header is not displayed.");

    }


    @Test
    public void verifyThatSearchFunctionalityIsWorkingForProductsPage() throws InterruptedException {


        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        homePage.clickOnTopNavLink(TopNavLinks.PRODUCTS);

        step("Searching for product 'Blue Top'");
        productsPage.searchProduct("Blue Top");

        step("Validating search results");
        List<String> productNames = productsPage.getProductNames();

        Assert.assertEquals(productNames.size(), 1, "Unexpected number of search results.");
        Assert.assertEquals(productNames.getFirst(), "Blue Top", "Product name mismatch.");

    }


    @Test
    public void verifyThatProductCountIsCorrectForEachBrand() throws InterruptedException {


        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        homePage.clickOnTopNavLink(TopNavLinks.PRODUCTS);

        for (String brand : EXPECTED_BRANDS_COUNTS.keySet()) {

            step("Validating brand: " + brand);

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

    }


    @Test
    public void verifyThatProductSubCategoriesCountIsCorrect() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        homePage.clickOnTopNavLink(TopNavLinks.PRODUCTS);

        for (String category : EXPECTED_SUBCATEGORIES_COUNT.keySet()) {

            step("Validating sub-categories for category: " + category);

            int actualSubCategories = productsPage.getProductSubCategories(category).size();
            int expectedSubCategoriesCount = EXPECTED_SUBCATEGORIES_COUNT.get(category);

            Assert.assertEquals(actualSubCategories, expectedSubCategoriesCount,
                    "Sub-category count mismatch for category: " + category);
        }

    }


    @Test
    public void verifyThatProductDetailsAreDisplayedOnProductDetailsPage() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);

        homePage.clickOnTopNavLink(TopNavLinks.PRODUCTS);
        productsPage.clickViewProduct(1);

        step("Fetching product details");
        ProductDetailsDO productDetails = productDetailPage.getProductDetails();

        Assert.assertEquals(productDetails.getName(), "Men Tshirt", "Product name mismatch.");
        Assert.assertEquals(productDetails.getCategory(), "Category: Men > Tshirts", "Category mismatch.");
        Assert.assertEquals(productDetails.getPrice(), "Rs. 400", "Price mismatch.");
        Assert.assertEquals(productDetails.getAvailability(), "Availability: In Stock", "Availability mismatch.");
        Assert.assertEquals(productDetails.getCondition(), "Condition: New", "Condition mismatch.");
        Assert.assertEquals(productDetails.getBrand(), "Brand: H&M", "Brand mismatch.");

    }


    @Test
    public void verifyThatWeAreAbleToGiveReviewForProducts() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);

        homePage.clickOnTopNavLink(TopNavLinks.PRODUCTS);
        productsPage.clickViewProduct(1);

        step("Submitting product review");
        ReviewDO reviewDO = ReviewDF.getData();
        productDetailPage.submitReview(reviewDO);

        Assert.assertTrue(productDetailPage.isReviewSuccessAlertDisplayed(),
                "Review success message is not displayed.");

    }


    @Test
    public void verifyEndToEndProductCheckoutFunctionality() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        LoginSignupPage loginPage = new LoginSignupPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        CardDetailsPage cardDetailsPage = new CardDetailsPage(driver);
        OrderCompletePage orderCompletePage = new OrderCompletePage(driver);
        CheckoutModalPage checkoutModalPage = new CheckoutModalPage(driver);

        step("Add product to cart");
        homePage.clickOnTopNavLink(TopNavLinks.PRODUCTS);
        productsPage.clickViewProduct(1);
        productDetailPage.clickAddToCart();
        productDetailPage.clickContinueShopping();
        productsPage.clickOnTopNavLink(TopNavLinks.CART_MENU_PAGE);

        Assert.assertTrue(cartPage.isCartTableDisplayed(), "Cart table is not displayed.");
        Assert.assertTrue(cartPage.isProductImageDisplayed(), "Product image is not displayed.");

        step("Proceed to checkout and validate modal");
        cartPage.clickProceedToCheckout();
        Assert.assertTrue(checkoutModalPage.isCheckoutModalDisplayed(), "Checkout modal is not displayed.");
        checkoutModalPage.clickRegisterLoginModalLink();

        step("Logging in before checkout");
        UserDO userData = UserDF.getData();
        userData.setEmail("amiteshvashishthh@yopmail.com");
        userData.setPassword("12345678");
        loginPage.login(userData);
        homePage.clickOnTopNavLink(TopNavLinks.CART_MENU_PAGE);

        cartPage.clickProceedToCheckout();
        Assert.assertTrue(checkoutPage.isPlaceOrderButtonDisplayed(), "Place order button is not displayed.");

        step("Validating order summary");
        Assert.assertEquals(checkoutPage.getTotalAmount(), "Rs. 400", "Total amount mismatch.");
        Assert.assertEquals(checkoutPage.getNumberOfProductsInCart(), 1, "Unexpected number of products in cart.");

        step("Completing checkout");
        checkoutPage.completeCheckout("Order needs to be placed immediately");

        step("Completing payment");
        cardDetailsPage.fillPaymentDetails(CardDetailsDF.getData());
//        Assert.assertTrue(cardDetailsPage.isSuccessMessageDisplayed(), "Payment success message not displayed.");

        step("Validating order confirmation");
        Assert.assertTrue(orderCompletePage.isOrderPlacedTitleDisplayed(), "Order placed title not displayed.");
        Assert.assertEquals(orderCompletePage.getOrderPlacedTitle(), "ORDER PLACED!", "Order placed title mismatch.");
        Assert.assertEquals(orderCompletePage.getCongratulationsText(),
                "Congratulations! Your order has been confirmed!",
                "Congratulations message mismatch.");

        orderCompletePage.clickContinue();

        step("Validating user returned to home page");
        Assert.assertTrue(homePage.isHomePageLogoDisplayed(), "Home page logo is not displayed.");
        Assert.assertEquals(homePage.getLoggedInUserName(),
                "Amitesh Vashishth",
                "Logged-in username mismatch.");

    }
}