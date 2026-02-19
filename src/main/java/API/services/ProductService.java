package API.services;

import API.client.ApiClient;
import API.endpoints.APIRoutes;
import io.restassured.response.Response;

import java.util.Map;

public class ProductService {

    public Response getAllProducts() {
        return ApiClient.getRequest(APIRoutes.PRODUCTS);
    }

    public Response searchProduct(String product) {
        return ApiClient.postRequest(APIRoutes.SEARCH_PRODUCT,
                Map.of("search_product", product));
    }
}
