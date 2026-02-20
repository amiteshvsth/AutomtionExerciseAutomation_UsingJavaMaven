package API.client;

import API.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class ApiClient {

    static {
        RestAssured.baseURI = ConfigManager.getValue("apiUrl");
    }

    private static <T> ApiResponse<T> execute(
            String method,
            String endpoint,
            Map<String, ?> formParams,
            Class<T> clazz
    ) {

        Response response = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .formParams(formParams != null ? formParams : Map.of())
                .log().all()
                .when()
                .request(method, endpoint)
                .then()
                .log().all()
                .extract()
                .response();

        return ApiResponse.map(response, clazz, method, endpoint);
    }

    // PUBLIC METHODS

    public static <T> ApiResponse<T> get(String endpoint, Class<T> clazz) {
        return execute("GET", endpoint, null, clazz);
    }

    public static <T> ApiResponse<T> post(String endpoint, Map<String, ?> body, Class<T> clazz) {
        return execute("POST", endpoint, body, clazz);
    }

    public static <T> ApiResponse<T> put(String endpoint, Map<String, ?> body, Class<T> clazz) {
        return execute("PUT", endpoint, body, clazz);
    }

    public static <T> ApiResponse<T> delete(String endpoint, Map<String, ?> body, Class<T> clazz) {
        return execute("DELETE", endpoint, body, clazz);
    }
}