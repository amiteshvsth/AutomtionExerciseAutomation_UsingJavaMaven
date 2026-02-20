package API.services;

import API.client.ApiClient;
import API.client.ApiResponse;
import API.dataObjects.response.product.ProductsResponseDO;
import API.endpoints.APIRoutes;

import java.util.Map;

public class ProductService {

    public ApiResponse<ProductsResponseDO> getAllProducts() {

        return ApiClient.get(
                APIRoutes.PRODUCTS,
                ProductsResponseDO.class
        );
    }

    public ApiResponse<ProductsResponseDO> searchProduct(String product) {

        return ApiClient.post(
                APIRoutes.SEARCH_PRODUCT,
                Map.of("search_product", product),
                ProductsResponseDO.class
        );
    }
}