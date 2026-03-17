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

        test.info("===== TEST START: Verify user retrieval by valid email =====");

        UserService userService = new UserService();
        String email = "amiteshvashishth@yopmail.com";

        test.info("Step 1: Sending request to retrieve user by email");
        ApiResponse<UserResponseDO> response = userService.getUserByEmail(email);

        test.info("Step 2: Validating HTTP and API response codes");
        softAssert.assertEquals(response.getStatusCode(), 200,
                "Unexpected HTTP status code.");
        softAssert.assertEquals(response.getDto().getResponseCode(), 200,
                "Unexpected API response code.");

        test.info("Step 3: Validating user object is returned");
        softAssert.assertNotNull(response.getDto().getUser(),
                "User object is null.");

        test.info("Step 4: Validating returned user details");
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
        test.info("===== TEST PASSED: User retrieved and validated successfully =====");
    }


    @Test
    public void verifyUserCannotBeRetrievedByUnknownEmail() {

        test.info("===== TEST START: Verify user retrieval fails for unknown email =====");

        UserService userService = new UserService();
        String email = "testslugurlamitesh@xyz.com";

        test.info("Step 1: Sending request with unknown email");
        ApiResponse<CommonResponseDO> response =
                userService.getUserByInvalidEmail(email);

        test.info("Step 2: Validating error response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 404,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "Account not found with this email, try another email!",
                "Unexpected error message returned.");

        softAssert.assertAll();
        test.info("===== TEST PASSED: Unknown email correctly rejected =====");
    }


    @Test
    public void verifyThatWeAreAbleToCreateAccountWithValidDetails() {

        test.info("===== TEST START: Verify account creation with valid details =====");

        UserService userService = new UserService();

        test.info("Step 1: Sending account creation request with valid data");
        ApiResponse<CommonResponseDO> response = userService.createUser(
                UserRequestDF.setValidSignUpDetails());

        test.info("Step 2: Validating account creation response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 201,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "User created!",
                "Account creation failed.");

        softAssert.assertAll();
        test.info("===== TEST PASSED: Account created successfully =====");
    }


    @Test
    public void verifyThatWeAreNotAbleToCreateAccountWithInvalidDetails() {

        test.info("===== TEST START: Verify account creation fails with invalid details =====");

        UserService userService = new UserService();

        test.info("Step 1: Sending account creation request with invalid data");
        ApiResponse<CommonResponseDO> response = userService.createUser(
                UserRequestDF.setInvalidSignUpDetails());

        test.info("Step 2: Validating failure response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 400,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "Email already exists!",
                "Unexpected error message returned.");

        softAssert.assertAll();
        test.info("===== TEST PASSED: Invalid account creation correctly rejected =====");
    }


    @Test
    public void verifyThatWeAreAbleToDeleteAccountWithValidDetails() {

        test.info("===== TEST START: Verify account deletion with valid credentials =====");

        UserService userService = new UserService();

        test.info("Step 1: Creating user before deletion");
        var createUserData = UserRequestDF.setValidSignUpDetails();
        userService.createUser(createUserData);

        test.info("Step 2: Preparing delete request");
        UserRequestDO deleteUserData = new UserRequestDO();
        deleteUserData.setEmail(createUserData.getEmail());
        deleteUserData.setPassword(createUserData.getPassword());

        test.info("Step 3: Sending delete request");
        ApiResponse<CommonResponseDO> response = userService.deleteUser(deleteUserData);

        test.info("Step 4: Validating deletion response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 200,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "Account deleted!",
                "Account deletion failed.");

        softAssert.assertAll();
        test.info("===== TEST PASSED: Account deleted successfully =====");
    }


    @Test
    public void verifyThatWeAreNotAbleToDeleteAccountWithInvalidDetails() {

        test.info("===== TEST START: Verify account deletion fails with invalid credentials =====");

        UserService userService = new UserService();

        test.info("Step 1: Sending delete request with invalid credentials");
        ApiResponse<CommonResponseDO> response = userService.deleteUser(
                UserRequestDF.setInvalidLoginDetails());

        test.info("Step 2: Validating failure response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 404,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "Account not found!",
                "Unexpected error message returned.");

        softAssert.assertAll();
        test.info("===== TEST PASSED: Invalid deletion request correctly rejected =====");
    }


    @Test
    public void verifyThatWeAreAbleToUpdateAccountWithValidDetails() {

        test.info("===== TEST START: Verify account update with valid details =====");

        UserService userService = new UserService();

        test.info("Step 1: Sending update request with valid data");
        ApiResponse<CommonResponseDO> response = userService.updateUser(
                UserRequestDF.setInvalidSignUpDetails());

        test.info("Step 2: Validating update response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 200,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "User updated!",
                "User update failed.");

        softAssert.assertAll();
        test.info("===== TEST PASSED: Account updated successfully =====");
    }


    @Test
    public void verifyThatWeAreNotAbleToUpdateAccountWithInValidDetails() {

        test.info("===== TEST START: Verify account update fails with invalid data =====");

        UserService userService = new UserService();

        test.info("Step 1: Sending update request with invalid data");
        ApiResponse<CommonResponseDO> response = userService.updateUser(
                UserRequestDF.setValidSignUpDetails());

        test.info("Step 2: Validating failure response");
        softAssert.assertEquals(response.getDto().getResponseCode(), 404,
                "Unexpected API response code.");
        softAssert.assertEquals(response.getDto().getMessage(),
                "Account not found!",
                "Unexpected error message returned.");

        softAssert.assertAll();
        test.info("===== TEST PASSED: Invalid update request correctly rejected =====");
    }
}