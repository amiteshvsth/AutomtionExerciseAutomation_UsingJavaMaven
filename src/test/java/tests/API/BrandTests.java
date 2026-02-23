package tests.API;

import API.client.ApiResponse;
import API.dataObjects.response.common.CommonResponseDO;
import API.services.ProductService;
import API.dataObjects.response.brand.BrandsResponseDO;
import API.dataObjects.response.brand.BrandDO;
import API.utilities.ResponseValidator;
import org.testng.annotations.Test;

import java.util.List;

public class BrandTests extends BaseTest {

    @Test
    public void verifyThatAllBrandsAreReturnedSuccessfully() {

        ProductService productService = new ProductService();
        ApiResponse<BrandsResponseDO> response = productService.getAllBrands();

        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 200);
        ResponseValidator.validateNotNull(response.getDto().getBrands());

        List<BrandDO> brands = response.getDto().getBrands();
        ResponseValidator.validateTrue(!brands.isEmpty(),"Brands list should not be empty");

        BrandDO brand = brands.getFirst();
        ResponseValidator.validateNotNull(brand.getBrand());
    }

    @Test
    public void verifyThatWeAreNotAbleToAddNewBrands() {

        ProductService productService = new ProductService();
        ApiResponse<CommonResponseDO> response = productService.addNewBrand();

        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 405);
        ResponseValidator.validateTrue(response.getDto().getMessage().equals("This request method is not supported."), "Brands should not be added to the page but its added");
    }

}