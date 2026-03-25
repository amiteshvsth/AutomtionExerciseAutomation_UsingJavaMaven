package tests.API;

import API.dataObjects.common.CommonResponseDO;
import API.dataObjects.product.ProductsResponseDO;
import API.dataObjects.product.ProductRequestDO;
import API.utilities.ApiHelper;
import API.utilities.Endpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

public class ProductTests extends BaseTest {

    @Test
    public void verifyThatAllProductsAreReturnedSuccessfully() {

        SoftAssert softAssert = new SoftAssert();
        test.info("Sending request to retrieve all products");
        Response response = ApiHelper.get(Endpoints.PRODUCTS);
        
        ProductsResponseDO dto = ApiHelper.parseResponse(response, ProductsResponseDO.class);

        test.info("Validating HTTP status code");
        softAssert.assertEquals(response.getStatusCode(), 200,
                "Unexpected HTTP status code.");

        test.info("Validating products list is not null");
        softAssert.assertNotNull(dto.getProducts(),
                "Products list is null.");

        List<ProductRequestDO> products = dto.getProducts();

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

        SoftAssert softAssert = new SoftAssert();
        test.info("Sending request to add a new product");
        Response response = ApiHelper.postWithFormParameters(Endpoints.PRODUCTS, Map.of("product", "Adidas shoes"));
        
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


    @Test
    public void verifyThatWeAreAbleToSearchProducts() {

        SoftAssert softAssert = new SoftAssert();
        test.info("Sending product search request with valid keyword");
        Response response = ApiHelper.postWithFormParameters(Endpoints.SEARCH_PRODUCT, Map.of("search_product", "Adidas shoes"));
        
        ProductsResponseDO dto = ApiHelper.parseResponse(response, ProductsResponseDO.class);

        test.info("Validating API response code");
        softAssert.assertEquals(dto.getResponseCode(), 200,
                "Unexpected API response code.");

        test.info("Validating search results are returned");
        softAssert.assertNotNull(dto.getProducts(),
                "Search results list is null.");

        softAssert.assertAll();
    }


    @Test
    public void verifyThatSearchingProductsWithoutParameterFails() {

        SoftAssert softAssert = new SoftAssert();
        test.info("Sending product search request without search parameter");
        Response response = ApiHelper.post(Endpoints.SEARCH_PRODUCT);
        
        CommonResponseDO dto = ApiHelper.parseResponse(response, CommonResponseDO.class);

        test.info("Validating API response code for bad request");
        softAssert.assertEquals(dto.getResponseCode(), 400,
                "Unexpected API response code.");

        test.info("Validating error message for missing search parameter");
        softAssert.assertEquals(dto.getMessage(),
                "Bad request, search_product parameter is missing in POST request.",
                "Unexpected error message returned.");

        softAssert.assertAll();
    }
}
