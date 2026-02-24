package API.services;

import API.client.ApiClient;
import API.client.ApiResponse;
import API.dataObjects.response.brand.BrandsResponseDO;
import API.dataObjects.response.common.CommonResponseDO;
import API.endpoints.APIRoutes;


public class BrandService {

    public ApiResponse<BrandsResponseDO> getAllBrands() {
        return ApiClient.get( APIRoutes.BRANDS, BrandsResponseDO.class);
    }

    public ApiResponse<CommonResponseDO> addNewBrand() {
        return ApiClient.post( APIRoutes.BRANDS,null, CommonResponseDO.class);
    }
}