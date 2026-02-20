package Functional.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import Functional.pageObject.base.BasePage;

import java.util.List;

public class CommonPage extends BasePage {
    public CommonPage(WebDriver driver) {super(driver);}

    private final By productsLink = By.xpath("//a[contains(@href,'products')]");
    private final By loginLink = By.xpath("//a[contains(@href,'login')]");
    private final By logoutLink = By.xpath("//a[contains(@href,'logout')]");
    private final By deleteAccount = By.xpath("//a[contains(@href,'delete_account')]");
    private final By testCasesPage = By.xpath("//a[contains(@href,'test_cases')]");
    private final By apiTestingPage = By.xpath("//a[contains(@href,'api_list')]");
    private final By homePage = By.className("fa-home");

    private final By videoTutorialsPage = By.className("fa-youtube-play");
    private final By contactUsPage = By.className("fa-envelope");
    private final By userName = By.xpath("//i[@class='fa fa-user']/following-sibling::b");
    private final By cartLink = By.xpath("//a[contains(@href,'view_cart')]");
    private final By logo = By.xpath("//img[@alt='Website for automation practice']");
    private final By subscriptionForm = By.className("searchform");
    private final By subscribeEmailInput = By.id("susbscribe_email");
    private final By emailSubmitIcon = By.xpath("//button[@id='subscribe']");
    private final By footerText = By.xpath("//p[normalize-space()='Get the most recent updates from our site and be updated your self...']");
    private final By copyrightText = By.xpath("//p[@class='pull-left']");
    private final By scrollToTopIcon = By.xpath("//a[@id='scrollUp']");
    private final By successMessage = By.id("success-subscribe");

    public void goToProductsPage() throws InterruptedException { selenium.clickOn(productsLink); }
    public void goToLoginPage() throws InterruptedException{ selenium.clickOn(loginLink); }
    public void goToCartPage() throws InterruptedException{ selenium.clickOn(cartLink); }
    public void logoutUser() throws InterruptedException{ selenium.clickOn(logoutLink); }
    public void deleteUserAccount() throws InterruptedException{ selenium.clickOn(deleteAccount); }
    public void goToTestCasesPage() throws InterruptedException{ selenium.clickOn(testCasesPage); }
    public void goToHomePage() throws InterruptedException{ selenium.clickOn(homePage); }
    public void goToApiTestingPage() throws InterruptedException{ selenium.clickOn(apiTestingPage); }
    public void goToVideoTutorialsPage() throws InterruptedException{
        selenium.clickOn(videoTutorialsPage);
        selenium.waitForJavascriptToLoad();
    }
    public void goToContactUsPage() throws InterruptedException{ selenium.clickOn(contactUsPage); }
    public String getLoggedInUserName() { return selenium.getText(userName); }
    public boolean isHomePageLogoDisplayed() {return selenium.isElementPresent(logo);}

    public boolean isSubscriptionFormDisplayed(){
        selenium.moveToElement(subscriptionForm);
        return selenium.isElementPresent(subscriptionForm);
    }

    public boolean isSubscriptionEmailInputDisplayed(){
        selenium.moveToElement(subscriptionForm);
        return selenium.isElementPresent(subscribeEmailInput);
    }

    public boolean isEmailSubmitIconDisplayed(){
        selenium.moveToElement(subscriptionForm);
        return selenium.isElementPresent(emailSubmitIcon);
    }

    public boolean isFooterTextDisplayed(){
        selenium.moveToElement(subscriptionForm);
        return selenium.isElementPresent(footerText);
    }

    public boolean isCopyrightTextDisplayed(){
        selenium.moveToElement(subscriptionForm);
        return selenium.isElementPresent(copyrightText);
    }

    public boolean isScrollToTopIconDisplayed(){
        selenium.moveToElement(subscriptionForm);
        return selenium.isElementPresent(scrollToTopIcon);
    }

    public boolean isDeleteAccountLinkDisplayed(){return selenium.isElementPresent(deleteAccount);}

    public boolean isLogoutLinkDisplayed(){
        return selenium.isElementPresent(logoutLink);
    }

    public void submitSubscribeEmailForm() throws InterruptedException {
        selenium.enterText(subscribeEmailInput,"amiteshvashishth@yopmail.com",true);
        selenium.clickOn(emailSubmitIcon);
    }

    public void clickOnScrollToTopIcon() throws InterruptedException {
        selenium.clickOn(scrollToTopIcon);
    }

    public boolean isSuccessMessageDisplayed() {
        return selenium.isElementPresent(successMessage);
    }



}
