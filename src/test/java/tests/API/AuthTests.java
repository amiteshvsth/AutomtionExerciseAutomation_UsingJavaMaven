package tests.API;

import API.dataFactory.user.UserRequestDF;
import API.services.AuthService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

public class AuthTests extends BaseTest {

    @Test
    public void verifyThatWeAreAbleToLoginWithValidCredentials() {

        AuthService authService = new AuthService();

        var response = authService.login(UserRequestDF.setValidLoginDetails());
        
        Assert.assertEquals( response.getStatusCode(), 200, "Unexpected status code.");
        Assert.assertEquals(response.getDto().getResponseCode(), 200, "Login Not Successful");
        Assert.assertEquals(response.getDto().getMessage(), "User exists!", "User does not exist");
    }

    @Test
    public void verifyThatLoginFailsWithInvalidPassword() {

        AuthService authService = new AuthService();

        var response = authService.login(UserRequestDF.setInvalidLoginDetails());

        Assert.assertEquals(response.getDto().getResponseCode(), 404, "Unexpected status code.");
        Assert.assertEquals(response.getDto().getMessage(), "User not found!", "Username Not Found");

    }

    @Test
    public void verifyThatLoginFailsWithoutEmailParameter() {

        AuthService authService = new AuthService();

        var response = authService.loginWithoutEmailParameter(UserRequestDF.setValidLoginDetails());

        Assert.assertEquals(response.getDto().getResponseCode(), 400, "Unexpected status code.");
        Assert.assertEquals(response.getDto().getMessage(), "Bad request, email or password parameter is missing in POST request.", "Logged in without email");

    }

    @Test
    public void verifyThatLoginFailsWithDeleteRequest() {

        AuthService authService = new AuthService();

        var response = authService.loginDelete(UserRequestDF.setValidLoginDetails());

        Assert.assertEquals(response.getDto().getResponseCode(), 405,"Unexpected status code.");
        Assert.assertEquals(response.getDto().getMessage(), "This request method is not supported.", "Logged in with delete method");

    }
}