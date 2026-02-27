package tests.API;

import API.client.ApiResponse;
import API.dataFactory.user.UserRequestDF;
import API.dataObjects.request.user.UserRequestDO;
import API.dataObjects.response.common.CommonResponseDO;
import API.services.UserService;
import API.dataObjects.response.user.UserResponseDO;
import org.testng.Assert;
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
        Assert.assertEquals(response.getStatusCode(), 200,
                "Unexpected HTTP status code.");
        Assert.assertEquals(response.getDto().getResponseCode(), 200,
                "Unexpected API response code.");

        test.info("Step 3: Validating user object is returned");
        Assert.assertNotNull(response.getDto().getUser(),
                "User object is null.");

        test.info("Step 4: Validating returned user details");
        var user = response.getDto().getUser();

        Assert.assertEquals(user.getEmail(), email, "Email mismatch.");
        Assert.assertNotNull(user.getId(), "User ID is null.");
        Assert.assertEquals(user.getName(), "", "Name mismatch.");
        Assert.assertEquals(user.getTitle(), "Mr", "Title mismatch.");
        Assert.assertEquals(user.getBirth_day(), "15", "Birth day mismatch.");
        Assert.assertEquals(user.getBirth_month(), "9", "Birth month mismatch.");
        Assert.assertEquals(user.getBirth_year(), "2000", "Birth year mismatch.");
        Assert.assertEquals(user.getFirst_name(), "Rahul", "First name mismatch.");
        Assert.assertEquals(user.getLast_name(), "Mehta", "Last name mismatch.");
        Assert.assertEquals(user.getCompany(),"Kalyan Org.", "Company field mismatch.");
        Assert.assertEquals(user.getAddress1(), "Naroda", "Address1 mismatch.");
        Assert.assertEquals(user.getAddress2(), "Gujarat", "Address2 mismatch.");
        Assert.assertEquals(user.getCountry(), "Canada", "Country mismatch.");
        Assert.assertEquals(user.getState(), "Rajasthan", "State mismatch.");
        Assert.assertEquals(user.getCity(), "jaipur", "City mismatch.");
        Assert.assertEquals(user.getZipcode(), "390003", "Zipcode mismatch.");

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
        Assert.assertEquals(response.getDto().getResponseCode(), 404,
                "Unexpected API response code.");
        Assert.assertEquals(response.getDto().getMessage(),
                "Account not found with this email, try another email!",
                "Unexpected error message returned.");

        test.info("===== TEST PASSED: Unknown email correctly rejected =====");
    }


    @Test
    public void verifyThatWeAreAbleToCreateAccountWithValidDetails() {

        test.info("===== TEST START: Verify account creation with valid details =====");

        UserService userService = new UserService();

        test.info("Step 1: Sending account creation request with valid data");
        var response = userService.createUser(
                UserRequestDF.setValidSignUpDetails());

        test.info("Step 2: Validating account creation response");
        Assert.assertEquals(response.getDto().getResponseCode(), 201,
                "Unexpected API response code.");
        Assert.assertEquals(response.getDto().getMessage(),
                "User created!",
                "Account creation failed.");

        test.info("===== TEST PASSED: Account created successfully =====");
    }


    @Test
    public void verifyThatWeAreNotAbleToCreateAccountWithInvalidDetails() {

        test.info("===== TEST START: Verify account creation fails with invalid details =====");

        UserService userService = new UserService();

        test.info("Step 1: Sending account creation request with invalid data");
        var response = userService.createUser(
                UserRequestDF.setInvalidSignUpDetails());

        test.info("Step 2: Validating failure response");
        Assert.assertEquals(response.getDto().getResponseCode(), 400,
                "Unexpected API response code.");
        Assert.assertEquals(response.getDto().getMessage(),
                "Email already exists!",
                "Unexpected error message returned.");

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
        Assert.assertEquals(response.getDto().getResponseCode(), 200,
                "Unexpected API response code.");
        Assert.assertEquals(response.getDto().getMessage(),
                "Account deleted!",
                "Account deletion failed.");

        test.info("===== TEST PASSED: Account deleted successfully =====");
    }


    @Test
    public void verifyThatWeAreNotAbleToDeleteAccountWithInvalidDetails() {

        test.info("===== TEST START: Verify account deletion fails with invalid credentials =====");

        UserService userService = new UserService();

        test.info("Step 1: Sending delete request with invalid credentials");
        var response = userService.deleteUser(
                UserRequestDF.setInvalidLoginDetails());

        test.info("Step 2: Validating failure response");
        Assert.assertEquals(response.getDto().getResponseCode(), 404,
                "Unexpected API response code.");
        Assert.assertEquals(response.getDto().getMessage(),
                "Account not found!",
                "Unexpected error message returned.");

        test.info("===== TEST PASSED: Invalid deletion request correctly rejected =====");
    }


    @Test
    public void verifyThatWeAreAbleToUpdateAccountWithValidDetails() {

        test.info("===== TEST START: Verify account update with valid details =====");

        UserService userService = new UserService();

        test.info("Step 1: Sending update request with valid data");
        var response = userService.updateUser(
                UserRequestDF.setInvalidSignUpDetails());

        test.info("Step 2: Validating update response");
        Assert.assertEquals(response.getDto().getResponseCode(), 200,
                "Unexpected API response code.");
        Assert.assertEquals(response.getDto().getMessage(),
                "User updated!",
                "User update failed.");

        test.info("===== TEST PASSED: Account updated successfully =====");
    }


    @Test
    public void verifyThatWeAreNotAbleToUpdateAccountWithInValidDetails() {

        test.info("===== TEST START: Verify account update fails with invalid data =====");

        UserService userService = new UserService();

        test.info("Step 1: Sending update request with invalid data");
        var response = userService.updateUser(
                UserRequestDF.setValidSignUpDetails());

        test.info("Step 2: Validating failure response");
        Assert.assertEquals(response.getDto().getResponseCode(), 404,
                "Unexpected API response code.");
        Assert.assertEquals(response.getDto().getMessage(),
                "Account not found!",
                "Unexpected error message returned.");

        test.info("===== TEST PASSED: Invalid update request correctly rejected =====");
    }
}