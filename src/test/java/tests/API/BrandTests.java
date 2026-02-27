package tests.API;

import API.client.ApiResponse;
import API.dataObjects.response.common.CommonResponseDO;
import API.services.BrandService;
import API.dataObjects.response.brand.BrandsResponseDO;
import API.dataObjects.response.brand.BrandDO;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BrandTests extends BaseTest {

    @Test
    public void verifyThatAllBrandsAreReturnedSuccessfully() {

        test.info("===== TEST START: Verify all brands are returned successfully =====");

        BrandService brandService = new BrandService();

        test.info("Step 1: Sending request to retrieve all brands");
        ApiResponse<BrandsResponseDO> response = brandService.getAllBrands();

        test.info("Step 2: Validating API response code");
        Assert.assertEquals(response.getDto().getResponseCode(), 200,
                "Unexpected API response code.");

        test.info("Step 3: Validating brands list is not null");
        Assert.assertNotNull(response.getDto().getBrands(),
                "Brands list is null.");

        List<BrandDO> brands = response.getDto().getBrands();

        test.info("Step 4: Validating brands list is not empty");
        Assert.assertFalse(brands.isEmpty(),
                "Brands list should not be empty.");

        test.info("Step 5: Validating first brand object contains brand name");
        BrandDO brand = brands.getFirst();
        Assert.assertNotNull(brand.getBrand(),
                "Brand name is null.");

        test.info("===== TEST PASSED: All brands retrieved successfully =====");
    }


    @Test
    public void verifyThatWeAreNotAbleToAddNewBrands() {

        test.info("===== TEST START: Verify adding new brand is not allowed =====");

        BrandService brandService = new BrandService();

        test.info("Step 1: Sending request to add a new brand");
        ApiResponse<CommonResponseDO> response = brandService.addNewBrand();

        test.info("Step 2: Validating API response code for unsupported method");
        Assert.assertEquals(response.getDto().getResponseCode(), 405,
                "Unexpected API response code.");

        test.info("Step 3: Validating error message for unsupported request");
        Assert.assertEquals(response.getDto().getMessage(),
                "This request method is not supported.",
                "Unexpected error message returned.");

        test.info("===== TEST PASSED: API correctly rejected brand creation request =====");
    }
}