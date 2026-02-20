package API.services;

import API.client.ApiClient;
import API.client.ApiResponse;
import API.dataObjects.response.user.UserResponseDO;
import API.endpoints.APIRoutes;

import java.util.Map;

public class AuthService {

    public ApiResponse<UserResponseDO> login(String email, String password) {

        return ApiClient.post(
                APIRoutes.VERIFY_LOGIN,
                Map.of("email", email, "password", password),
                UserResponseDO.class
        );
    }

    public ApiResponse<UserResponseDO> deleteLogin(String email, String password) {

        return ApiClient.delete(
                APIRoutes.VERIFY_LOGIN,
                Map.of("email", email, "password", password),
                UserResponseDO.class
        );
    }
}