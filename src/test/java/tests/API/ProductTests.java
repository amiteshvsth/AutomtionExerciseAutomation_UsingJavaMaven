package tests.API;

import API.client.ApiResponse;
import API.dataObjects.common.CommonResponseDO;
import API.services.ProductService;
import API.dataObjects.product.ProductsResponseDO;
import API.dataObjects.product.ProductRequestDO;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ProductTests extends BaseTest {

    @Test
    public void verifyThatAllProductsAreReturnedSuccessfully() {

        ProductService productService = new ProductService();

        SoftAssert softAssert = new SoftAssert();
        test.info("Sending request to retrieve all products");
        ApiResponse<ProductsResponseDO> response = productService.getAllProducts();

        test.info("Validating HTTP status code");
        softAssert.assertEquals(response.getStatusCode(), 200,
                "Unexpected HTTP status code.");

        test.info("Validating products list is not null");
        softAssert.assertNotNull(response.getDto().getProducts(),
                "Products list is null.");

        List<ProductRequestDO> products = response.getDto().getProducts();

        test.info("Validating products list is not empty");
        softAssert.assertFalse(products.isEmpty(),
                "Products list should not be empty.");

        test.info("Validating product details for first product");
        ProductRequestDO product = products.getFirst();

        softAssert.assertNotNull(product.getId(), "Product ID is null.");
        softAssert.assertNotNull(product.getName(), "Product name is null.");
        softAssert.assertNotNull(product.getPrice(), "Product price is null.");
        softAssert.assertNotNull(product.getBrand(), "Product brand is null.");
        softAssert.assertNotNull(product.getCategory().getUsertype(),
                "Product user type is null.");
        softAssert.assertNotNull(product.getCategory().getCategory(),
                "Product category is null.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreNotAbleToAddProducts() {

        ProductService productService = new ProductService();

        SoftAssert softAssert = new SoftAssert();
        test.info("Sending request to add a new product");
        ApiResponse<CommonResponseDO> response =
                productService.addANewProduct("Adidas shoes");

        test.info("Validating API response code for unsupported method");
        softAssert.assertEquals(response.getDto().getResponseCode(), 405,
                "Unexpected API response code.");

        test.info("Validating error message for unsupported request");
        softAssert.assertEquals(response.getDto().getMessage(),
                "This request method is not supported.",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatWeAreAbleToSearchProducts() {


        ProductService productService = new ProductService();

        SoftAssert softAssert = new SoftAssert();
        test.info("Sending product search request with valid keyword");
        ApiResponse<ProductsResponseDO> response =
                productService.searchProduct("Adidas shoes");

        test.info("Validating API response code");
        softAssert.assertEquals(response.getDto().getResponseCode(), 200,
                "Unexpected API response code.");

        test.info("Validating search results are returned");
        softAssert.assertNotNull(response.getDto().getProducts(),
                "Search results list is null.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatSearchingProductsWithoutParameterFails() {


        ProductService productService = new ProductService();

        SoftAssert softAssert = new SoftAssert();
        test.info("Sending product search request without search parameter");
        ApiResponse<CommonResponseDO> response =
                productService.searchProductWithoutParameter();

        test.info("Validating API response code for bad request");
        softAssert.assertEquals(response.getDto().getResponseCode(), 400,
                "Unexpected API response code.");

        test.info("Validating error message for missing search parameter");
        softAssert.assertEquals(response.getDto().getMessage(),
                "Bad request, search_product parameter is missing in POST request.",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }
}