package API.client;

import API.config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class ApiClient {

    static {
        RestAssured.baseURI = ConfigManager.getValue("baseUrl");
    }

    public static Response getRequest(String endpoint) {
        return RestAssured
                .given()
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response postRequest(String endpoint, Object body) {
        return RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .formParams((Map<String, ?>) body)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response deleteRequest(String endpoint, Object body) {
        return RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .formParams((Map<String, ?>) body)
                .log().all()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
}
