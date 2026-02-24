package API.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.Getter;

import java.net.URI;

@Getter
public class ApiResponse<T> {

    private T dto;
    private int statusCode;
    private URI locationHeader;
    private Response rawResponse;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // ==============================
    // ===== MAPPING LOGIC ==========
    // ==============================

    public static <T> ApiResponse<T> map(
            Response response,
            Class<T> clazz,
            String method,
            String endpoint) {

        String content = response.getBody().asString();

        // 🔴 Handle 5xx errors
        if (response.getStatusCode() >= 500) {
            throw new RuntimeException(
                    String.format(
                            "Server Error\nMethod: %s\nEndpoint: %s\nStatus: %d\nBody: %s",
                            method,
                            endpoint,
                            response.getStatusCode(),
                            content
                    )
            );
        }

        T dto;
        try {
            dto = objectMapper.readValue(content, clazz);
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format(
                            "Deserialization Failed\nExpected Type: %s\nEndpoint: %s\nResponse Body: %s",
                            clazz.getSimpleName(),
                            endpoint,
                            content
                    ),
                    e
            );
        }

        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.dto = dto;
        apiResponse.statusCode = response.getStatusCode();
        apiResponse.rawResponse = response;

        String location = response.getHeader("Location");
        if (location != null) {
            apiResponse.locationHeader = URI.create(location);
        }

        return apiResponse;
    }

    // ==============================
    // ===== SUCCESS CHECK ==========
    // ==============================

    public void ensureSuccess() {
        if (isSuccess()) return;

        throw new RuntimeException(
                String.format(
                        "Request Failed\nStatus Code: %d\nResponse Body: %s",
                        statusCode,
                        getBodyAsString()
                )
        );
    }

    public boolean isSuccess() {
        return statusCode >= 200 && statusCode < 300;
    }

    // ==============================
    // ===== NEW HELPER METHODS =====
    // ==============================

    /**
     * Cleaner alias instead of getDto()
     */
    public T getBody() {
        return dto;
    }

    /**
     * Returns raw body as string
     */
    public String getBodyAsString() {
        return rawResponse.getBody().asString();
    }

    /**
     * Extract response time in milliseconds
     */
    public long getResponseTime() {
        return rawResponse.getTime();
    }

    /**
     * Generic header getter
     */
    public String getHeader(String headerName) {
        return rawResponse.getHeader(headerName);
    }

    /**
     * Safe token extractor (if DTO contains a "token" field)
     */
    public String getToken() {
        try {
            return rawResponse.jsonPath().getString("token");
        } catch (Exception e) {
            return null;
        }
    }

}