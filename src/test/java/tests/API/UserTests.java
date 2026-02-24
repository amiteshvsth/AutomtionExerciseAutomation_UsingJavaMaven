package tests.API;

import API.client.ApiResponse;
import API.dataFactory.user.UserRequestDF;
import API.dataObjects.response.common.CommonResponseDO;
import API.services.UserService;
import API.dataObjects.response.user.UserResponseDO;
import API.utilities.ResponseValidator;
import org.testng.annotations.Test;

public class UserTests extends BaseTest {

    @Test
    public void verifyUserCanBeRetrievedByEmail() {

        UserService userService = new UserService();
        String email = "amiteshvashishth@yopmail.com";

        ApiResponse<UserResponseDO> response = userService.getUserByEmail(email);

        ResponseValidator.validateStatusCode(response.getStatusCode(), 200);
        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(),200);
        ResponseValidator.validateNotNull(response.getDto().getUser());
        ResponseValidator.validateTrue(response.getDto().getUser().getEmail().equals(email),"Email does not match");
        ResponseValidator.validateNotNull(response.getDto().getUser().getId());
        ResponseValidator.validateTrue(response.getDto().getUser().getName().equals("Amitesh Vashishth"),"Name does not match");
        ResponseValidator.validateTrue(response.getDto().getUser().getTitle().equals("Mr"),"Title does not match");
        ResponseValidator.validateTrue(response.getDto().getUser().getBirth_day().equals("12"),"Birth Day does not match");
        ResponseValidator.validateTrue(response.getDto().getUser().getBirth_month().equals("6"),"Birth Month does not match");
        ResponseValidator.validateTrue(response.getDto().getUser().getBirth_year().equals("2018"),"Birth Year does not match");
        ResponseValidator.validateTrue(response.getDto().getUser().getFirst_name().equals("Amitesh"),"First Name does not match");
        ResponseValidator.validateTrue(response.getDto().getUser().getLast_name().equals("Vashishth"),"Last Name does not match");
        ResponseValidator.validateTrue(response.getDto().getUser().getCompany().isEmpty(),"Company does not match");
        ResponseValidator.validateTrue(response.getDto().getUser().getAddress1().equals("B4baqrMaklfgdf"),"Address1 Name does not match");
        ResponseValidator.validateTrue(response.getDto().getUser().getAddress2().equals("HmjlptLd8hsdfg"),"Address2 does not match");
        ResponseValidator.validateTrue(response.getDto().getUser().getCountry().equals("India"),"Country does not match");
        ResponseValidator.validateTrue(response.getDto().getUser().getState().equals("AUMyJsgdf"),"State does not match");
        ResponseValidator.validateTrue(response.getDto().getUser().getCity().equals("dafIOsdfg"),"City does not match");
        ResponseValidator.validateTrue(response.getDto().getUser().getZipcode().equals("993576sdf"),"ZipCode does not match");
    }

    @Test
    public void verifyUserCannotBeRetrievedByUnknownEmail() {

        UserService userService = new UserService();
        String email = "testslugurlamitesh@xyz.com";

        ApiResponse<CommonResponseDO> response = userService.getUserByInvalidEmail(email);

        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 404);
        ResponseValidator.validateTrue(response.getDto().getMessage().equals("Account not found with this email, try another email!"), "Account should not be found");
    }

    @Test
    public void verifyThatWeAreAbleToCreateAccountWithValidDetails() {
        UserService userService = new UserService();

        var response = userService.createUser(UserRequestDF.setValidSignUpDetails());

        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 201);
        ResponseValidator.validateTrue(response.getDto().getMessage().equals("User created!"), "User Not Created");
    }

    @Test
    public void verifyThatWeAreNotAbleToCreateAccountWithInvalidDetails() {
        UserService userService = new UserService();

        var response = userService.createUser(UserRequestDF.setInvalidSignUpDetails());

        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 400);
        ResponseValidator.validateTrue(response.getDto().getMessage().equals("Email already exists!"), "User should not be created");
    }

    @Test
    public void verifyThatWeAreAbleToDeleteAccountWithValidDetails() {
        UserService userService = new UserService();

        var response = userService.deleteUser(UserRequestDF.setValidLoginDetails());

        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 200);
        ResponseValidator.validateTrue(response.getDto().getMessage().equals("Account deleted!"), "Account Not Created");
    }

    @Test
    public void verifyThatWeAreNotAbleToDeleteAccountWithInvalidDetails() {
        UserService userService = new UserService();

        var response = userService.deleteUser(UserRequestDF.setInvalidLoginDetails());

        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 404);
        ResponseValidator.validateTrue(response.getDto().getMessage().equals("Account not found!"), "Account should not be created");
    }

    @Test
    public void verifyThatWeAreAbleToUpdateAccountWithValidDetails() {
        UserService userService = new UserService();

        var response = userService.updateUser(UserRequestDF.setInvalidSignUpDetails());

        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 200);
        ResponseValidator.validateTrue(response.getDto().getMessage().equals("User updated!"), "User not updated!");

    }

    @Test
    public void verifyThatWeAreNotAbleToUpdateAccountWithInValidDetails() {
        UserService userService = new UserService();

        var response = userService.updateUser(UserRequestDF.setValidSignUpDetails());

        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 404);
        ResponseValidator.validateTrue(response.getDto().getMessage().equals("Account not found!"), "Account existed");

    }
}
