package tests.Functional;

import Functional.enums.TopNavLinks;
import Functional.pageObject.CartPage;
import Functional.pageObject.LoginSignupPage;
import Functional.pageObject.ProductDetailPage;
import Functional.pageObject.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

public class CartTests extends BaseTest {

    @Test
    public void verifyThatCartIsEmptyByDefault() throws InterruptedException {

        step("===== TEST START: Verify cart is empty by default =====");

        CartPage cartPage = new CartPage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        step("Navigating to Cart page");
        loginSignupPage.clickOnTopNavLink(TopNavLinks.CART_MENU_PAGE);

        step("Validating empty cart message is displayed");
        Assert.assertTrue(cartPage.isEmptyCartMessageDisplayed(),
                "Empty cart message is not displayed.");

        step("Validating 'Buy Products' link is displayed");
        Assert.assertTrue(cartPage.isBuyProductsLinkDisplayed(),
                "'Buy Products' link is not displayed.");

        step("===== TEST PASSED: Cart is empty by default =====");
    }


    @Test
    public void verifyAddToCartFunctionality() throws InterruptedException {

        step("===== TEST START: Verify add to cart functionality =====");

        CartPage cartPage = new CartPage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);

        step("Navigating to Products page");
        loginSignupPage.clickOnTopNavLink(TopNavLinks.PRODUCTS);

        step("Opening first product details page");
        productsPage.clickViewProduct(1);

        step("Adding product to cart");
        productDetailPage.clickAddToCart();

        step("Navigating to Cart page");
        productDetailPage.clickViewCart();

        step("Validating cart table and headers are displayed");
        Assert.assertTrue(cartPage.isCartTableDisplayed(), "Cart table is not displayed.");
        Assert.assertTrue(cartPage.isCartMenuDisplayed(), "Cart menu is not displayed.");
        Assert.assertTrue(cartPage.isCardTableImageHeaderDisplayed(), "Image header is not displayed.");
        Assert.assertTrue(cartPage.isCartTableDescriptionHeaderDisplayed(), "Description header is not displayed.");
        Assert.assertTrue(cartPage.isCartTablePriceHeaderDisplayed(), "Price header is not displayed.");
        Assert.assertTrue(cartPage.isCartTableQuantityHeaderDisplayed(), "Quantity header is not displayed.");
        Assert.assertTrue(cartPage.isCartTableTotalHeaderDisplayed(), "Total header is not displayed.");

        step("Validating product details in cart");
        Assert.assertTrue(cartPage.isProductImageDisplayed(), "Product image is not displayed.");
        Assert.assertTrue(cartPage.isProductPriceDisplayed(), "Product price is not displayed.");
        Assert.assertTrue(cartPage.isProductQuantityDisplayed(), "Product quantity is not displayed.");
        Assert.assertTrue(cartPage.isTotalPriceDisplayed(), "Total price is not displayed.");

        step("Validating checkout option is available");
        Assert.assertTrue(cartPage.isProceedToCheckoutButtonDisplayed(),
                "Proceed to checkout button is not displayed.");

        step("===== TEST PASSED: Add to cart functionality verified successfully =====");
    }


    @Test
    public void verifyThatProductCanBeRemovedFromCart() throws InterruptedException {

        step("===== TEST START: Verify product can be removed from cart =====");

        CartPage cartPage = new CartPage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);

        step("Navigating to Products page");
        loginSignupPage.clickOnTopNavLink(TopNavLinks.PRODUCTS);

        step("Opening first product details page");
        productsPage.clickViewProduct(1);

        step("Adding product to cart");
        productDetailPage.clickAddToCart();

        step("Navigating to Cart page");
        productDetailPage.clickViewCart();

        step("Removing product from cart");
        cartPage.clickDeleteProduct();

        step("Validating cart is empty after removal");
        Assert.assertFalse(cartPage.isCartMenuDisplayed(), "Cart menu is still displayed.");
        Assert.assertFalse(cartPage.isCardTableImageHeaderDisplayed(), "Image header is still displayed.");
        Assert.assertFalse(cartPage.isCartTableDescriptionHeaderDisplayed(), "Description header is still displayed.");
        Assert.assertFalse(cartPage.isCartTablePriceHeaderDisplayed(), "Price header is still displayed.");
        Assert.assertFalse(cartPage.isCartTableQuantityHeaderDisplayed(), "Quantity header is still displayed.");
        Assert.assertFalse(cartPage.isCartTableTotalHeaderDisplayed(), "Total header is still displayed.");
        Assert.assertFalse(cartPage.isProductImageDisplayed(), "Product image is still displayed.");
        Assert.assertFalse(cartPage.isProductPriceDisplayed(), "Product price is still displayed.");
        Assert.assertFalse(cartPage.isProductQuantityDisplayed(), "Product quantity is still displayed.");
        Assert.assertFalse(cartPage.isTotalPriceDisplayed(), "Total price is still displayed.");
        Assert.assertFalse(cartPage.isProceedToCheckoutButtonDisplayed(),
                "Proceed to checkout button is still displayed.");

        step("Validating empty cart message and navigation link");
        Assert.assertTrue(cartPage.isEmptyCartMessageDisplayed(),
                "Empty cart message is not displayed.");
        Assert.assertTrue(cartPage.isBuyProductsLinkDisplayed(),
                "'Buy Products' link is not displayed.");

        step("===== TEST PASSED: Product removed from cart successfully =====");
    }
}