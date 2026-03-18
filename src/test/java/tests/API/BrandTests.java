package tests.API;

import API.client.ApiResponse;
import API.dataObjects.common.CommonResponseDO;
import API.services.BrandService;
import API.dataObjects.brand.BrandsResponseDO;
import API.dataObjects.brand.BrandRequestDO;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class BrandTests extends BaseTest {

    @Test
    public void verifyThatAllBrandsAreReturnedSuccessfully() {


        SoftAssert softAssert = new SoftAssert();
        BrandService brandService = new BrandService();

        test.info("Sending request to retrieve all brands");
        ApiResponse<BrandsResponseDO> response = brandService.getAllBrands();

        test.info("Validating API response code");
        softAssert.assertEquals(response.getDto().getResponseCode(), 200,
                "Unexpected API response code.");

        test.info("Validating brands list is not null");
        softAssert.assertNotNull(response.getDto().getBrands(),
                "Brands list is null.");

        List<BrandRequestDO> brands = response.getDto().getBrands();

        test.info("Validating brands list is not empty");
        softAssert.assertFalse(brands.isEmpty(),
                "Brands list should not be empty.");

        test.info("Validating first brand object contains brand name");
        BrandRequestDO brand = brands.getFirst();
        softAssert.assertNotNull(brand.getBrand(),
                "Brand name is null.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreNotAbleToAddNewBrands() {


        SoftAssert softAssert = new SoftAssert();
        BrandService brandService = new BrandService();

        test.info("Sending request to add a new brand");
        ApiResponse<CommonResponseDO> response = brandService.addNewBrand();

        test.info("Validating API response code for unsupported method");
        softAssert.assertEquals(response.getDto().getResponseCode(), 405,
                "Unexpected API response code.");

        test.info("Validating error message for unsupported request");
        softAssert.assertEquals(response.getDto().getMessage(),
                "This request method is not supported.",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }
}