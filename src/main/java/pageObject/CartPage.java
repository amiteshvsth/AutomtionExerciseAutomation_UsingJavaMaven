package pageObject;

import pageObject.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private final By checkoutBtn = By.className("check_out");
    private final By cartTable = By.id("cart_info_table");

    public CartPage(WebDriver driver){
        super(driver);
    }

    public void proceedToCheckout() throws  InterruptedException {
        selenium.clickOn(checkoutBtn);
    }

    public boolean isCartLoaded() throws InterruptedException {
        return selenium.isElementPresent(cartTable);
    }
}

