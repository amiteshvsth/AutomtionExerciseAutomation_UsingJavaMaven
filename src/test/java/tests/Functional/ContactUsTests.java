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

        test.info("===== TEST START: Verify Contact Us form submission =====");

        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);

        test.info("Step 1: Navigating to Contact Us page");
        homePage.goToContactUsPage();

        test.info("Step 2: Validating Contact Us page elements");
        Assert.assertTrue(contactUsPage.isContactUsHeadervisible(),
                "Contact Us header is not visible.");
        Assert.assertTrue(contactUsPage.isfileUploadOptionVisible(),
                "File upload option is not visible.");
        Assert.assertTrue(contactUsPage.isFeedbackHeadervisible(),
                "Feedback header is not visible.");
        Assert.assertTrue(contactUsPage.isFeedbackDescriptionvisible(),
                "Feedback description is not visible.");
        Assert.assertTrue(contactUsPage.isFeedbackEmailvisible(),
                "Feedback email text is not visible.");
        Assert.assertTrue(contactUsPage.isSuggestionTextvisible(),
                "Suggestion text is not visible.");
        Assert.assertTrue(contactUsPage.isThankyouTextvisible(),
                "Thank you text is not visible.");
        Assert.assertTrue(contactUsPage.isNotevisible(),
                "Note section is not visible.");
        Assert.assertTrue(contactUsPage.isGetInTouchTextvisible(),
                "Get in touch text is not visible.");

        test.info("Step 3: Submitting Contact Us form with valid details");
        contactUsPage.submitForm(ContactUsDF.fillContactUsDetails());

        test.info("Step 4: Validating success message after submission");
        Assert.assertEquals(contactUsPage.getSuccessText(),
                "Success! Your details have been submitted successfully.",
                "Success message is not displayed.");

        test.info("Step 5: Validating post-submission UI changes");
        Assert.assertFalse(contactUsPage.isfileUploadOptionVisible(),
                "File upload option is still visible after submission.");
        Assert.assertTrue(contactUsPage.isHomeButtonvisible(),
                "Home button is not visible after submission.");

        test.info("Step 6: Navigating back to Home page");
        contactUsPage.clickOnHomeButton();

        test.info("Step 7: Validating Home page is displayed");
        Assert.assertTrue(homePage.isHomePageLogoDisplayed(),
                "Home page logo is not displayed.");

        test.info("===== TEST PASSED: Contact Us form submitted successfully =====");
    }
}