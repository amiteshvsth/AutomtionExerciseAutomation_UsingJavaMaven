package tests.API;

import API.dataFactory.user.LoginDF;
import API.dataObjects.common.CommonResponseDO;
import API.dataObjects.user.UserRequestDO;
import API.utilities.ApiHelper;
import API.utilities.Endpoints;
import Functional.utilities.Constants;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;


public class AuthTests extends BaseTest {

    @Test
    public void verifyThatWeAreAbleToLoginWithValidCredentials() {

        SoftAssert softAssert = new SoftAssert();
        UserRequestDO userData = LoginDF.getData();
        userData.setEmail(Constants.EXISTING_EMAIL);
        userData.setPassword(Constants.CONSTANT_PASSWORD);
        
        test.info("Sending login request with valid user credentials");
        Response response = ApiHelper.postWithFormParameters(Endpoints.VERIFY_LOGIN, 
                Map.of("email", userData.getEmail(), "password", userData.getPassword()));
        
        CommonResponseDO dto = ApiHelper.parseResponse(response, CommonResponseDO.class);

        test.info("Validating HTTP status code");
        softAssert.assertEquals(response.getStatusCode(), 200,
                "Unexpected HTTP status code.");

        test.info("Validating API response code");
        softAssert.assertEquals(dto.getResponseCode(), 200,
                "Login was not successful.");

        test.info("Validating success message");
        softAssert.assertEquals(dto.getMessage(), "User exists!",
                "Unexpected success message returned.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatLoginFailsWithInvalidUserDetails() {

        SoftAssert softAssert = new SoftAssert();
        UserRequestDO userData = LoginDF.getData();
        
        test.info("Sending login request with invalid credentials");
        Response response = ApiHelper.postWithFormParameters(Endpoints.VERIFY_LOGIN, 
                Map.of("email", userData.getEmail(), "password", userData.getPassword()));
        
        CommonResponseDO dto = ApiHelper.parseResponse(response, CommonResponseDO.class);

        test.info("Validating API response code for invalid user");
        softAssert.assertEquals(dto.getResponseCode(), 404,
                "Unexpected API response code.");

        test.info("Validating error message for invalid user");
        softAssert.assertEquals(dto.getMessage(), "User not found!",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatLoginFailsWithoutEmailParameter() {

        SoftAssert softAssert = new SoftAssert();
        UserRequestDO userData = LoginDF.getData();
        userData.setEmail(Constants.EXISTING_EMAIL);
        userData.setPassword(Constants.CONSTANT_PASSWORD);
        
        test.info("Sending login request without email parameter");
        Response response = ApiHelper.postWithFormParameters(Endpoints.VERIFY_LOGIN, 
                Map.of("password", userData.getPassword()));
        
        CommonResponseDO dto = ApiHelper.parseResponse(response, CommonResponseDO.class);

        test.info("Validating API response code for bad request");
        softAssert.assertEquals(dto.getResponseCode(), 400,
                "Unexpected API response code.");

        test.info("Validating error message for missing parameter");
        softAssert.assertEquals(dto.getMessage(),
                "Bad request, email or password parameter is missing in POST request.",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatLoginFailsWithDeleteRequest() {

        SoftAssert softAssert = new SoftAssert();
        UserRequestDO userData = LoginDF.getData();
        userData.setEmail(Constants.EXISTING_EMAIL);
        userData.setPassword(Constants.CONSTANT_PASSWORD);
        
        test.info("Sending DELETE request to login endpoint");
        Response response = ApiHelper.deleteWithFormParameters(Endpoints.VERIFY_LOGIN, 
                Map.of("email", userData.getEmail(), "password", userData.getPassword()));
        
        CommonResponseDO dto = ApiHelper.parseResponse(response, CommonResponseDO.class);

        test.info("Validating API response code for unsupported method");
        softAssert.assertEquals(dto.getResponseCode(), 405,
                "Unexpected API response code.");

        test.info("Validating error message for unsupported HTTP method");
        softAssert.assertEquals(dto.getMessage(),
                "This request method is not supported.",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }
}
