package tests.API;

import API.client.ApiResponse;
import API.dataObjects.response.common.CommonResponseDO;
import API.services.ProductService;
import API.dataObjects.response.product.ProductsResponseDO;
import API.dataObjects.response.product.ProductDO;
import API.utilities.ResponseValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductTests extends BaseTest {

    @Test
    public void verifyThatAllProductsAreReturnedSuccessfully() {

        ProductService productService = new ProductService();
        ApiResponse<ProductsResponseDO> response = productService.getAllProducts();

        ResponseValidator.validateStatusCode(response.getStatusCode(), 200);
        ResponseValidator.validateNotNull(response.getDto().getProducts());

        List<ProductDO> products = response.getDto().getProducts();
        ResponseValidator.validateTrue(!products.isEmpty(), "Products list should not be empty" );

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
        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 405);
        ResponseValidator.validateTrue(response.getDto().getMessage().equals("This request method is not supported."),"Product should not be added to the page but its added");
    }

    @Test
    public void verifyThatWeAreAbleToSearchProducts() {

        ProductService productService = new ProductService();
        ApiResponse<ProductsResponseDO> response = productService.searchProduct("Adidas shoes");
        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 200);
        ResponseValidator.validateNotNull(response.getDto().getProducts());
    }

    @Test
    public void verifyThatSearchingProductsWithoutParameterFails() {

        ProductService productService = new ProductService();
        ApiResponse<CommonResponseDO> response = productService.searchProductWithoutParameter();
        ResponseValidator.validateStatusCode(response.getDto().getResponseCode(), 400);
        ResponseValidator.validateTrue(response.getDto().getMessage().equals("Bad request, search_product parameter is missing in POST request."),"Search without parameter should not be working");
    }

}