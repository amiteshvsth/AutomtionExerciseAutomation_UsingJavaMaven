package API.services;

import API.client.ApiClient;
import API.endpoints.APIRoutes;
import io.restassured.response.Response;

import java.util.Map;

public class UserService {

    public Response createUser(Map<String, String> userData) {
        return ApiClient.postRequest(APIRoutes.CREATE_ACCOUNT, userData);
    }

    public Response deleteUser(String email, String password) {
        return ApiClient.deleteRequest(APIRoutes.DELETE_ACCOUNT,
                Map.of("email", email, "password", password));
    }
}
