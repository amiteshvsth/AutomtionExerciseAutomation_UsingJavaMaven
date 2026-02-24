package API.utilities;

import org.testng.Assert;

public class ResponseValidator {

    private ResponseValidator() {
        // prevent instantiation
    }
    public static void validateStatusCode(int actualStatusCode, int expectedStatusCode) {
        Assert.assertEquals( actualStatusCode, expectedStatusCode, "Unexpected status code.");
    }

    public static void validateResponseTime(long actualResponseTime, long maxAllowedTime) {
        Assert.assertTrue(actualResponseTime <= maxAllowedTime, "Response time exceeded allowed limit. Actual: " + actualResponseTime + "ms");
    }

    public static void validateNotNull(Object object) {
        Assert.assertNotNull(object);
    }

    public static void validateTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }
}