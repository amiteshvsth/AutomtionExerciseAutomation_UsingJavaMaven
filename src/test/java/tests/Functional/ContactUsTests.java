package tests.Functional;

import Functional.dataFactory.ContactUsDF;
import Functional.pageObject.ContactUsPage;
import Functional.pageObject.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.Functional.base.BaseTest;

public class ContactUsTests extends BaseTest {

    @Test
    public void verifyThatContactUsFormIsSubmittedSuccessfully() throws Exception {

        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);

        step("Navigating to Contact Us page");
        homePage.navigateToContactUsPage();

        step("Validating Contact Us page elements");
        Assert.assertTrue(contactUsPage.isContactUsHeaderVisible(),
                "Contact Us header is not visible.");
        Assert.assertTrue(contactUsPage.isFileUploadOptionVisible(),
                "File upload option is not visible.");
        Assert.assertTrue(contactUsPage.isFeedbackHeaderVisible(),
                "Feedback header is not visible.");
        Assert.assertTrue(contactUsPage.isFeedbackDescriptionVisible(),
                "Feedback description is not visible.");
        Assert.assertTrue(contactUsPage.isFeedbackEmailVisible(),
                "Feedback email text is not visible.");
        Assert.assertTrue(contactUsPage.isSuggestionTextVisible(),
                "Suggestion text is not visible.");
        Assert.assertTrue(contactUsPage.isThankYouTextVisible(),
                "Thank you text is not visible.");
        Assert.assertTrue(contactUsPage.isNoteVisible(),
                "Note section is not visible.");
        Assert.assertTrue(contactUsPage.isGetInTouchTextVisible(),
                "Get in touch text is not visible.");

        step("Submitting Contact Us form with valid details");
        contactUsPage.submitForm(ContactUsDF.getData());

        step("Validating success message after submission");
        Assert.assertEquals(contactUsPage.getSuccessText(),
                "Success! Your details have been submitted successfully.",
                "Success message is not displayed.");

        step("Validating post-submission UI changes");
        Assert.assertFalse(contactUsPage.isFileUploadOptionVisible(),
                "File upload option is still visible after submission.");
        Assert.assertTrue(contactUsPage.isHomeButtonVisible(),
                "Home button is not visible after submission.");

        step("Navigating back to Home page");
        contactUsPage.clickOnHomeButton();

        step("Validating Home page is displayed");
        Assert.assertTrue(homePage.isHomePageLogoDisplayed(),
                "Home page logo is not displayed.");

    }
}