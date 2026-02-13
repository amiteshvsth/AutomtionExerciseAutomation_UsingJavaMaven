package pageObject;

import pageObject.base.BasePage;
import org.openqa.selenium.*;

public class HomePage extends BasePage {

    private final By productsLink = By.xpath("//a[contains(@href,'products')]");
    private final By loginLink = By.xpath("//a[contains(@href,'login')]");
    private final By cartLink = By.xpath("//a[contains(@href,'view_cart')]");

    public HomePage(WebDriver driver){
        super(driver);
    }


    public void goToProducts() throws InterruptedException { selenium.clickOn(productsLink); }
    public void goToLogin() throws InterruptedException{ selenium.clickOn(loginLink); }
    public void goToCart() throws InterruptedException{ selenium.clickOn(cartLink); }
}

