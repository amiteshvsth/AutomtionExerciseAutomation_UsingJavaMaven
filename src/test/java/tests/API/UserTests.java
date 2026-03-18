package tests.API;

import API.client.ApiResponse;
import API.dataFactory.user.UserRequestDF;
import API.dataObjects.user.UserRequestDO;
import API.dataObjects.common.CommonResponseDO;
import API.dataObjects.user.UserDO;
import API.dataObjects.user.UserResponseDO;
import API.services.UserService;
import org.testng.annotations.Test;

public class UserTests extends BaseTest {

    @Test
    public void verifyUserCanBeRetrievedByEmail() {


        UserService userService = new UserService();
        String email = "amiteshvashishth@yopmail.com";

        test.info("Sending request to retrieve user by email");
        ApiResponse<UserResponseDO> response = userService.getUserByEmail(email);

        test.info("Validating HTTP and API response codes");
        softAssert.assertEquals(response.getStatusCode(), 200,
                "Unexpected HTTP status code.");
        softAssert.assertEquals(response.getDto().getResponseCode(), 200,
                "Unexpected API response code.");

        test.info("Validating user object is returned");
        softAssert.assertNotNull(response.getDto().getUser(),
                "User object is null.");

        test.info("Validating returned user details");
        UserDO user = response.getDto().getUser();

        softAssert.assertEquals(user.getEmail(), email, "Email mismatch.");
        softAssert.assertNotNull(user.getId(), "User ID is null.");
        softAssert.assertEquals(user.getName(), "", "Name mismatch.");
        softAssert.assertEquals(user.getTitle(), "Mr", "Title mismatch.");
        softAssert.assertEquals(user.getBirth_day(), "15", "Birth day mismatch.");
        softAssert.assertEquals(user.getBirth_month(), "9", "Birth month mismatch.");
        softAssert.assertEquals(user.getBirth_year(), "2000", "Birth year mismatch.");
        softAssert.assertEquals(user.getFirst_name(), "Rahul", "First name mismatch.");
        softAssert.assertEquals(user.getLast_name(), "Mehta", "Last name mismatch.");
        softAssert.assertEquals(user.getCompany(),"Kalyan Org.", "Company field mismatch.");
        softAssert.assertEquals(user.getAddress1(), "Naroda", "Address1 mismatch.");
        softAssert.assertEquals(user.getAddress2(), "Gujarat", "Address2 mismatch.");
        softAssert.assertEquals(user.getCountry(), "Canada", "Country mismatch.");
        softAssert.assertEquals(user.getState(), "Rajasthan", "State mismatch.");
        softAssert.assertEquals(user.getCity(), "jaipur", "City mismatch.");
        softAssert.assertEquals(user.getZipcode(), "390003", "Zipcode mismatch.");

        softAssert.assertAll();
    }


    @Test
    public void verifyUserCannotBeRetrievedByUnknownEmail() {

        UserService userService = new UserService();
        String email = "testslugurlamitesh@xyz.com";

        test.info("Sending request with unknown email");
        ApiResponse<CommonResponseDO> response =
                userService.getUserByInvalidEmail(email);

        test.info("Validating error response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 404,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "Account not found with this email, try another email!",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreAbleToCreateAccountWithValidDetails() {

        UserService userService = new UserService();

        test.info("Sending account creation request with valid data");
        ApiResponse<CommonResponseDO> response = userService.createUser(
                UserRequestDF.setValidSignUpDetails());

        test.info("Validating account creation response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 201,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "User created!",
                "Account creation failed.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreNotAbleToCreateAccountWithInvalidDetails() {

        UserService userService = new UserService();

        test.info("Sending account creation request with invalid data");
        ApiResponse<CommonResponseDO> response = userService.createUser(
                UserRequestDF.setInvalidSignUpDetails());

        test.info("Validating failure response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 400,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "Email already exists!",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreAbleToDeleteAccountWithValidDetails() {

        UserService userService = new UserService();

        test.info("Creating user before deletion");
        var createUserData = UserRequestDF.setValidSignUpDetails();
        userService.createUser(createUserData);

        test.info("Preparing delete request");
        UserRequestDO deleteUserData = new UserRequestDO();
        deleteUserData.setEmail(createUserData.getEmail());
        deleteUserData.setPassword(createUserData.getPassword());

        test.info("Sending delete request");
        ApiResponse<CommonResponseDO> response = userService.deleteUser(deleteUserData);

        test.info("Validating deletion response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 200,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "Account deleted!",
                "Account deletion failed.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreNotAbleToDeleteAccountWithInvalidDetails() {

        UserService userService = new UserService();

        test.info("Sending delete request with invalid credentials");
        ApiResponse<CommonResponseDO> response = userService.deleteUser(
                UserRequestDF.setInvalidLoginDetails());

        test.info("Validating failure response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 404,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "Account not found!",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreAbleToUpdateAccountWithValidDetails() {

        UserService userService = new UserService();

        test.info("Sending update request with valid data");
        ApiResponse<CommonResponseDO> response = userService.updateUser(
                UserRequestDF.setInvalidSignUpDetails());

        test.info("Validating update response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 200,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "User updated!",
                "User update failed.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreNotAbleToUpdateAccountWithInValidDetails() {

        UserService userService = new UserService();

        test.info("Sending update request with invalid data");
        ApiResponse<CommonResponseDO> response = userService.updateUser(
                UserRequestDF.setValidSignUpDetails());

        test.info("Validating failure response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 404,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "Account not found!",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }
}