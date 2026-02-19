package tests.Functional;

import Functional.dataFactory.ContactUsDF;
import org.testng.Assert;
import org.testng.annotations.Test;
import Functional.pageObject.ContactUsPage;
import Functional.pageObject.HomePage;
import tests.Functional.base.BaseTest;

public class ContactUsTests extends BaseTest {

    @Test
    public void VerifyThatContactUsFormIsSubmittedSuccessfully() throws Exception
    {
        HomePage homePage = new HomePage(driver);
        ContactUsPage contactUsPage = new ContactUsPage(driver);
        homePage.goToContactUsPage();
        Assert.assertTrue(contactUsPage.isContactUsHeadervisible(), "Contact Us Header is not visible");
        Assert.assertTrue(contactUsPage.isfileUploadOptionVisible(), "File Upload Option is not visible");
        Assert.assertTrue(contactUsPage.isFeedbackHeadervisible(), "Feedback Header is not visible");
        Assert.assertTrue(contactUsPage.isFeedbackDescriptionvisible(), "Feedback Description is not visible");
        Assert.assertTrue(contactUsPage.isFeedbackEmailvisible(), "Feedback Email is not visible");
        Assert.assertTrue(contactUsPage.isSuggestionTextvisible(), "Suggestion Text is not visible");
        Assert.assertTrue(contactUsPage.isThankyouTextvisible(), "Thank You Text is not visible");
        Assert.assertTrue(contactUsPage.isNotevisible(), "Note is not visible");
        Assert.assertTrue(contactUsPage.isGetInTouchTextvisible(), "Get In Touch Text is not visible");

        contactUsPage.submitForm(ContactUsDF.fillContactUsDetails());
        Assert.assertEquals(contactUsPage.getSuccessText(), "Success! Your details have been submitted successfully.", "Sucess text not displayed");
        Assert.assertFalse(contactUsPage.isfileUploadOptionVisible(), "File Upload Option is still visible");
        Assert.assertTrue(contactUsPage.isHomeButtonvisible(), "Home Button is not visible");
        contactUsPage.clickOnHomeButton();
        Assert.assertTrue(homePage.isHomePageLogoDisplayed(),"Home page logo not displayed");
    }
}
