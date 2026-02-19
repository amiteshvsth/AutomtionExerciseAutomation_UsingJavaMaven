package Functional.pageObject;

import org.openqa.selenium.*;

public class HomePage extends CommonPage {

    public HomePage(WebDriver driver){
        super(driver);
    }

    private final By testCasesButton = By.xpath("//a[@class='test_cases_list']/button");
    private final By apiListButton = By.xpath("//a[@class='api_list']/button");
    private final By carouselImage = By.xpath("//img[@class='girl img-responsive']");
    private final By product = By.xpath("//div[@class='productinfo text-center']");
    private final By productImage = By.xpath("//div[@class='productinfo text-center']/img");
    private final By productPrice = By.xpath("//div[@class='productinfo text-center']/h2");
    private final By productName = By.xpath("//div[@class='productinfo text-center']/p");
    private final By productAddToCartButton = By.xpath("//div[@class='productinfo text-center']/a");
    private final By productOverlayContent = By.xpath("//div[@class='overlay-content']");
    private final By overlayPrice = By.xpath("//div[@class='overlay-content']/h2");
    private final By overlayName = By.xpath("//div[@class='overlay-content']/p");
    private final By overlayAddToCartButton = By.xpath("//div[@class='overlay-content']/a");
    private final By viewProductButton = By.xpath("//ul[@class='nav nav-pills nav-justified']/li/a");
    private final By automationHeader = By.xpath("//div[@class='item active']//h1");
    private final By automationTagLine = By.xpath("//div[@class='item active']//h2[text()='Full-Fledged practice website for Automation Engineers']");
    private final By automationDescription = By.xpath("//div[@class='item active']//p[text()='All QA engineers can use this website for automation practice and API testing either they are at beginner or advance level. This is for everybody to help them brush up their automation skills.']");
    private final By categoryHeader = By.xpath("//div[@class='left-sidebar']/h2");
    private final By brandsHeader = By.xpath("//div[@class='brands_products']/h2");
    private final By featuredItemsHeader = By.xpath("//div[@class='features_items']/h2");
    private final By nextSlideIcon = By.xpath("//a[@data-slide='next']");
    private final By previousSlideIcon = By.xpath("//a[@data-slide='prev']");

    public boolean isTestCasesButtonDisplayed(){
        return selenium.isElementPresent(testCasesButton);
    }

    public boolean isApiListButtonDisplayed(){
        return selenium.isElementPresent(apiListButton);
    }

    public boolean isCarouselImageDisplayed(){
        return selenium.isElementPresent(carouselImage);
    }

    public boolean isProductDisplayed(){
        return selenium.isElementPresent(product);
    }

    public boolean isProductNameDisplayed(){
        return selenium.isElementPresent(productName);
    }

    public boolean isProductImageDisplayed(){
        return selenium.isElementPresent(productImage);
    }

    public boolean isProductPriceDisplayed(){
        return selenium.isElementPresent(productPrice);
    }

    public boolean isProductAddToCartButtonDisplayed(){
        return selenium.isElementPresent(productAddToCartButton);
    }

    public boolean isProductOverlayDisplayed(){
        return selenium.isElementPresent(productOverlayContent);
    }

    public boolean isOverlayPriceDisplayed(){
        return selenium.isElementPresent(overlayPrice);
    }

    public boolean isOverlayNameDisplayed(){
        return selenium.isElementPresent(overlayName);
    }

    public boolean isOverlayAddToCartButtonDisplayed(){
        return selenium.isElementPresent(overlayAddToCartButton);
    }

    public boolean isViewProductButtonDisplayed(){
        return selenium.isElementPresent(viewProductButton);
    }

    public boolean isAutomationHeaderDisplayed(){
        return selenium.isElementPresent(automationHeader);
    }

    public boolean isAutomationDescriptionDisplayed(){
        return selenium.isElementPresent(automationDescription);
    }

    public boolean isAutomationTagLineDisplayed(){
        return selenium.isElementPresent(automationTagLine);
    }

    public boolean isCategoryHeaderDisplayed(){
        return selenium.isElementPresent(categoryHeader);
    }

    public boolean isBrandsHeaderDisplayed(){
        return selenium.isElementPresent(brandsHeader);
    }

    public boolean isFeaturedItemsHeaderDisplayed(){
        return selenium.isElementPresent(featuredItemsHeader);
    }

    public void clickOnPreviousSlideIcon() throws InterruptedException {
        selenium.clickOn(previousSlideIcon);
    }

    public void clickOnNextSlideIcon() throws InterruptedException {
        selenium.clickOn(nextSlideIcon);
    }

}

