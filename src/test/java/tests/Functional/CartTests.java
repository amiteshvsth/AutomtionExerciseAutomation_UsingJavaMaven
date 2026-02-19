package tests.Functional;

import Functional.pageObject.CartPage;
import Functional.pageObject.LoginSignupPage;
import Functional.pageObject.ProductDetailPage;
import Functional.pageObject.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

public class CartTests extends BaseTest {

    @Test
    public void VerifyThatCartIsEmptyByDefault() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);

        loginSignupPage.goToCartPage();
        Assert.assertTrue(cartPage.isEmptyCartMessageDisplayed(),"Cart message is not displayed");
        Assert.assertTrue(cartPage.isBuyProductsLinkDisplayed(),"Buy Products Link is not displayed");
    }

    @Test
    public void VerifyAddToCartFunctionality() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        loginSignupPage.goToProductsPage();
        productsPage.clickViewProduct(1);
        productDetailPage.clickAddToCart();
        productDetailPage.clickViewCart();
        Assert.assertTrue(cartPage.isCartTableDisplayed(),"Cart table is not displayed");
        Assert.assertTrue(cartPage.isProductImageDisplayed(),"Product image is not displayed.");
        Assert.assertTrue(cartPage.isCartMenuDisplayed(),"Cart Menu is not displayed.");
        Assert.assertTrue(cartPage.isCardTableImageHeaderDisplayed(),"Image Header is not displayed.");
        Assert.assertTrue(cartPage.isCartTableDescriptionHeaderDisplayed(),"Description Header is not displayed.");
        Assert.assertTrue(cartPage.isCartTablePriceHeaderDisplayed(),"Price Header is not displayed.");
        Assert.assertTrue(cartPage.isCartTableQuantityHeaderDisplayed(),"Quantity Header is not displayed.");
        Assert.assertTrue(cartPage.isCartTableTotalHeaderDisplayed(),"Total Header is not displayed.");
        Assert.assertTrue(cartPage.isProductPriceDisplayed(),"Product Price is not displayed.");
        Assert.assertTrue(cartPage.isProductQuantityDisplayed(),"Product Quantity is not displayed.");
        Assert.assertTrue(cartPage.isTotalPriceDisplayed(),"Total price is not displayed.");
        Assert.assertTrue(cartPage.isProceedToCheckoutButtonDisplayed(),"Proceed to checkout button is not displayed.");
    }

    @Test
    public void VerifyThatProductCanBeRemovedFromCart() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        LoginSignupPage loginSignupPage = new LoginSignupPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        loginSignupPage.goToProductsPage();
        productsPage.clickViewProduct(1);
        productDetailPage.clickAddToCart();
        productDetailPage.clickViewCart();
        cartPage.clickDeleteProduct();
        Assert.assertFalse(cartPage.isProductImageDisplayed(),"Product image is  displayed.");
        Assert.assertFalse(cartPage.isCartMenuDisplayed(),"Cart Menu is  displayed.");
        Assert.assertFalse(cartPage.isCardTableImageHeaderDisplayed(),"Image Header is  displayed.");
        Assert.assertFalse(cartPage.isCartTableDescriptionHeaderDisplayed(),"Description Header is  displayed.");
        Assert.assertFalse(cartPage.isCartTablePriceHeaderDisplayed(),"Price Header is  displayed.");
        Assert.assertFalse(cartPage.isCartTableQuantityHeaderDisplayed(),"Quantity Header is  displayed.");
        Assert.assertFalse(cartPage.isCartTableTotalHeaderDisplayed(),"Total Header is  displayed.");
        Assert.assertFalse(cartPage.isProductPriceDisplayed(),"Product Price is  displayed.");
        Assert.assertFalse(cartPage.isProductQuantityDisplayed(),"Product Quantity is  displayed.");
        Assert.assertFalse(cartPage.isTotalPriceDisplayed(),"Total price is  displayed.");
        Assert.assertFalse(cartPage.isProceedToCheckoutButtonDisplayed(),"Proceed to checkout button is  displayed.");
        Assert.assertTrue(cartPage.isEmptyCartMessageDisplayed(),"Cart message is  displayed");
        Assert.assertTrue(cartPage.isBuyProductsLinkDisplayed(),"Buy Products Link is  displayed");
    }
}


