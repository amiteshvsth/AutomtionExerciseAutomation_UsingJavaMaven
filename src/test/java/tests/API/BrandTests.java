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

        BrandService brandService = new BrandService();
        ApiResponse<BrandsResponseDO> response = brandService.getAllBrands();

        Assert.assertEquals(response.getDto().getResponseCode(), 200);
        Assert.assertNotNull(response.getDto().getBrands());

        List<BrandDO> brands = response.getDto().getBrands();
        Assert.assertFalse(brands.isEmpty(), "Brands list should not be empty");

        BrandDO brand = brands.getFirst();
        Assert.assertNotNull(brand.getBrand());
    }

    @Test
    public void verifyThatWeAreNotAbleToAddNewBrands() {

        BrandService brandService = new BrandService();
        ApiResponse<CommonResponseDO> response = brandService.addNewBrand();

        Assert.assertEquals(response.getDto().getResponseCode(), 405, "Unexpected status code.");
        Assert.assertEquals(response.getDto().getMessage(), "This request method is not supported.", "Brands should not be added to the page but its added");
    }

}