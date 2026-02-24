package API.services;

import API.client.ApiClient;
import API.client.ApiResponse;
import API.dataFactory.user.UserRequestDF;
import API.dataObjects.request.user.UserDO;
import API.dataObjects.request.user.UserRequestDO;
import API.dataObjects.response.common.CommonResponseDO;
import API.dataObjects.response.user.UserResponseDO;
import API.endpoints.APIRoutes;
import Functional.dataFactory.UserDF;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    public ApiResponse<CommonResponseDO> createUser(UserDO user) {

        Map<String, String> requestBody = new HashMap<>();

        requestBody.put("name", user.getName());
        requestBody.put("email", user.getEmail());
        requestBody.put("password", user.getPassword());
        requestBody.put("title", user.getTitle());
        requestBody.put("birth_date", user.getBirth_day());
        requestBody.put("birth_month", user.getBirth_month());
        requestBody.put("birth_year", user.getBirth_year());
        requestBody.put("firstname", user.getFirst_name());
        requestBody.put("lastname", user.getLast_name());
        requestBody.put("company", user.getCompany());
        requestBody.put("address1", user.getAddress1());
        requestBody.put("address2", user.getAddress2());
        requestBody.put("country", user.getCountry());
        requestBody.put("zipcode", user.getZipcode());
        requestBody.put("state", user.getState());
        requestBody.put("city", user.getCity());
        requestBody.put("mobile_number", user.getMobile_number());

        return ApiClient.post(
                APIRoutes.CREATE_ACCOUNT,
                requestBody,
                CommonResponseDO.class
        );
    }

    public ApiResponse<CommonResponseDO> updateUser(UserDO user) {

        Map<String, String> requestBody = new HashMap<>();

        requestBody.put("name", user.getName());
        requestBody.put("email", user.getEmail());
        requestBody.put("password", user.getPassword());
        requestBody.put("title", user.getTitle());
        requestBody.put("birth_date", user.getBirth_day());
        requestBody.put("birth_month", user.getBirth_month());
        requestBody.put("birth_year", user.getBirth_year());
        requestBody.put("firstname", user.getFirst_name());
        requestBody.put("lastname", user.getLast_name());
        requestBody.put("company", user.getCompany());
        requestBody.put("address1", user.getAddress1());
        requestBody.put("address2", user.getAddress2());
        requestBody.put("country", user.getCountry());
        requestBody.put("zipcode", user.getZipcode());
        requestBody.put("state", user.getState());
        requestBody.put("city", user.getCity());
        requestBody.put("mobile_number", user.getMobile_number());
        return ApiClient.put(APIRoutes.UPDATE_ACCOUNT, requestBody, CommonResponseDO.class );
    }

    public ApiResponse<CommonResponseDO> deleteUser(UserRequestDO userData) {

        return ApiClient.delete( APIRoutes.DELETE_ACCOUNT, Map.of("email", userData.getEmail(), "password", userData.getPassword()), CommonResponseDO.class );
    }

    public ApiResponse<UserResponseDO> getUserByEmail(String email) {
        return ApiClient.get( APIRoutes.GET_USER_BY_EMAIL + "?email=" + email, UserResponseDO.class );
    }

    public ApiResponse<CommonResponseDO> getUserByInvalidEmail(String email) {
        return ApiClient.get( APIRoutes.GET_USER_BY_EMAIL + "?email=" + email, CommonResponseDO.class );
    }
}