package API.services;

import API.client.ApiClient;
import API.endpoints.APIRoutes;
import io.restassured.response.Response;

import java.util.Map;

public class AuthService {

    public Response login(String email, String password) {
        return ApiClient.postRequest(APIRoutes.VERIFY_LOGIN,
                Map.of("email", email, "password", password));
    }

    public Response deleteLogin(String email, String password) {
        return ApiClient.deleteRequest(APIRoutes.VERIFY_LOGIN,
                Map.of("email", email, "password", password));
    }
}

