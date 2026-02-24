package API.services;

import API.client.ApiClient;
import API.client.ApiResponse;
import API.dataObjects.response.brand.BrandsResponseDO;
import API.dataObjects.response.common.CommonResponseDO;
import API.dataObjects.response.product.ProductsResponseDO;
import API.endpoints.APIRoutes;

import java.util.Map;

public class ProductService {

    public ApiResponse<ProductsResponseDO> getAllProducts() {

        return ApiClient.get(APIRoutes.PRODUCTS,  ProductsResponseDO.class );
    }

    public ApiResponse<ProductsResponseDO> searchProduct(String product) {

        return ApiClient.post( APIRoutes.SEARCH_PRODUCT, Map.of("search_product", product), ProductsResponseDO.class );
    }

    public ApiResponse<CommonResponseDO> searchProductWithoutParameter() {

        return ApiClient.post( APIRoutes.SEARCH_PRODUCT, null, CommonResponseDO.class );
    }

    public ApiResponse<BrandsResponseDO> getAllBrands() {
        return ApiClient.get( APIRoutes.BRANDS, BrandsResponseDO.class);
    }

    public ApiResponse<CommonResponseDO> addNewBrand() {
        return ApiClient.post( APIRoutes.BRANDS,null, CommonResponseDO.class);
    }

    public ApiResponse<CommonResponseDO> addANewProduct(String product) {
        return ApiClient.post( APIRoutes.PRODUCTS, null,CommonResponseDO.class);
    }
}