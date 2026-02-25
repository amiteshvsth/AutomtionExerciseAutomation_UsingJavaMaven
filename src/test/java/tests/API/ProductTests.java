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

        ProductService productService = new ProductService();
        ApiResponse<ProductsResponseDO> response = productService.getAllProducts();

        Assert.assertEquals(response.getStatusCode(), 200,"Unexpected status code.");
        Assert.assertNotNull(response.getDto().getProducts());

        List<ProductDO> products = response.getDto().getProducts();
        Assert.assertFalse(products.isEmpty(), "Products list should not be empty");

        ProductDO product = products.getFirst();
        Assert.assertNotNull(product.getId(), "Product ID is null");
        Assert.assertNotNull(product.getName(), "Product name is null");
        Assert.assertNotNull(product.getPrice(), "Product price is null");
        Assert.assertNotNull(product.getBrand(), "Product brand is null");
        Assert.assertNotNull(product.getCategory().getUsertype(), "Product usertype is null");
        Assert.assertNotNull(product.getCategory().getCategory(), "Product category is null");
    }

    @Test
    public void verifyThatWeAreNotAbleToAddProducts() {

        ProductService productService = new ProductService();
        ApiResponse<CommonResponseDO> response = productService.addANewProduct("Adidas shoes");
        Assert.assertEquals(response.getDto().getResponseCode(), 405,"Unexpected response code.");
        Assert.assertEquals(response.getDto().getMessage(), "This request method is not supported.","Product should not be added to the page but its added");
    }

    @Test
    public void verifyThatWeAreAbleToSearchProducts() {

        ProductService productService = new ProductService();
        ApiResponse<ProductsResponseDO> response = productService.searchProduct("Adidas shoes");
        Assert.assertEquals(response.getDto().getResponseCode(), 200,"Unexpected response code.");
        Assert.assertNotNull(response.getDto().getProducts());
    }

    @Test
    public void verifyThatSearchingProductsWithoutParameterFails() {

        ProductService productService = new ProductService();
        ApiResponse<CommonResponseDO> response = productService.searchProductWithoutParameter();
        Assert.assertEquals(response.getDto().getResponseCode(), 400,"Unexpected response code.");
        Assert.assertEquals(response.getDto().getMessage(), "Bad request, search_product parameter is missing in POST request.","Search without parameter should not be working");
    }

}