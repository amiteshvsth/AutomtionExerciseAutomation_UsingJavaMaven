package pageObject;

import pageObject.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");

    public ProductsPage(WebDriver driver){
        super(driver);
    }

    public void searchProduct(String product) throws InterruptedException {
        selenium.enterText(searchInput, product,true);
        selenium.clickOn(searchButton);
    }
}

