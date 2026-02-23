package tests.API;

import API.client.ApiClient;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected ApiClient apiClient;

    @BeforeClass
    public void setup() {
        apiClient = new ApiClient();
    }
}