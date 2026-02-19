package Functional.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends CommonPage {

    // Checkout section
    private final By proceedToCheckoutBtn = By.xpath("//a[@class='btn btn-default check_out']");

    // Modal
    private final By checkoutModal = By.id("checkoutModal");
    private final By registerLoginModalLink = By.xpath("//div[@id='checkoutModal']//a[@href='/login']");
    private final By continueOnCartBtn = By.xpath("//button[@class='btn btn-success close-checkout-modal btn-block']");

    // Cart table
    private final By cartInfoTable = By.id("cart_info_table");
    private final By productImage = By.xpath("//img[@class='product_image']");
    private final By productDetailsLink = By.xpath("//a[@href='/product_details/2']");
    private final By deleteProductBtn = By.xpath("//a[@class='cart_quantity_delete']");
    private final By emptyCartMessage = By.id("empty_cart");
    private final By buyProductsLink = By.xpath("//span[@id='empty_cart']//a[@href='/products']");
    private final By cartMenu = By.className("cart_menu");
    private final By cartTableImageHeader = By.className("image");
    private final By cartTableDescriptionHeader = By.xpath("//tr[@class='cart_menu']/td[@class='description']");
    private final By cartTablePriceHeader = By.xpath("//tr[@class='cart_menu']/td[@class='price']");
    private final By cartTableQuantityHeader = By.xpath("//tr[@class='cart_menu']/td[@class='quantity']");
    private final By cartTableTotalHeader = By.xpath("//tr[@class='cart_menu']/td[@class='total']");
    private final By productPrice = By.xpath("//td[@class='cart_price']/p");
    private final By totalPrice =By.xpath("//p[@class='cart_total_price']");
    private final By productQuantity = By.xpath("//td[@class='cart_quantity']/button");



    public CartPage(WebDriver driver){
        super(driver);
    }

    // Checkout methods
    public void clickProceedToCheckout() throws InterruptedException {
        selenium.clickOn(proceedToCheckoutBtn);
    }

    public boolean isProceedToCheckoutButtonDisplayed() throws InterruptedException {
        return selenium.isElementPresent(proceedToCheckoutBtn);
    }

    // Modal methods
    public boolean isCheckoutModalDisplayed() {
        return selenium.isElementPresent(checkoutModal);
    }

    public void clickRegisterLoginModalLink() throws InterruptedException {
        selenium.clickOn(registerLoginModalLink);
    }

    public void clickContinueOnCartButton() throws InterruptedException {
        selenium.clickOn(continueOnCartBtn);
    }

    // Cart methods
    public boolean isCartTableDisplayed() {
        return selenium.isElementPresent(cartInfoTable);
    }

    public boolean isProductImageDisplayed() {
        List<WebElement> elements = selenium.findElements(productImage);
        return elements.size() > 0 && elements.getFirst().isDisplayed();
    }

    public void clickProductDetails() throws InterruptedException {
        selenium.clickOn(productDetailsLink);
    }

    public void clickDeleteProduct() throws InterruptedException {
        selenium.clickOn(deleteProductBtn);
    }

    public boolean isEmptyCartMessageDisplayed() {
        return selenium.isElementPresent(emptyCartMessage);
    }

    public void clickBuyProductsLink() throws InterruptedException {
        selenium.clickOn(buyProductsLink);
    }

    public boolean isBuyProductsLinkDisplayed() throws InterruptedException {
        return selenium.isElementPresent(buyProductsLink);
    }

    public boolean isCartMenuDisplayed() throws InterruptedException {
        return selenium.isElementPresent(cartMenu);
    }

    public boolean isCardTableImageHeaderDisplayed() throws InterruptedException {
        return selenium.isElementPresent(cartTableImageHeader);
    }

    public boolean isCartTableDescriptionHeaderDisplayed() throws InterruptedException {
        return selenium.isElementPresent(cartTableDescriptionHeader);
    }

    public boolean isCartTablePriceHeaderDisplayed() throws InterruptedException {
        return selenium.isElementPresent(cartTablePriceHeader);
    }

    public boolean isCartTableQuantityHeaderDisplayed() throws InterruptedException {
        return selenium.isElementPresent(cartTableQuantityHeader);
    }

    public boolean isCartTableTotalHeaderDisplayed() throws InterruptedException {
        return selenium.isElementPresent(cartTableTotalHeader);
    }

    public boolean isProductPriceDisplayed() throws InterruptedException {
        return selenium.isElementPresent(productPrice);
    }

    public boolean isProductQuantityDisplayed() throws InterruptedException {
        return selenium.isElementPresent(productQuantity);
    }

    public boolean isTotalPriceDisplayed() throws InterruptedException {
        return selenium.isElementPresent(totalPrice);
    }
}

