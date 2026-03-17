package API.services;

import API.client.ApiClient;
import API.client.ApiResponse;
import API.dataObjects.common.CommonResponseDO;
import API.dataObjects.product.ProductsResponseDO;
import API.utilities.Endpoints;

import java.util.Map;

public class ProductService {

    public ApiResponse<ProductsResponseDO> getAllProducts() {

        return ApiClient.get(Endpoints.PRODUCTS,  ProductsResponseDO.class );
    }

    public ApiResponse<ProductsResponseDO> searchProduct(String product) {

        return ApiClient.post( Endpoints.SEARCH_PRODUCT, Map.of("search_product", product), ProductsResponseDO.class );
    }

    public ApiResponse<CommonResponseDO> searchProductWithoutParameter() {

        return ApiClient.post( Endpoints.SEARCH_PRODUCT, null, CommonResponseDO.class );
    }

    public ApiResponse<CommonResponseDO> addANewProduct(String product) {
        return ApiClient.post( Endpoints.PRODUCTS, Map.of("product", product),CommonResponseDO.class);
    }
}