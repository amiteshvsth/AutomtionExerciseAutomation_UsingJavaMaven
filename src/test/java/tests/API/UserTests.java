package tests.API;

import API.client.ApiResponse;
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
        ResponseValidator.validateNotNull(response.getDto().getUser());
        ResponseValidator.validateTrue(response.getDto().getUser().getEmail().equals(email),"Email does not match");
    }
}
