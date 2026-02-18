package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutPage extends CommonPage {

    public CheckoutPage(WebDriver driver) {super(driver);}

    // Address Details Section
    private final By addressDetailsHeading = By.xpath("//h2[@class='heading' and text()='Address Details']");
    private final By checkoutInfoDiv = By.xpath("//div[@data-qa='checkout-info']");
    private final By deliveryAddressSection = By.id("address_delivery");
    private final By deliveryAddressTitle = By.xpath("//ul[@id='address_delivery']//h3[@class='page-subheading']");
    private final By deliveryAddressName = By.xpath("//ul[@id='address_delivery']//li[@class='address_firstname address_lastname']");
    private final By deliveryAddressLine1 = By.xpath("//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][1]");
    private final By deliveryAddressLine2 = By.xpath("//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][2]");
    private final By deliveryAddressLine3 = By.xpath("//ul[@id='address_delivery']//li[@class='address_address1 address_address2'][3]");
    private final By deliveryAddressCityStateZip = By.xpath("//ul[@id='address_delivery']//li[@class='address_city address_state_name address_postcode']");
    private final By deliveryAddressCountry = By.xpath("//ul[@id='address_delivery']//li[@class='address_country_name']");
    private final By deliveryAddressPhone = By.xpath("//ul[@id='address_delivery']//li[@class='address_phone']");

    private final By billingAddressSection = By.id("address_invoice");
    private final By billingAddressTitle = By.xpath("//ul[@id='address_invoice']//h3[@class='page-subheading']");
    private final By billingAddressName = By.xpath("//ul[@id='address_invoice']//li[@class='address_firstname address_lastname']");
    private final By billingAddressLine1 = By.xpath("//ul[@id='address_invoice']//li[@class='address_address1 address_address2'][1]");
    private final By billingAddressLine2 = By.xpath("//ul[@id='address_invoice']//li[@class='address_address1 address_address2'][2]");
    private final By billingAddressLine3 = By.xpath("//ul[@id='address_invoice']//li[@class='address_address1 address_address2'][3]");
    private final By billingAddressCityStateZip = By.xpath("//ul[@id='address_invoice']//li[@class='address_city address_state_name address_postcode']");
    private final By billingAddressCountry = By.xpath("//ul[@id='address_invoice']//li[@class='address_country_name']");
    private final By billingAddressPhone = By.xpath("//ul[@id='address_invoice']//li[@class='address_phone']");

    // Review Order Section
    private final By reviewOrderHeading = By.xpath("//h2[@class='heading' and text()='Review Your Order']");
    private final By cartInfoDiv = By.id("cart_info");
    private final By cartTable = By.xpath("//div[@id='cart_info']//table");
    private final By cartTableRows = By.xpath("//div[@id='cart_info']//table//tbody//tr");
    private final By productRows = By.xpath("//div[@id='cart_info']//table//tbody//tr[contains(@id,'product-')]");
    private final By totalAmountRow = By.xpath("//div[@id='cart_info']//table//tbody//tr[.//h4//b[text()='Total Amount']]");
    private final By totalAmountText = By.xpath("//div[@id='cart_info']//table//tbody//tr[.//h4//b[text()='Total Amount']]//p[@class='cart_total_price']");

    // Order Comment Section
    private final By orderMessageDiv = By.id("ordermsg");
    private final By orderCommentLabel = By.xpath("//div[@id='ordermsg']//label");
    private final By commentTextarea = By.xpath("//div[@id='ordermsg']//textarea[@name='message']");

    // Place Order Button
    private final By placeOrderButton = By.xpath("//a[@href='/payment' and @class='btn btn-default check_out']");

    // Address Details Methods
    public boolean isAddressDetailsHeadingDisplayed() {
        return selenium.isElementPresent(addressDetailsHeading);
    }

    public String getDeliveryAddressTitle() {
        return selenium.getText(deliveryAddressTitle);
    }

    public String getDeliveryAddressName() {
        return selenium.getText(deliveryAddressName);
    }

    public String getDeliveryAddressLine1() {
        return selenium.getText(deliveryAddressLine1);
    }

    public String getDeliveryAddressLine2() {
        return selenium.getText(deliveryAddressLine2);
    }

    public String getDeliveryAddressLine3() {
        return selenium.getText(deliveryAddressLine3);
    }

    public String getDeliveryAddressCityStateZip() {
        return selenium.getText(deliveryAddressCityStateZip);
    }

    public String getDeliveryAddressCountry() {
        return selenium.getText(deliveryAddressCountry);
    }

    public String getDeliveryAddressPhone() {
        return selenium.getText(deliveryAddressPhone);
    }

    public String getBillingAddressTitle() {
        return selenium.getText(billingAddressTitle);
    }

    public String getBillingAddressName() {
        return selenium.getText(billingAddressName);
    }

    public String getBillingAddressLine1() {
        return selenium.getText(billingAddressLine1);
    }

    public String getBillingAddressLine2() {
        return selenium.getText(billingAddressLine2);
    }

    public String getBillingAddressLine3() {
        return selenium.getText(billingAddressLine3);
    }

    public String getBillingAddressCityStateZip() {
        return selenium.getText(billingAddressCityStateZip);
    }

    public String getBillingAddressCountry() {
        return selenium.getText(billingAddressCountry);
    }

    public String getBillingAddressPhone() {
        return selenium.getText(billingAddressPhone);
    }

    public boolean isDeliveryAddressSectionDisplayed() {
        return selenium.isElementPresent(deliveryAddressSection);
    }

    public boolean isBillingAddressSectionDisplayed() {
        return selenium.isElementPresent(billingAddressSection);
    }

    // Review Order Methods
    public boolean isReviewOrderHeadingDisplayed() {
        return selenium.isElementPresent(reviewOrderHeading);
    }

    public boolean isCartTableDisplayed() {
        return selenium.isElementPresent(cartTable);
    }

    public int getNumberOfProductsInCart() {
        return selenium.findElements(productRows).size();
    }

    public String getTotalAmount() {
        return selenium.getText(totalAmountText);
    }

    public List<WebElement> getProductRows() {
        return selenium.findElements(productRows);
    }

    public String getProductNameByIndex(int index) {
        By productName = By.xpath("(//div[@id='cart_info']//table//tbody//tr[contains(@id,'product-')]//h4//a)[" + index + "]");
        return selenium.getText(productName);
    }

    public String getProductPriceByIndex(int index) {
        By productPrice = By.xpath("(//div[@id='cart_info']//table//tbody//tr[contains(@id,'product-')]//td[@class='cart_price']//p)[" + index + "]");
        return selenium.getText(productPrice);
    }

    public String getProductQuantityByIndex(int index) {
        By productQuantity = By.xpath("(//div[@id='cart_info']//table//tbody//tr[contains(@id,'product-')]//td[@class='cart_quantity']//button)[" + index + "]");
        return selenium.getText(productQuantity);
    }

    public String getProductTotalByIndex(int index) {
        By productTotal = By.xpath("(//div[@id='cart_info']//table//tbody//tr[contains(@id,'product-')]//p[@class='cart_total_price'])[" + index + "]");
        return selenium.getText(productTotal);
    }

    public void setOrderComment(String comment) {
        selenium.enterText(commentTextarea,comment,true);
    }

    public boolean isOrderCommentTextareaDisplayed() {
        return selenium.isElementPresent(commentTextarea);
    }

    public String getOrderCommentLabel() {
        return selenium.getText(orderCommentLabel);
    }

    // Place Order Methods
    public void clickPlaceOrderButton() throws InterruptedException {
        selenium.clickOn(placeOrderButton);
    }

    public boolean isPlaceOrderButtonDisplayed() {
        return selenium.isElementPresent(placeOrderButton);
    }

    // Combined Action Methods
    public void completeCheckout(String orderComment) throws InterruptedException {
        setOrderComment(orderComment);
        clickPlaceOrderButton();
    }
}

