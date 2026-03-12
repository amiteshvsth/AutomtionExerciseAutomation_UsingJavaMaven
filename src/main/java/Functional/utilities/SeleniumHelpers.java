package Functional.utilities;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class SeleniumHelpers extends WaitHelpers {
    JavaHelpers helper;
    Actions actions;

    public SeleniumHelpers(WebDriver driver) {
        super(driver);
        helper = new JavaHelpers();
        actions = new Actions(driver);
    }

    // Take screenshot

    /**
     * Take screenshot of the web page
     *
     * @param fileName screenshot file name
     * @throws IOException ioException
     */
    public String takeScreenshot(String fileName) throws IOException {
        String screenshotPath = Constants.SCREENSHOT_LOCATION + File.separator + fileName + helper.getTimeStamp("_yyyyMMdd_HHmmss") + ".png";
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(scrFile, new File(screenshotPath));
        return screenshotPath;
    }

    //Navigation
    public void navigateToPage(String url) {
        driver.get(url);
    }



    /**
     * Get Text from field
     *
     * @param by By object
     * @return text from field
     */
    public String getText(By by) {
        return waitTillElementIsVisible(by, Constants.WEBDRIVER_WAIT_DURATION).getText().trim();
    }

    //Elements

    /**
     * Click on Element
     *
     * @param e WebElement object
     * @throws InterruptedException interruptedException
     */
    public void clickOn(WebElement e) throws InterruptedException {
        waitTillElementIsClickable(e).click();
    }

    /**
     * Click on Element
     *
     * @param by By object
     * @throws InterruptedException interruptedException
     */
    public void clickOn(By by) throws InterruptedException {
        waitTillElementIsClickable(by).click();
    }


    /**
     * method verify whether element is present on screen
     *
     * @param targetElement element to be present
     * @return true if element is present else throws exception
     */
    public Boolean isElementPresent(By targetElement) {
        WebElement element = waitInCaseElementVisible(targetElement, Constants.WEBDRIVER_WAIT_DURATION);
        return element != null && element.isDisplayed();
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }



    /**
     * Enter text to input field
     *
     * @param by    By object
     * @param text  input text
     * @param clear set true if you want to clear field else set false
     */
    public void enterText(By by, String text, boolean clear) {
        WebElement e = waitTillElementIsClickable(by);
        if (clear) {
            e.clear();
        }
        e.sendKeys(text);
    }

    public void selectDropDownValueByText(By by, String text) {
        WebElement e = waitTillElementIsClickable(by);
        new Select(e).selectByVisibleText(text);
    }


    public void moveToElement(By targetElement) {
        WebElement element = driver.findElement(targetElement);
        actions.moveToElement(element).perform();
    }


    public void pageScrollInView(WebElement e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
    }




    /**
     * Accepts the currently open alert.
     * <p>
     * This method is used to accept alerts that are triggered by the application.
     * It switches to the alert and then accepts it.
     */
    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }


    /**
     * method verify whether element is present on screen * * @param targetElement element to be present * @return true if element is present else throws exception
     */
    public Boolean isElementPresent(WebElement targetElement) {
        try {
            WebElement element = waitInCaseElementVisible(targetElement, Constants.WEBDRIVER_WAIT_DURATION);
            return element != null && element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public WebElement waitInCaseElementVisible(WebElement e, int waitDurationInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitDurationInSeconds));
        try {
            return wait.until(ExpectedConditions.visibilityOf(e));
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Wait for specified duration and check if element is visible or not * * @param by                    By object * @param waitDurationInSeconds wait duration in seconds * @return WebElement if visible or null if not visible
     */
    public WebElement waitInCaseElementVisible(By by, int waitDurationInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitDurationInSeconds));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception ex) {
            return null;
        }
    }

}