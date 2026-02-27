package tests.API;

import API.client.ApiResponse;
import API.dataObjects.response.common.CommonResponseDO;
import API.services.ProductService;
import API.dataObjects.response.product.ProductsResponseDO;
import API.dataObjects.response.product.ProductDO;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTests extends BaseTest {

    @Test
    public void verifyThatAllProductsAreReturnedSuccessfully() {

        test.info("===== TEST START: Verify all products are returned successfully =====");

        ProductService productService = new ProductService();

        test.info("Step 1: Sending request to retrieve all products");
        ApiResponse<ProductsResponseDO> response = productService.getAllProducts();

        test.info("Step 2: Validating HTTP status code");
        Assert.assertEquals(response.getStatusCode(), 200,
                "Unexpected HTTP status code.");

        test.info("Step 3: Validating products list is not null");
        Assert.assertNotNull(response.getDto().getProducts(),
                "Products list is null.");

        List<ProductDO> products = response.getDto().getProducts();

        test.info("Step 4: Validating products list is not empty");
        Assert.assertFalse(products.isEmpty(),
                "Products list should not be empty.");

        test.info("Step 5: Validating product details for first product");
        ProductDO product = products.getFirst();

        Assert.assertNotNull(product.getId(), "Product ID is null.");
        Assert.assertNotNull(product.getName(), "Product name is null.");
        Assert.assertNotNull(product.getPrice(), "Product price is null.");
        Assert.assertNotNull(product.getBrand(), "Product brand is null.");
        Assert.assertNotNull(product.getCategory().getUsertype(),
                "Product user type is null.");
        Assert.assertNotNull(product.getCategory().getCategory(),
                "Product category is null.");

        test.info("===== TEST PASSED: All products retrieved and validated successfully =====");
    }


    @Test
    public void verifyThatWeAreNotAbleToAddProducts() {

        test.info("===== TEST START: Verify adding new product is not allowed =====");

        ProductService productService = new ProductService();

        test.info("Step 1: Sending request to add a new product");
        ApiResponse<CommonResponseDO> response =
                productService.addANewProduct("Adidas shoes");

        test.info("Step 2: Validating API response code for unsupported method");
        Assert.assertEquals(response.getDto().getResponseCode(), 405,
                "Unexpected API response code.");

        test.info("Step 3: Validating error message for unsupported request");
        Assert.assertEquals(response.getDto().getMessage(),
                "This request method is not supported.",
                "Unexpected error message returned.");

        test.info("===== TEST PASSED: API correctly rejected product creation request =====");
    }


    @Test
    public void verifyThatWeAreAbleToSearchProducts() {

        test.info("===== TEST START: Verify product search returns matching results =====");

        ProductService productService = new ProductService();

        test.info("Step 1: Sending product search request with valid keyword");
        ApiResponse<ProductsResponseDO> response =
                productService.searchProduct("Adidas shoes");

        test.info("Step 2: Validating API response code");
        Assert.assertEquals(response.getDto().getResponseCode(), 200,
                "Unexpected API response code.");

        test.info("Step 3: Validating search results are returned");
        Assert.assertNotNull(response.getDto().getProducts(),
                "Search results list is null.");

        test.info("===== TEST PASSED: Product search executed successfully =====");
    }


    @Test
    public void verifyThatSearchingProductsWithoutParameterFails() {

        test.info("===== TEST START: Verify product search fails without required parameter =====");

        ProductService productService = new ProductService();

        test.info("Step 1: Sending product search request without search parameter");
        ApiResponse<CommonResponseDO> response =
                productService.searchProductWithoutParameter();

        test.info("Step 2: Validating API response code for bad request");
        Assert.assertEquals(response.getDto().getResponseCode(), 400,
                "Unexpected API response code.");

        test.info("Step 3: Validating error message for missing search parameter");
        Assert.assertEquals(response.getDto().getMessage(),
                "Bad request, search_product parameter is missing in POST request.",
                "Unexpected error message returned.");

        test.info("===== TEST PASSED: Proper validation error returned for missing search parameter =====");
    }
}