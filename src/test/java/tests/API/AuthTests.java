package tests.API;

import API.client.ApiResponse;
import API.dataFactory.user.UserRequestDF;
import API.dataObjects.response.common.CommonResponseDO;
import API.services.AuthService;
import org.testng.annotations.Test;


public class AuthTests extends BaseTest {

    @Test
    public void verifyThatWeAreAbleToLoginWithValidCredentials() {

        test.info("===== TEST START: Verify login with valid credentials =====");

        AuthService authService = new AuthService();

        test.info("Step 1: Sending login request with valid user credentials");
        ApiResponse<CommonResponseDO> response = authService.login(UserRequestDF.setValidLoginDetails());

        test.info("Step 2: Validating HTTP status code");
        softAssert.assertEquals(response.getStatusCode(), 200,
                "Unexpected HTTP status code.");

        test.info("Step 3: Validating API response code");
        softAssert.assertEquals(response.getDto().getResponseCode(), 200,
                "Login was not successful.");

        test.info("Step 4: Validating success message");
        softAssert.assertEquals(response.getDto().getMessage(), "User exists!",
                "Unexpected success message returned.");

        softAssert.assertAll();
        test.info("===== TEST PASSED: Login successful with valid credentials =====");

        //Response response1 = RestAssured.given().spec().body().when().post(end);
    }


    @Test
    public void verifyThatLoginFailsWithInvalidUserDetails() {

        test.info("===== TEST START: Verify login failure with invalid credentials =====");

        AuthService authService = new AuthService();

        test.info("Step 1: Sending login request with invalid credentials");
        ApiResponse<CommonResponseDO> response = authService.login(UserRequestDF.setInvalidLoginDetails());

        test.info("Step 2: Validating API response code for invalid user");
        softAssert.assertEquals(response.getDto().getResponseCode(), 404,
                "Unexpected API response code.");

        test.info("Step 3: Validating error message for invalid user");
        softAssert.assertEquals(response.getDto().getMessage(), "User not found!",
                "Unexpected error message returned.");

        softAssert.assertAll();
        test.info("===== TEST PASSED: Proper error returned for invalid login =====");
    }


    @Test
    public void verifyThatLoginFailsWithoutEmailParameter() {

        test.info("===== TEST START: Verify login failure when email parameter is missing =====");

        AuthService authService = new AuthService();

        test.info("Step 1: Sending login request without email parameter");
        ApiResponse<CommonResponseDO> response = authService.loginWithoutEmailParameter(
                UserRequestDF.setValidLoginDetails());

        test.info("Step 2: Validating API response code for bad request");
        softAssert.assertEquals(response.getDto().getResponseCode(), 400,
                "Unexpected API response code.");

        test.info("Step 3: Validating error message for missing parameter");
        softAssert.assertEquals(response.getDto().getMessage(),
                "Bad request, email or password parameter is missing in POST request.",
                "Unexpected error message returned.");

        softAssert.assertAll();
        test.info("===== TEST PASSED: Proper validation error returned for missing email =====");
    }


    @Test
    public void verifyThatLoginFailsWithDeleteRequest() {

        test.info("===== TEST START: Verify login fails when using unsupported HTTP method =====");

        AuthService authService = new AuthService();

        test.info("Step 1: Sending DELETE request to login endpoint");
        ApiResponse<CommonResponseDO> response = authService.loginDelete(
                UserRequestDF.setValidLoginDetails());

        test.info("Step 2: Validating API response code for unsupported method");
        softAssert.assertEquals(response.getDto().getResponseCode(), 405,
                "Unexpected API response code.");

        test.info("Step 3: Validating error message for unsupported HTTP method");
        softAssert.assertEquals(response.getDto().getMessage(),
                "This request method is not supported.",
                "Unexpected error message returned.");

        softAssert.assertAll();
        test.info("===== TEST PASSED: Unsupported HTTP method properly rejected =====");
    }
}