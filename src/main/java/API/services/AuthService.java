package API.services;

import API.client.ApiClient;
import API.client.ApiResponse;
import API.dataObjects.request.user.UserRequestDO;
import API.dataObjects.response.common.CommonResponseDO;
import API.endpoints.APIRoutes;

import java.util.Map;

public class AuthService {

    public ApiResponse<CommonResponseDO> login(UserRequestDO userRequestDO) {

        return ApiClient.post( APIRoutes.VERIFY_LOGIN, Map.of("email", userRequestDO.getEmail(), "password", userRequestDO.getPassword()), CommonResponseDO.class );
    }

    public ApiResponse<CommonResponseDO> loginWithoutEmailParameter(UserRequestDO userRequestDO) {

        return ApiClient.post( APIRoutes.VERIFY_LOGIN, Map.of("password", userRequestDO.getPassword()), CommonResponseDO.class );
    }

    public ApiResponse<CommonResponseDO> loginDelete(UserRequestDO userRequestDO) {

        return ApiClient.delete( APIRoutes.VERIFY_LOGIN, Map.of("email", userRequestDO.getEmail(), "password", userRequestDO.getPassword()), CommonResponseDO.class );
    }

}