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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeleniumHelpers extends WaitHelpers {
    JavaHelpers helper;
    Actions actions;
    Random random = new Random();

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

    public void closeAdvertisement() {

        try {
            List<WebElement> iframes = driver.findElements(By.xpath("//iframe[@title='Advertisement']"));

            for (WebElement frame : iframes) {

                driver.switchTo().frame(frame);
                List<WebElement> closeBtns =
                        driver.findElements(By.xpath("//div[@id='dismiss-button']/div"));

                if (!closeBtns.isEmpty()) {
                    closeBtns.getFirst().click();
                    driver.switchTo().defaultContent();
                    return;
                }
                driver.switchTo().defaultContent();
            }

        } catch (Exception ignored) {}
    }

    //Navigation
    public void navigateToPage(String url) {
        driver.get(url);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void navigateToBackPage() {
        driver.navigate().back();
    }

    /**
     * Get Text from field
     *
     * @param e WebElement object
     * @return text from field
     */
    public String getText(WebElement e) {
        return waitTillElementIsVisible(e).getText().trim();
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
     * Enter text to input field
     *
     * @param e     WebElement object
     * @param text  input text
     * @param clear set true if you want to clear field else set false
     */
    public void enterText(WebElement e, String text, boolean clear) {
        if (clear) {
            e.clear();
        }
        e.sendKeys(text);
    }

    public void clearTextFieldUsingKeyboard(WebElement element) {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
    }


    /**
     * Enter text to input field
     *
     * @param e     WebElement object
     * @param text  input int text
     * @param clear set true if you want to clear field else set false
     */
    public void enterText(WebElement e, long text, boolean clear) {
        if (clear) {
            e.clear();
        }
        e.sendKeys(String.valueOf(text));
    }

    /**
     * Enter text to input field
     *
     * @param e    WebElement object
     * @param text input text
     */
    public void uploadImage(WebElement e, String text) {
        e.sendKeys(Constants.UPLOAD_FOLDER + File.separator + text);
    }

    /**
     * To get Element attribute value
     *
     * @param e             WebElement object
     * @param attributeName attribute name e.g. style
     * @return attribute present or not
     */
    public boolean isElementAttributePresent(WebElement e, String attributeName) {
        return e.getDomAttribute(attributeName) != null;
    }

    /**
     * To get Element attribute value
     *
     * @param e             WebElement Object
     * @param attributeName attribute name
     * @return attribute value
     */
    public String getElementAttributeValue(WebElement e, String attributeName) {
        if (isElementAttributePresent(e, attributeName)) {
            return e.getDomAttribute(attributeName);
        }
        return "Attribute" + attributeName + " not found";
    }

    /**
     * To get Element attribute value
     *
     * @param e             WebElement Object
     * @param attributeName attribute name
     * @return attribute value
     */
    public String getElementAttributeValue(By e, String attributeName) {
        WebElement element = driver.findElement(e);
        if (isElementAttributePresent(element, attributeName)) {
            return element.getDomAttribute(attributeName);
        }
        return "Attribute" + attributeName + " not found";
    }

    public void pressEscKey() {
        actions.sendKeys(Keys.ESCAPE).perform();
    }

    public String getComputedFieldValue(WebElement e) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;", e);
    }

    public String getComputedFieldValue(By by) {
        WebElement element = driver.findElement(by);
        return ((JavascriptExecutor) driver).executeScript("return arguments[0].value;", element).toString();
    }

    public void clearTextBoxUsingJavaScript(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
    }

    public void clearTextBoxUsingJavaScript(By by) {
        WebElement element = driver.findElement(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
    }

    /**
     * Click on Element
     *
     * @param e WebElement object
     * @throws InterruptedException interruptedException
     */
    public void clickOn(WebElement e) throws InterruptedException {
        waitTillElementIsClickable(e).click();
//        waitForJavascriptToLoad();
    }

    public boolean isClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Click on Element
     *
     * @param e WebElement object
     */
    public void clickOnWithoutWait(WebElement e) {
        e.click();
    }

    /**
     * Click on Element
     *
     * @param by By object
     * @throws InterruptedException interruptedException
     */
    public void clickOn(By by) throws InterruptedException {
        waitTillElementIsClickable(by).click();
//        waitForJavascriptToLoad();
//        closeAdvertisement();
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element != null && element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
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

    public void javascriptClickOn(WebElement e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
    }

    public void javascriptClickOn(By targetElement) {
        WebElement element = driver.findElement(targetElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
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

    public String selectDropDownValueByIndex(WebElement e, int index) {
        new Select(e).selectByIndex(index);
        return new Select(e).getFirstSelectedOption().getText().trim();
    }

    public String getFirstSelectedOption(WebElement e) {
        return new Select(e).getFirstSelectedOption().getText();
    }

    public void selectDropDownValueByText(WebElement e, String text) {
        new Select(e).selectByVisibleText(text);
    }

    public void selectDropDownValueByText(By by, String text) {
        WebElement e = waitTillElementIsClickable(by);
        new Select(e).selectByVisibleText(text);
    }

    public String selectDropdownValueRandom(WebElement e) {
        List<WebElement> options = new Select(e).getOptions();
        int index;

        // Skip the default "Please Select" option
        do {
            index = random.nextInt(options.size());
        } while (Objects.equals(options.get(index).getDomAttribute("value"), "-1"));

        WebElement selectedOption = options.get(index);
        String value = selectedOption.getDomAttribute("value");
        String text = selectedOption.getText();

        new Select(e).selectByValue(value); // Use value to select
        return text; // Return visible text
    }

    public String selectDropdownValueRandom(By by) {
        WebElement e = waitTillElementIsClickable(by);
        List<WebElement> options = new Select(e).getOptions();
        int index;
        String val;

        // Skip "Please Select" and "Custom" options
        do {
            index = random.nextInt(options.size());
            val = options.get(index).getDomAttribute("value");
        } while (Objects.equals(val, "-1") || Objects.equals(val, "Custom"));

        WebElement selectedOption = options.get(index);
        String value = selectedOption.getDomAttribute("value");
        String text = selectedOption.getText();

        new Select(e).selectByValue(value); // Use value to select
        return text; // Return visible text
    }

    //Slider Movement
    public void clickOnMoveToElement(WebElement element, int xOffset, int yOffset) {
        actions.moveToElement(element, xOffset, yOffset)
                .click()
                .build()
                .perform();
    }


    public void moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public void moveToElement(By targetElement) {
        WebElement element = driver.findElement(targetElement);
        actions.moveToElement(element).perform();
    }

    public void moveToElementAndClick(WebElement element) {
        actions.moveToElement(element).click().build().perform();
    }

    /**
     * Hovers over the specified element.
     *
     * @param element the element to hover over
     */
    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void pageScrollInView(WebElement e) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
    }

    //drag and drop
    public void dragAndDrop(WebElement drag, WebElement drop) {
        actions.dragAndDrop(drag, drop).build().perform();
    }

    public void dragAndDrop(By drag, By drop) {
        WebElement dragElement = driver.findElement(drag);
        WebElement dropElement = driver.findElement(drop);
        actions.dragAndDrop(dragElement, dropElement).build().perform();
    }

    public void dragAndDropWithPause(WebElement source, WebElement target) {
        actions.clickAndHold(source).moveToElement(target).pause(Duration.ofSeconds(10)).release().build().perform();
    }

    public void moveTheMapPinWithOffsetSteps(WebElement pin, int steps, int xOffset, int yOffset) throws InterruptedException {
        actions.clickAndHold(pin);

        for (int i = 0; i < steps; i++) {
            actions.moveByOffset(xOffset, yOffset);
            hardWait(2);
        }

        actions.release().build().perform();
    }

    public void dragAndDropWithOffSet(WebElement source, WebElement target, int xOffset, int yOffset) {
        actions.clickAndHold(source)
                .moveToElement(target)
                .moveByOffset(xOffset, yOffset)
                .pause(10)
                .release()
                .perform();
    }

    // iFrames
    public void switchToIframe(String iframeId) {
        driver.switchTo().frame(iframeId);
    }

    public void switchToIframe(int iframeIndex) {
        driver.switchTo().frame(iframeIndex);
    }

    public void switchToIframe(WebElement e) {
        driver.switchTo().frame(e);
    }

    public void switchToMainIframe() {
        driver.switchTo().defaultContent();
    }

    //Browsers

    public void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open('','_blank');");
    }

    // Browser's Tab handler
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public void switchToWindow(int tabNumber) {
        int i = 1;
        for (String winHandle : getWindowHandles()) {
            driver.switchTo().window(winHandle);
            if (i == tabNumber) break;
            i++;
        }
    }

    public void switchToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public void pressDownKeyAndEnter() {
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        actions.sendKeys(Keys.ENTER).perform();
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
    public boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            return alert != null;
        } catch (TimeoutException e) {
            return false;
        }
    }
    /**
     * Retrieves the text from the currently open alert.
     * <p>
     * This method is used to get the text of alerts that are triggered by the application.
     * It switches to the alert and then retrieves its text.
     *
     * @return The text of the alert.
     */
    public String getTextFromAlert() {
        return driver.switchTo().alert().getText().trim();
    }

    /**
     * Verifies whether all elements in the given list are displayed on the page.
     *
     * @param elements the list of elements to check
     * @return true if all elements are displayed, false otherwise
     */
    public boolean areElementsDisplayed(List<WebElement> elements) {
        if (elements == null || elements.isEmpty()) {
            return false;
        }
        for (WebElement element : elements) {
            if (!element.isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Draws a signature by moving the mouse cursor over the specified WebElement
     * using a sequence of offset coordinates.
     *
     * @param element the WebElement (usually a canvas) where the signature should be drawn
     * @param offsets a 2D array of int values representing the x and y offsets for drawing the signature
     */
    public void drawSignature(WebElement element, int[][] offsets) {
        actions.moveToElement(element).clickAndHold();
        for (int[] offset : offsets) {
            actions.moveByOffset(offset[0], offset[1]);
        }
        actions.release().perform();
    }

    /**
     * Switches to a newly opened browser window after clicking an element
     * that triggers a new window/tab. Waits until the new window is available
     * and returns the handle of the parent/original window for later use.
     *
     * @param driver The WebDriver instance
     * @return The handle of the parent/original window
     */
    public String switchToNewWindowAfterClick(WebDriver driver) {
        String parentWindow = getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(d -> d.getWindowHandles().size() > 1);
        for (String windowHandle : getWindowHandles()) {
            if (!windowHandle.equals(parentWindow)) {
                switchToWindow(windowHandle);
                break;
            }
        }
        return parentWindow;
    }

    public void delayBrowser(WebElement element) {
        waitTillElementIsClickable(element).click();

        try {
            acceptAlert();
        } catch (NoAlertPresentException e) {
            System.out.println("Alert dismissed too quickly or not triggered.");
        }
    }

    public String getAlertTextWithElementClick(WebElement element) {
        waitTillElementIsClickable(element).click();
        String alertText = "";
        try {
            alertText = getTextFromAlert();
            acceptAlert();
        } catch (NoAlertPresentException e) {
            System.out.println("Alert dismissed too quickly or not triggered.");
        }
        return alertText;
    }

    public void delayBrowser(By element) {
        waitTillElementIsClickable(element).click();

        try {
            acceptAlert();
        } catch (NoAlertPresentException e) {
            System.out.println("Alert dismissed too quickly or not triggered.");
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Performs a double-click action on a web element located by the given selector.
     * Waits until the element is clickable, performs the double-click,
     * and then waits for any JavaScript to finish loading.
     *
     * @param by The By locator of the element to double-click.
     * @throws InterruptedException if thread is interrupted while waiting.
     */
    public void doubleClickOn(By by) throws InterruptedException {
        WebElement element = waitTillElementIsClickable(by);
        actions.doubleClick(element).perform();
        waitForJavascriptToLoad();
    }


    /**
     * Return gettext value of all option inside select tag
     */
    public List<String> getSelectTagOptionText(WebElement e) {
        List<WebElement> options = e.findElements(By.tagName("option"));
        List<String> optionTexts = new ArrayList<>();

        for (WebElement option : options) {
            optionTexts.add(option.getText());
        }

        return optionTexts;
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

    public void doubleClickOn(WebElement webElement) throws InterruptedException {
        WebElement element = waitTillElementIsClickable(webElement);
        actions.doubleClick(element).perform();
        waitForJavascriptToLoad();
    }

    public boolean isElementChecked(WebElement webElement) {
        return (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].checked;", webElement);
    }

    public void eraseLastCharacter(WebElement element) {
        element.sendKeys(Keys.END); // Move cursor to end of text
        element.sendKeys(Keys.BACK_SPACE); // Delete last character
    }

    public void scrollHorizontallyToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({ block: 'nearest', inline: 'center' });",
                element
        );
    }

    public int getDifferenceBetweenTwoStylePixels(Pattern leftPattern, String style, Pattern rightPattern) {
        Matcher leftMatcher = leftPattern.matcher(style);
        Matcher rightMatcher = rightPattern.matcher(style);
        int left = 0, right = 0;
        if (leftMatcher.find()) {
            left = Integer.parseInt(leftMatcher.group(1));
        } else {
            throw new RuntimeException("Left value not found in style attribute.");
        }

        if (rightMatcher.find()) {
            right = Integer.parseInt(rightMatcher.group(1));
        } else {
            throw new RuntimeException("Right value not found in style attribute.");
        }
        return Math.abs(right) - left;
    }
}