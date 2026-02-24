package tests.API;

import API.client.ApiResponse;
import API.dataObjects.response.common.CommonResponseDO;
import API.services.BrandService;
import API.dataObjects.response.brand.BrandsResponseDO;
import API.dataObjects.response.brand.BrandDO;
import API.utilities.ResponseValidator;
import org.testng.annotations.Test;

import java.util.List;

public class BrandTests extends BaseTest {

    @Test
    public void verifyThatAllBrandsAreReturnedSuccessfully() {

        BrandService brandService = new BrandService();
        ApiResponse<BrandsResponseDO> response = brandService.getAllBrands();

        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 200);
        ResponseValidator.validateNotNull(response.getDto().getBrands());

        List<BrandDO> brands = response.getDto().getBrands();
        ResponseValidator.validateTrue(!brands.isEmpty(),"Brands list should not be empty");

        BrandDO brand = brands.getFirst();
        ResponseValidator.validateNotNull(brand.getBrand());
    }

    @Test
    public void verifyThatWeAreNotAbleToAddNewBrands() {

        BrandService brandService = new BrandService();
        ApiResponse<CommonResponseDO> response = brandService.addNewBrand();

        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 405);
        ResponseValidator.validateTrue(response.getDto().getMessage().equals("This request method is not supported."), "Brands should not be added to the page but its added");
    }

}