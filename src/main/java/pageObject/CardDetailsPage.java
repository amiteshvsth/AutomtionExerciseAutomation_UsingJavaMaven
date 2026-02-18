package pageObject;

import dataObject.CardDetailsDO;
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

    public void enterNameOnCard(String name) {
        selenium.enterText(nameOnCardInput,name,true);
    }

    public void enterCardNumber(String cardNumber) {
        selenium.enterText(cardNumberInput,cardNumber,true);
    }

    public void enterCvc(String cvc) {
        selenium.enterText(cvcInput,cvc,true);
    }

    public void enterExpiryMonth(String month) {
        selenium.enterText(expiryMonthInput,month,true);
    }

    public void enterExpiryYear(String year) {
        selenium.enterText(expiryYearInput,year,true);
    }

    public void clickPayAndConfirmButton() throws InterruptedException {
        selenium.clickOn(payAndConfirmButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return selenium.isElementPresent(successMessage);
    }

    public String getSuccessMessageText() {
        return selenium.getText(successMessage);
    }

    public void fillPaymentDetails(String nameOnCard, String cardNumber, String cvc, String expiryMonth, String expiryYear) {
        enterNameOnCard(nameOnCard);
        enterCardNumber(cardNumber);
        enterCvc(cvc);
        enterExpiryMonth(expiryMonth);
        enterExpiryYear(expiryYear);
    }

    public void completePayment(CardDetailsDO cardDetails) throws InterruptedException {
        fillPaymentDetails(cardDetails.getName(), cardDetails.getCardNumber(), cardDetails.getCvc(), cardDetails.getExpiryMonth(), cardDetails.getExpiryYear());
        clickPayAndConfirmButton();
    }
}

