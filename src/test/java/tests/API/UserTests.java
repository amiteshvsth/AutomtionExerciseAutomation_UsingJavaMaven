package tests.API;

import API.dataFactory.user.LoginDF;
import API.dataFactory.user.SignUpDF;
import API.dataObjects.user.UserRequestDO;
import API.dataObjects.common.CommonResponseDO;
import API.dataObjects.user.UserDetailsRequestDO;
import API.dataObjects.user.UserResponseDO;
import API.utilities.ApiHelper;
import API.utilities.Endpoints;
import Functional.utilities.Constants;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class UserTests extends BaseTest {

    private Map<String, String> getUserDetails(UserDetailsRequestDO user) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", user.getName());
        requestBody.put("email", user.getEmail());
        requestBody.put("password", user.getPassword());
        requestBody.put("title", user.getTitle());
        requestBody.put("birth_date", user.getBirth_day());
        requestBody.put("birth_month", user.getBirth_month());
        requestBody.put("birth_year", user.getBirth_year());
        requestBody.put("firstname", user.getFirst_name());
        requestBody.put("lastname", user.getLast_name());
        requestBody.put("company", user.getCompany());
        requestBody.put("address1", user.getAddress1());
        requestBody.put("address2", user.getAddress2());
        requestBody.put("country", user.getCountry());
        requestBody.put("zipcode", user.getZipcode());
        requestBody.put("state", user.getState());
        requestBody.put("city", user.getCity());
        requestBody.put("mobile_number", user.getMobile_number());
        return requestBody;
    }

    @Test
    public void verifyUserCanBeRetrievedByEmail() {

        String email = Constants.EXISTING_EMAIL;

        SoftAssert softAssert = new SoftAssert();
        test.info("Sending request to retrieve user by email");
        Response response = ApiHelper.getWithQueryParameters(Endpoints.GET_USER_BY_EMAIL, Map.of("email", email));
        
        UserResponseDO dto = ApiHelper.parseResponse(response, UserResponseDO.class);

        test.info("Validating HTTP and API response codes");
        softAssert.assertEquals(response.getStatusCode(), 200,
                "Unexpected HTTP status code.");
        softAssert.assertEquals(dto.getResponseCode(), 200,
                "Unexpected API response code.");

        test.info("Validating user object is returned");
        softAssert.assertNotNull(dto.getUser(),
                "User object is null.");

        test.info("Validating returned user details");
        UserDetailsRequestDO user = dto.getUser();

        softAssert.assertEquals(user.getEmail(), email, "Email mismatch.");
        softAssert.assertNotNull(user.getId(), "User ID is null.");
        softAssert.assertEquals(user.getName(), "Amitesh Vashishth", "Name mismatch.");
        softAssert.assertEquals(user.getTitle(), "", "Title mismatch.");
        softAssert.assertEquals(user.getBirth_day(), "14", "Birth day mismatch.");
        softAssert.assertEquals(user.getBirth_month(), "1", "Birth month mismatch.");
        softAssert.assertEquals(user.getBirth_year(), "2005", "Birth year mismatch.");
        softAssert.assertEquals(user.getFirst_name(), "RwOnrAdfgd", "First name mismatch.");
        softAssert.assertEquals(user.getLast_name(), "LzcZehGodfgd", "Last name mismatch.");
        softAssert.assertEquals(user.getCompany(),"Amitesh & Sons pvt ltd", "Company field mismatch.");
        softAssert.assertEquals(user.getAddress1(), "B4baqrMaklfgdf", "Address1 mismatch.");
        softAssert.assertEquals(user.getAddress2(), "HmjlptLd8hsdfg", "Address2 mismatch.");
        softAssert.assertEquals(user.getCountry(), "India", "Country mismatch.");
        softAssert.assertEquals(user.getState(), "AUMyJsgdf", "State mismatch.");
        softAssert.assertEquals(user.getCity(), "dafIOsdfg", "City mismatch.");
        softAssert.assertEquals(user.getZipcode(), "993576sdf", "Zipcode mismatch.");

        softAssert.assertAll();
    }


    @Test
    public void verifyUserCannotBeRetrievedByUnknownEmail() {

        String email = "testslugurlamitesh@xyz.com";

        SoftAssert softAssert = new SoftAssert();
        test.info("Sending request with unknown email");
        Response response = ApiHelper.getWithQueryParameters(Endpoints.GET_USER_BY_EMAIL, Map.of("email", email));
        
        CommonResponseDO dto = ApiHelper.parseResponse(response, CommonResponseDO.class);

        test.info("Validating error response");
        softAssert.assertEquals(dto.getResponseCode(), 404,
                "Unexpected API response code.");
        softAssert.assertEquals(dto.getMessage(),
                "Account not found with this email, try another email!",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreAbleToCreateAccountWithValidDetails() {

        SoftAssert softAssert = new SoftAssert();
        UserDetailsRequestDO userData = SignUpDF.getData();
        test.info("Sending account creation request with valid data");
        Response response = ApiHelper.postWithFormParameters(Endpoints.CREATE_ACCOUNT, getUserDetails(userData));
        
        CommonResponseDO dto = ApiHelper.parseResponse(response, CommonResponseDO.class);

        test.info("Validating account creation response");
        softAssert.assertEquals(dto.getResponseCode(), 201,
                "Unexpected API response code.");
        softAssert.assertEquals(dto.getMessage(),
                "User created!",
                "Account creation failed.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreNotAbleToCreateAccountWithInvalidDetails() {

        SoftAssert softAssert = new SoftAssert();
        UserDetailsRequestDO userData = SignUpDF.getData();
        userData.setEmail(Constants.EXISTING_EMAIL);
        test.info("Sending account creation request with invalid data");
        Response response = ApiHelper.postWithFormParameters(Endpoints.CREATE_ACCOUNT, getUserDetails(userData));
        
        CommonResponseDO dto = ApiHelper.parseResponse(response, CommonResponseDO.class);

        test.info("Validating failure response");
        softAssert.assertEquals(dto.getResponseCode(), 400,
                "Unexpected API response code.");
        softAssert.assertEquals(dto.getMessage(),
                "Email already exists!",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreAbleToDeleteAccountWithValidDetails() {

        SoftAssert softAssert = new SoftAssert();
        test.info("Creating user before deletion");
        UserDetailsRequestDO userData = SignUpDF.getData();
        ApiHelper.postWithFormParameters(Endpoints.CREATE_ACCOUNT, getUserDetails(userData));

        test.info("Preparing delete request");
        Map<String, String> deleteParams = Map.of(
                "email", userData.getEmail(),
                "password", userData.getPassword()
        );

        test.info("Sending delete request");
        Response response = ApiHelper.deleteWithFormParameters(Endpoints.DELETE_ACCOUNT, deleteParams);
        
        CommonResponseDO dto = ApiHelper.parseResponse(response, CommonResponseDO.class);

        test.info("Validating deletion response");
        softAssert.assertEquals(dto.getResponseCode(), 200,
                "Unexpected API response code.");
        softAssert.assertEquals(dto.getMessage(),
                "Account deleted!",
                "Account deletion failed.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreNotAbleToDeleteAccountWithInvalidDetails() {

        SoftAssert softAssert = new SoftAssert();
        UserRequestDO userData = LoginDF.getData();
        test.info("Sending delete request with invalid credentials");
        Response response = ApiHelper.deleteWithFormParameters(Endpoints.DELETE_ACCOUNT, 
                Map.of("email", userData.getEmail(), "password", userData.getPassword()));
        
        CommonResponseDO dto = ApiHelper.parseResponse(response, CommonResponseDO.class);

        test.info("Validating failure response");
        softAssert.assertEquals(dto.getResponseCode(), 404,
                "Unexpected API response code.");
        softAssert.assertEquals(dto.getMessage(),
                "Account not found!",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreAbleToUpdateAccountWithValidDetails() {

        SoftAssert softAssert = new SoftAssert();
        UserDetailsRequestDO userData = SignUpDF.getData();
        ApiHelper.postWithFormParameters(Endpoints.CREATE_ACCOUNT, getUserDetails(userData));
        test.info("Sending update request with valid data");
        Response response = ApiHelper.putWithFormParameters(Endpoints.UPDATE_ACCOUNT, getUserDetails(userData));
        
        CommonResponseDO dto = ApiHelper.parseResponse(response, CommonResponseDO.class);

        test.info("Validating update response");
        softAssert.assertEquals(dto.getResponseCode(), 200,
                "Unexpected API response code.");
        softAssert.assertEquals(dto.getMessage(),
                "User updated!",
                "User update failed.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreNotAbleToUpdateAccountWithInValidDetails() {

        SoftAssert softAssert = new SoftAssert();
        UserDetailsRequestDO userData = SignUpDF.getData();
        test.info("Sending update request with invalid data");
        Response response = ApiHelper.putWithFormParameters(Endpoints.UPDATE_ACCOUNT, getUserDetails(userData));
        
        CommonResponseDO dto = ApiHelper.parseResponse(response, CommonResponseDO.class);

        test.info("Validating failure response");
        softAssert.assertEquals(dto.getResponseCode(), 404,
                "Unexpected API response code.");
        softAssert.assertEquals(dto.getMessage(),
                "Account not found!",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }
}
