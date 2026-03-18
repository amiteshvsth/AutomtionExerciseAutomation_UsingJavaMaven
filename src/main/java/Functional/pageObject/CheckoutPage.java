package Functional.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends CommonPage {

    public CheckoutPage(WebDriver driver) {super(driver);}

    // Address Details Section
    private final By productRows = By.xpath("//div[@id='cart_info']//table//tbody//tr[contains(@id,'product-')]");
    private final By totalAmountText = By.xpath("//div[@id='cart_info']//table//tbody//tr[.//h4//b[text()='Total Amount']]//p[@class='cart_total_price']");

    // Order Comment Section
    private final By commentTextarea = By.xpath("//div[@id='ordermsg']//textarea[@name='message']");

    // Place Order Button
    private final By placeOrderButton = By.xpath("//a[@href='/payment' and @class='btn btn-default check_out']");

    // Review Order Methods

    public int getNumberOfProductsInCart() {
        return selenium.findElements(productRows).size();
    }

    public String getTotalAmount() {
        return selenium.getText(totalAmountText);
    }

    public boolean isPlaceOrderButtonDisplayed() {
        return selenium.isElementPresent(placeOrderButton);
    }

    // Combined Action Methods
    public void completeCheckout(String orderComment) throws InterruptedException {
        selenium.enterText(commentTextarea,orderComment,true);
        selenium.clickOn(placeOrderButton);
    }
}

