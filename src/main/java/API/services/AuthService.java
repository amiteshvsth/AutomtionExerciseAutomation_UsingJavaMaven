package API.services;

import API.client.ApiClient;
import API.client.ApiResponse;
import API.dataObjects.user.UserRequestDO;
import API.dataObjects.common.CommonResponseDO;
import API.utilities.Endpoints;

import java.util.Map;

public class AuthService {

    public ApiResponse<CommonResponseDO> login(UserRequestDO userRequestDO) {

        return ApiClient.post( Endpoints.VERIFY_LOGIN, Map.of("email", userRequestDO.getEmail(), "password", userRequestDO.getPassword()), CommonResponseDO.class );
    }

    public ApiResponse<CommonResponseDO> loginWithoutEmailParameter(UserRequestDO userRequestDO) {

        return ApiClient.post( Endpoints.VERIFY_LOGIN, Map.of("password", userRequestDO.getPassword()), CommonResponseDO.class );
    }

    public ApiResponse<CommonResponseDO> loginDelete(UserRequestDO userRequestDO) {

        return ApiClient.delete( Endpoints.VERIFY_LOGIN, Map.of("email", userRequestDO.getEmail(), "password", userRequestDO.getPassword()), CommonResponseDO.class );
    }

}