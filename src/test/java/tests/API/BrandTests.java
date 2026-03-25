package tests.API;

import API.dataObjects.common.CommonResponseDO;
import API.dataObjects.brand.BrandsResponseDO;
import API.dataObjects.brand.BrandRequestDO;
import API.utilities.ApiHelper;
import API.utilities.Endpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class BrandTests extends BaseTest {

    @Test
    public void verifyThatAllBrandsAreReturnedSuccessfully() {

        SoftAssert softAssert = new SoftAssert();

        test.info("Sending request to retrieve all brands");
        Response response = ApiHelper.get(Endpoints.BRANDS);
        
        BrandsResponseDO dto = ApiHelper.parseResponse(response, BrandsResponseDO.class);

        test.info("Validating API response code");
        softAssert.assertEquals(dto.getResponseCode(), 200,
                "Unexpected API response code.");

        test.info("Validating brands list is not null");
        softAssert.assertNotNull(dto.getBrands(),
                "Brands list is null.");

        List<BrandRequestDO> brands = dto.getBrands();

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

        test.info("Sending request to add a new brand");
        Response response = ApiHelper.post(Endpoints.BRANDS);
        
        CommonResponseDO dto = ApiHelper.parseResponse(response, CommonResponseDO.class);

        test.info("Validating API response code for unsupported method");
        softAssert.assertEquals(dto.getResponseCode(), 405,
                "Unexpected API response code.");

        test.info("Validating error message for unsupported request");
        softAssert.assertEquals(dto.getMessage(),
                "This request method is not supported.",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }
}
