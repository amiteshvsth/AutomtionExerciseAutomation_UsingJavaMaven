package API.services;

import API.client.ApiClient;
import API.client.ApiResponse;
import API.dataObjects.brand.BrandsResponseDO;
import API.dataObjects.common.CommonResponseDO;
import API.utilities.Endpoints;


public class BrandService {

    public ApiResponse<BrandsResponseDO> getAllBrands() {
        return ApiClient.get( Endpoints.BRANDS, BrandsResponseDO.class);
    }

    public ApiResponse<CommonResponseDO> addNewBrand() {
        return ApiClient.post( Endpoints.BRANDS,null, CommonResponseDO.class);
    }
}