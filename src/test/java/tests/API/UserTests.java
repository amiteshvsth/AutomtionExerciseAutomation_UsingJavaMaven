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

        UserService userService = new UserService();
        String email = "amiteshvashishth@yopmail.com";

        ApiResponse<UserResponseDO> response = userService.getUserByEmail(email);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getDto().getResponseCode(),200);
        Assert.assertNotNull(response.getDto().getUser());
        Assert.assertEquals(response.getDto().getUser().getEmail(), email, "Email does not match");
        Assert.assertNotNull(response.getDto().getUser().getId());
        Assert.assertEquals(response.getDto().getUser().getName(), "Amitesh Vashishth", "Name does not match");
        Assert.assertEquals(response.getDto().getUser().getTitle(), "Mr", "Title does not match");
        Assert.assertEquals(response.getDto().getUser().getBirth_day(), "12", "Birth Day does not match");
        Assert.assertEquals(response.getDto().getUser().getBirth_month(), "6", "Birth Month does not match");
        Assert.assertEquals(response.getDto().getUser().getBirth_year(), "2018", "Birth Year does not match");
        Assert.assertEquals(response.getDto().getUser().getFirst_name(), "Amitesh", "First Name does not match");
        Assert.assertEquals(response.getDto().getUser().getLast_name(), "Vashishth", "Last Name does not match");
        Assert.assertTrue(response.getDto().getUser().getCompany().isEmpty(),"Company does not match");
        Assert.assertEquals(response.getDto().getUser().getAddress1(), "B4baqrMaklfgdf", "Address1 Name does not match");
        Assert.assertEquals(response.getDto().getUser().getAddress2(), "HmjlptLd8hsdfg", "Address2 does not match");
        Assert.assertEquals(response.getDto().getUser().getCountry(), "India", "Country does not match");
        Assert.assertEquals(response.getDto().getUser().getState(), "AUMyJsgdf", "State does not match");
        Assert.assertEquals(response.getDto().getUser().getCity(), "dafIOsdfg", "City does not match");
        Assert.assertEquals(response.getDto().getUser().getZipcode(), "993576sdf", "ZipCode does not match");
    }

    @Test
    public void verifyUserCannotBeRetrievedByUnknownEmail() {

        UserService userService = new UserService();
        String email = "testslugurlamitesh@xyz.com";

        ApiResponse<CommonResponseDO> response = userService.getUserByInvalidEmail(email);

        Assert.assertEquals(response.getDto().getResponseCode(), 404);
        Assert.assertEquals(response.getDto().getMessage(), "Account not found with this email, try another email!", "Account should not be found");
    }

    @Test
    public void verifyThatWeAreAbleToCreateAccountWithValidDetails() {
        UserService userService = new UserService();

        var response = userService.createUser(UserRequestDF.setValidSignUpDetails());

        Assert.assertEquals(response.getDto().getResponseCode(), 201);
        Assert.assertEquals(response.getDto().getMessage(), "User created!", "User Not Created");
    }

    @Test
    public void verifyThatWeAreNotAbleToCreateAccountWithInvalidDetails() {
        UserService userService = new UserService();

        var response = userService.createUser(UserRequestDF.setInvalidSignUpDetails());

        Assert.assertEquals(response.getDto().getResponseCode(), 400);
        Assert.assertEquals(response.getDto().getMessage(), "Email already exists!", "User should not be created");
    }

    @Test
    public void verifyThatWeAreAbleToDeleteAccountWithValidDetails() {
        UserService userService = new UserService();

        //Firstly we create user and then delete it
        var createUserData = UserRequestDF.setValidSignUpDetails();
        userService.createUser(createUserData);

        UserRequestDO deleteUserData = new UserRequestDO();
        deleteUserData.setEmail(createUserData.getEmail());
        deleteUserData.setPassword(createUserData.getPassword());

        var response = userService.deleteUser(deleteUserData);

        Assert.assertEquals(response.getDto().getResponseCode(), 200);
        Assert.assertEquals(response.getDto().getMessage(), "Account deleted!", "Account Not Created");
    }

    @Test
    public void verifyThatWeAreNotAbleToDeleteAccountWithInvalidDetails() {
        UserService userService = new UserService();

        var response = userService.deleteUser(UserRequestDF.setInvalidLoginDetails());

        Assert.assertEquals(response.getDto().getResponseCode(), 404);
        Assert.assertEquals(response.getDto().getMessage(), "Account not found!", "Account should not be created");
    }

    @Test
    public void verifyThatWeAreAbleToUpdateAccountWithValidDetails() {
        UserService userService = new UserService();

        var response = userService.updateUser(UserRequestDF.setInvalidSignUpDetails());

        Assert.assertEquals(response.getDto().getResponseCode(), 200);
        Assert.assertEquals(response.getDto().getMessage(), "User updated!", "User not updated!");

    }

    @Test
    public void verifyThatWeAreNotAbleToUpdateAccountWithInValidDetails() {
        UserService userService = new UserService();

        var response = userService.updateUser(UserRequestDF.setValidSignUpDetails());

        Assert.assertEquals(response.getDto().getResponseCode(), 404);
        Assert.assertEquals(response.getDto().getMessage(), "Account not found!", "Account existed");

    }
}
