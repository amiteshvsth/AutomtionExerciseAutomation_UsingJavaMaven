package pageObject;


import dataObject.SignUpDO;
import enums.Title;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pageObject.base.BasePage;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    // ================= LOCATORS =================

    // Title
    private final By titleMr = By.id("id_gender1");
    private final By titleMrs = By.id("id_gender2");

    // Account Info
    private final By name = By.id("name");
    private final By password = By.id("password");

    // DOB
    private final By days = By.id("days");
    private final By months = By.id("months");
    private final By years = By.id("years");

    // Checkboxes
    private final By newsletter = By.id("newsletter");
    private final By optin = By.id("optin");

    // Address Info
    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By company = By.id("company");
    private final By address1 = By.id("address1");
    private final By address2 = By.id("address2");
    private final By country = By.id("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By mobile = By.id("mobile_number");

    // Submit
    private final By createAccountBtn = By.cssSelector("[data-qa='create-account']");


    // ================= METHODS =================

    public void selectTitle(Title title) throws InterruptedException {
        if (title == Title.MR) {
            selenium.clickOn(titleMr);
        } else {
            selenium.clickOn(titleMrs);
        }
    }

    public void enterName(String value) {
        selenium.enterText(name,value,true);
    }

    public void enterPassword(String value) {
        selenium.enterText(password,value,true);
    }

    public void selectDOB(String day, String month, String year) {
        selenium.selectDropDownValueByText(days,day);
        selenium.selectDropDownValueByText(months,month);
        selenium.selectDropDownValueByText(years,year);
    }

    public void setNewsletter(boolean value) throws InterruptedException {
        if (value) selenium.clickOn(newsletter);
    }

    public void setOptin(boolean value) throws InterruptedException {
        if (value)
            selenium.clickOn(optin);
    }

    public void enterAddressInfo(SignUpDO data) {

        selenium.enterText(firstName,data.getFirstName(),true);
        selenium.enterText(lastName,data.getLastName(),true);
        selenium.enterText(company,data.getCompany(),true);
        selenium.enterText(address1,data.getAddress1(),true);
        selenium.enterText(address2,data.getAddress2(),true);

        selenium.selectDropDownValueByText(country,data.getCountry().toString());

        selenium.enterText(state,data.getState(),true);
        selenium.enterText(city,data.getCity(),true);
        selenium.enterText(zipcode,data.getZipcode(),true);
        selenium.enterText(mobile,data.getMobile(),true);
    }

    public void clickCreateAccount() throws InterruptedException {
        selenium.clickOn(createAccountBtn);
    }

    // 🔥 MAIN BUSINESS METHOD
    public void createAccount(SignUpDO data) throws InterruptedException {

        selectTitle(data.getTitle());
        enterName(data.getFirstName());
        enterPassword(data.getPassword());
        selectDOB(data.getDay(), data.getMonth(), data.getYear());
        setNewsletter(data.isNewsletter());
        setOptin(data.isOption());
        enterAddressInfo(data);
        clickCreateAccount();
    }
}

