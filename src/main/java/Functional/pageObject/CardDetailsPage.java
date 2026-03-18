package Functional.pageObject;

import Functional.dataObject.CardDetailsDO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CardDetailsPage extends CommonPage {

    public CardDetailsPage(WebDriver driver) {super(driver);}

    private final By nameOnCardInput = By.cssSelector("input[data-qa='name-on-card']");
    private final By cardNumberInput = By.cssSelector("input[data-qa='card-number']");
    private final By cvcInput = By.cssSelector("input[data-qa='cvc']");
    private final By expiryMonthInput = By.cssSelector("input[data-qa='expiry-month']");
    private final By expiryYearInput = By.cssSelector("input[data-qa='expiry-year']");
    private final By payAndConfirmButton = By.cssSelector("button[data-qa='pay-button']");
    private final By successMessage = By.id("success_message");

    public boolean isSuccessMessageDisplayed() {
        return selenium.isElementPresent(successMessage);
    }

    public void fillPaymentDetails(CardDetailsDO cardDetails) throws InterruptedException {
        selenium.enterText(nameOnCardInput,cardDetails.getName(),true);
        selenium.enterText(cardNumberInput,cardDetails.getCardNumber(),true);
        selenium.enterText(cvcInput,cardDetails.getCvc(),true);
        selenium.enterText(expiryMonthInput,cardDetails.getExpiryMonth(),true);
        selenium.enterText(expiryYearInput,cardDetails.getExpiryYear(),true);
        selenium.clickOn(payAndConfirmButton);
    }
}

