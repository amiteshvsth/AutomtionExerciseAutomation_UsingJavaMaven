package Functional.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutModalPage extends CommonPage{
    public CheckoutModalPage(WebDriver driver){
        super(driver);
    }
    private final By checkoutModal = By.id("checkoutModal");
    private final By registerLoginModalLink = By.xpath("//div[@id='checkoutModal']//a[@href='/login']");

    public boolean isCheckoutModalDisplayed() {
        return selenium.isElementPresent(checkoutModal);
    }

    public void clickRegisterLoginModalLink() throws InterruptedException {
        selenium.clickOn(registerLoginModalLink);
    }
}
