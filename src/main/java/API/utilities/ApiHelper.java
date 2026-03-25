package API.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiHelper {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Pattern JSON_IN_HTML_PATTERN = Pattern.compile("<body>(.*?)</body>", Pattern.DOTALL);

    static {
        RestAssured.baseURI = Endpoints.BASE_URI;
        RestAssured.useRelaxedHTTPSValidation();
    }

    private static io.restassured.specification.RequestSpecification getRequestSpec() {
        return RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .accept("application/json");
    }

    public static Response get(String endpoint) {
        return getRequestSpec()
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response getWithQueryParameters(String endpoint, Map<String, ?> queryParams) {
        return getRequestSpec()
                .queryParams(queryParams)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response postWithFormParameters(String endpoint, Map<String, ?> formParams) {
        return getRequestSpec()
                .formParams(formParams != null ? formParams : Map.of())
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response post(String endpoint) {
        return postWithFormParameters(endpoint, null);
    }

    public static Response putWithFormParameters(String endpoint, Map<String, ?> formParams) {
        return getRequestSpec()
                .formParams(formParams != null ? formParams : Map.of())
                .log().all()
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response deleteWithFormParameters(String endpoint, Map<String, ?> formParams) {
        return getRequestSpec()
                .formParams(formParams != null ? formParams : Map.of())
                .log().all()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static String extractJsonFromHtml(Response response) {
        String body = response.getBody().asString();
        Matcher matcher = JSON_IN_HTML_PATTERN.matcher(body);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return body;
    }

    public static <T> T parseResponse(Response response, Class<T> clazz) {
        String jsonBody = extractJsonFromHtml(response);
        try {
            return objectMapper.readValue(jsonBody, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse response: " + e.getMessage(), e);
        }
    }
}
