package API.services;

import API.client.ApiClient;
import API.client.ApiResponse;
import API.dataObjects.response.user.UserResponseDO;
import API.endpoints.APIRoutes;

import java.util.Map;

public class UserService {

    public ApiResponse<UserResponseDO> createUser(Map<String, String> userData) {

        return ApiClient.post(
                APIRoutes.CREATE_ACCOUNT,
                userData,
                UserResponseDO.class
        );
    }

    public ApiResponse<UserResponseDO> updateUser(Map<String, String> userData) {

        return ApiClient.put(
                APIRoutes.UPDATE_ACCOUNT,
                userData,
                UserResponseDO.class
        );
    }

    public ApiResponse<UserResponseDO> deleteUser(String email, String password) {

        return ApiClient.delete(
                APIRoutes.DELETE_ACCOUNT,
                Map.of("email", email, "password", password),
                UserResponseDO.class
        );
    }
}