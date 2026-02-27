package tests.API;

import API.dataFactory.user.UserRequestDF;
import API.services.AuthService;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AuthTests extends BaseTest {

    @Test
    public void verifyThatWeAreAbleToLoginWithValidCredentials() {

        test.info("===== TEST START: Verify login with valid credentials =====");

        AuthService authService = new AuthService();

        test.info("Step 1: Sending login request with valid user credentials");
        var response = authService.login(UserRequestDF.setValidLoginDetails());

        test.info("Step 2: Validating HTTP status code");
        Assert.assertEquals(response.getStatusCode(), 200,
                "Unexpected HTTP status code.");

        test.info("Step 3: Validating API response code");
        Assert.assertEquals(response.getDto().getResponseCode(), 200,
                "Login was not successful.");

        test.info("Step 4: Validating success message");
        Assert.assertEquals(response.getDto().getMessage(), "User exists!",
                "Unexpected success message returned.");

        test.info("===== TEST PASSED: Login successful with valid credentials =====");
    }


    @Test
    public void verifyThatLoginFailsWithInvalidUserDetails() {

        test.info("===== TEST START: Verify login failure with invalid credentials =====");

        AuthService authService = new AuthService();

        test.info("Step 1: Sending login request with invalid credentials");
        var response = authService.login(UserRequestDF.setInvalidLoginDetails());

        test.info("Step 2: Validating API response code for invalid user");
        Assert.assertEquals(response.getDto().getResponseCode(), 404,
                "Unexpected API response code.");

        test.info("Step 3: Validating error message for invalid user");
        Assert.assertEquals(response.getDto().getMessage(), "User not found!",
                "Unexpected error message returned.");

        test.info("===== TEST PASSED: Proper error returned for invalid login =====");
    }


    @Test
    public void verifyThatLoginFailsWithoutEmailParameter() {

        test.info("===== TEST START: Verify login failure when email parameter is missing =====");

        AuthService authService = new AuthService();

        test.info("Step 1: Sending login request without email parameter");
        var response = authService.loginWithoutEmailParameter(
                UserRequestDF.setValidLoginDetails());

        test.info("Step 2: Validating API response code for bad request");
        Assert.assertEquals(response.getDto().getResponseCode(), 400,
                "Unexpected API response code.");

        test.info("Step 3: Validating error message for missing parameter");
        Assert.assertEquals(response.getDto().getMessage(),
                "Bad request, email or password parameter is missing in POST request.",
                "Unexpected error message returned.");

        test.info("===== TEST PASSED: Proper validation error returned for missing email =====");
    }


    @Test
    public void verifyThatLoginFailsWithDeleteRequest() {

        test.info("===== TEST START: Verify login fails when using unsupported HTTP method =====");

        AuthService authService = new AuthService();

        test.info("Step 1: Sending DELETE request to login endpoint");
        var response = authService.loginDelete(
                UserRequestDF.setValidLoginDetails());

        test.info("Step 2: Validating API response code for unsupported method");
        Assert.assertEquals(response.getDto().getResponseCode(), 405,
                "Unexpected API response code.");

        test.info("Step 3: Validating error message for unsupported HTTP method");
        Assert.assertEquals(response.getDto().getMessage(),
                "This request method is not supported.",
                "Unexpected error message returned.");

        test.info("===== TEST PASSED: Unsupported HTTP method properly rejected =====");
    }
}