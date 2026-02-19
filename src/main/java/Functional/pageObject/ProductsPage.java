package Functional.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends CommonPage {

    private final By viewProductLinks = By.xpath("//a[contains(@href, '/product_details/')]");
    private final By addToCartButtons = By.xpath("//a[@class='btn btn-default add-to-cart']");
    private final By productPrices = By.xpath("//div[@class='productinfo text-center']/h2");
    private final By categoryLinks = By.xpath("//div[@class='left-sidebar']//div[@class='panel-heading']//a");
    private final By searchInput = By.id("search_product");
    private final By searchButton = By.id("submit_search");
    private final By productsHeader = By.xpath("//h2[@class='title text-center']");
    private final By productCategories = By.xpath("//div[@id='accordian']//a[@data-parent='#accordian']");
    private final By brands = By.xpath("//div[@class='brands-name']/ul/li/a");
    private final By categoryAndBrandNameInBreadCrumb = By.xpath("//ol[@class='breadcrumb']/li[@class='active']");
    private final By saleImage = By.id("sale_image");
    private final By productName = By.xpath("//div[@class='productinfo text-center']/p");
    private By brandName(String brandName) {
        return By.xpath(String.format(
                "//div[@class='brands-name']//a[contains(.,'%s')]",
                brandName
        ));
    }
    public ProductsPage(WebDriver driver){
        super(driver);
    }

    public void searchProduct(String product) throws InterruptedException {
        selenium.enterText(searchInput, product,true);
        selenium.clickOn(searchButton);
    }
    public void clickViewProduct(int index) {
        selenium.pageScrollInView(selenium.findElements(viewProductLinks).get(index));
        selenium.findElements(viewProductLinks).get(index).click();
    }

    public void clickAddToCart(int index) {
        selenium.findElements(addToCartButtons).get(index).click();
    }

    public String getProductName(int index) {
        return selenium.findElements(productName).get(index).getText();
    }

    public String getProductPrice(int index) {
        return selenium.findElements(productPrices).get(index).getText();
    }

    public boolean isProductsHeaderDisplayed() throws InterruptedException {
        return selenium.isElementPresent(productsHeader);
    }

    public String getProductsHeaderText() throws InterruptedException {
        return selenium.getText(productsHeader);
    }

    public int getProductCategoriesCount(){
        return selenium.findElements(productCategories).size();
    }

    public List<String> getProductSubCategories(String category) throws InterruptedException {
        selenium.clickOn(By.xpath("//div[@id='accordian']//a[@data-parent='#accordian' and @href[contains(.,'"+category+"')]]"));
        By subCategoryLocator = By.xpath(
                "//div[@id='" + category + "']//a"
        );

        selenium.waitInCaseElementVisible(subCategoryLocator,3);

        return selenium.findElements(subCategoryLocator)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<String> getProductCategories(){
        return selenium.findElements(productCategories).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public int getBrandsCount(){
        return selenium.findElements(brands).size();
    }

    public List<String> getBrandNames(){
        return selenium.findElements(brands).stream().map(e -> e.getText().replaceAll("\\(.*?\\)", "").trim()).collect(Collectors.toList());
    }

    public void clickOnBrandName(String brand) throws InterruptedException {
        selenium.clickOn(By.xpath("//div[@class='brands-name']//a[contains(.,'" + brand + "')]"));
    }

    public boolean isSaleImageDisplayed(){
        return selenium.isElementPresent(saleImage);
    }

    public List<String> getProductNames() throws InterruptedException {
        Thread.sleep(1000);
        return selenium.findElements(productName).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getCategoryAndBrandNameInBreadCrumb() throws InterruptedException {
        return selenium.getText(categoryAndBrandNameInBreadCrumb);
    }
}

