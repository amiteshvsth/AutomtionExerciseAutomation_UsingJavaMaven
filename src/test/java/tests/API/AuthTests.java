package tests.API;

import API.dataFactory.user.UserRequestDF;
import API.services.AuthService;
import API.utilities.ResponseValidator;
import org.testng.annotations.Test;

import java.util.Objects;

public class AuthTests extends BaseTest {

    @Test
    public void verifyThatWeAreAbleToLoginWithValidCredentials() {

        AuthService authService = new AuthService();

        var response = authService.login(UserRequestDF.setValidLoginDetails());
        ResponseValidator.validateStatusCode(response.getStatusCode(), 200);
        ResponseValidator.validateTrue(response.getDto().getResponseCode() == 200, "Login Not Successful");
        ResponseValidator.validateTrue(response.getDto().getMessage().equals("User exists!"), "User does not exist");
    }

    @Test
    public void verifyThatLoginFailsWithInvalidPassword() {

        AuthService authService = new AuthService();

        var response = authService.login(UserRequestDF.setInvalidLoginDetails());

        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 404);
        ResponseValidator.validateTrue(Objects.equals(response.getDto().getMessage(), "User not found!"), "Username Not Found");

    }
}