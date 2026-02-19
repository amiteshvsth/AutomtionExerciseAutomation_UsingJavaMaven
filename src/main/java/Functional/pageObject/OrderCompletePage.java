package Functional.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderCompletePage extends CommonPage {

    public OrderCompletePage(WebDriver driver) {super(driver);}

    // Order Placed Section
    private final By orderPlacedTitle = By.xpath("//h2[@data-qa='order-placed']");
    private final By congratulationsText = By.xpath("//p[contains(text(),'Congratulations! Your order has been confirmed!')]");
    private final By downloadInvoiceBtn = By.xpath("//a[@class='btn btn-default check_out']");
    private final By continueBtn = By.xpath("//a[@data-qa='continue-button']");

    // Order Placed Actions
    public String getOrderPlacedTitle() {
        return selenium.getText(orderPlacedTitle);
    }

    public boolean isOrderPlacedTitleDisplayed() {
        return selenium.isElementPresent(orderPlacedTitle);
    }

    public String getCongratulationsText() {
        return selenium.getText(congratulationsText);
    }

    public void clickDownloadInvoice() throws InterruptedException {
        selenium.clickOn(downloadInvoiceBtn);
    }

    public boolean isInvoiceDownloaded() {
        return selenium.isElementPresent(downloadInvoiceBtn);
    }

    public void clickContinue() throws InterruptedException {
        selenium.clickOn(continueBtn);
    }
}

