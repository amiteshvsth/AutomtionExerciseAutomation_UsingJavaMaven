package API.utilities;

import io.restassured.response.Response;
import org.testng.Assert;

public class ResponseValidator {

    public static void verifyStatusCode(Response response, int expected) {
        Assert.assertEquals(response.statusCode(), expected);
    }

    public static void verifyContains(Response response, String text) {
        Assert.assertTrue(response.asString().contains(text));
    }
}

