package tests.API;

import API.client.ApiResponse;
import API.dataFactory.user.LoginDF;
import API.dataObjects.common.CommonResponseDO;
import API.dataObjects.user.UserRequestDO;
import API.services.AuthService;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class AuthTests extends BaseTest {

    @Test
    public void verifyThatWeAreAbleToLoginWithValidCredentials() {

        AuthService authService = new AuthService();

        SoftAssert softAssert = new SoftAssert();
        UserRequestDO userData = LoginDF.getData();
        userData.setEmail("amiteshvashishthh@yopmail.com");
        userData.setPassword("12345678");
        test.info("Sending login request with valid user credentials");
        ApiResponse<CommonResponseDO> response = authService.login(userData);

        test.info("Validating HTTP status code");
        softAssert.assertEquals(response.getStatusCode(), 200,
                "Unexpected HTTP status code.");

        test.info("Validating API response code");
        softAssert.assertEquals(response.getDto().getResponseCode(), 200,
                "Login was not successful.");

        test.info("Validating success message");
        softAssert.assertEquals(response.getDto().getMessage(), "User exists!",
                "Unexpected success message returned.");

        softAssert.assertAll();

        //Response response1 = RestAssured.given().spec().body().when().post(end);
    }


    @Test
    public void verifyThatLoginFailsWithInvalidUserDetails() {


        AuthService authService = new AuthService();
        SoftAssert softAssert = new SoftAssert();
        UserRequestDO userData = LoginDF.getData();
        test.info("Sending login request with invalid credentials");
        ApiResponse<CommonResponseDO> response = authService.login(userData);

        test.info("Validating API response code for invalid user");
        softAssert.assertEquals(response.getDto().getResponseCode(), 404,
                "Unexpected API response code.");

        test.info("Validating error message for invalid user");
        softAssert.assertEquals(response.getDto().getMessage(), "User not found!",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatLoginFailsWithoutEmailParameter() {


        AuthService authService = new AuthService();

        SoftAssert softAssert = new SoftAssert();
        UserRequestDO userData = LoginDF.getData();
        userData.setEmail("amiteshvashishthh@yopmail.com");
        userData.setPassword("12345678");
        test.info("Sending login request without email parameter");
        ApiResponse<CommonResponseDO> response = authService.loginWithoutEmailParameter(userData);

        test.info("Validating API response code for bad request");
        softAssert.assertEquals(response.getDto().getResponseCode(), 400,
                "Unexpected API response code.");

        test.info("Validating error message for missing parameter");
        softAssert.assertEquals(response.getDto().getMessage(),
                "Bad request, email or password parameter is missing in POST request.",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatLoginFailsWithDeleteRequest() {


        AuthService authService = new AuthService();

        SoftAssert softAssert = new SoftAssert();
        UserRequestDO userData = LoginDF.getData();
        userData.setEmail("amiteshvashishthh@yopmail.com");
        userData.setPassword("12345678");
        test.info("Sending DELETE request to login endpoint");
        ApiResponse<CommonResponseDO> response = authService.loginDelete(userData);

        test.info("Validating API response code for unsupported method");
        softAssert.assertEquals(response.getDto().getResponseCode(), 405,
                "Unexpected API response code.");

        test.info("Validating error message for unsupported HTTP method");
        softAssert.assertEquals(response.getDto().getMessage(),
                "This request method is not supported.",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }
}