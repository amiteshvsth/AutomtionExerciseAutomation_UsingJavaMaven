package Functional.pageObject;

import Functional.dataObject.ProductDetailsDO;
import Functional.dataObject.ReviewDO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailPage extends CommonPage {

    public ProductDetailPage(WebDriver driver) {super(driver);}

    // Product Information Elements
    private final By productName = By.xpath("//div[@class='product-information']/h2");
    private final By category = By.xpath("//div[@class='product-information']/p[contains(text(),'Category:')]");
    private final By price = By.xpath("//div[@class='product-information']/span/span");
    private final By availability = By.xpath("//div[@class='product-information']/p/b[text()='Availability:']/parent::p");
    private final By condition = By.xpath("//div[@class='product-information']/p/b[text()='Condition:']/parent::p");
    private final By brand = By.xpath("//div[@class='product-information']/p/b[text()='Brand:']/parent::p");

    // Quantity and Add to Cart
    private final By addToCartBtn = By.xpath("//button[@type='button' and contains(@class,'cart')]");

    // Modal Elements
    private final By viewCartLink = By.xpath("//u[text()='View Cart']");
    private final By continueShopping = By.xpath("//button[contains(text(),'Continue Shopping')]");

    // Review Section
    private final By reviewName = By.id("name");
    private final By reviewEmail = By.id("email");
    private final By reviewTextarea = By.id("review");
    private final By submitReviewBtn = By.id("button-review");
    private final By reviewSuccessAlert = By.xpath("//div[@class='alert-success alert']/span[text()='Thank you for your review.']");
    // Product Information Actions

    private String getSafeText(By locator) {
        try {
            return selenium.getText(locator);
        } catch (Exception e) {
            return null;
        }
    }
    public ProductDetailsDO getProductDetails() {

        return new ProductDetailsDO(
                getSafeText(productName),
                getSafeText(category),
                getSafeText(price),
                getSafeText(availability),
                getSafeText(condition),
                getSafeText(brand)
        );
    }

    public void clickAddToCart() throws InterruptedException {
        selenium.clickOn(addToCartBtn);
    }

    // Modal Actions
    public void clickViewCart() throws InterruptedException {
        selenium.clickOn(viewCartLink);
    }

    public void clickContinueShopping() throws InterruptedException {
        selenium.clickOn(continueShopping);
    }

    public void submitReview(ReviewDO data) throws InterruptedException {
        selenium.enterText(reviewName,data.getName(),true);
        selenium.enterText(reviewEmail,data.getEmail(),true);
        selenium.enterText(reviewTextarea,data.getReviewText(),true);
        selenium.clickOn(submitReviewBtn);
    }

    // Validation Methods

    public boolean isReviewSuccessAlertDisplayed() {
        return selenium.isElementPresent(reviewSuccessAlert);
    }

}

