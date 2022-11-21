package com.coherent.training.selenium.kapitsa.web.pages.onliner;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import com.coherent.training.selenium.kapitsa.web.providers.onlinerMenu.Linkage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;

public class HomePage extends BasePageObject {
    @FindBy(xpath = "//ul[@class='catalog-navigation-classifier ']")
    private WebElement catalogNavigation;
    @FindBy(xpath = "//div[@class='catalog-navigation-list__aside-item catalog-navigation-list__aside-item_active']//a[@class='catalog-navigation-list__dropdown-item']")
    private List<WebElement> productItems;
    private final Linkage linkage = new Linkage();
    private final List<String> categoryList = linkage.getListOfCategories();
    private final Map<String, List<String>> categorySubCategoriesMap = linkage.getCategorySubCategoriesMap();
    private final Map<String, List<String>> subCategoryProductsMap = linkage.getSubCategoryProductsMap();

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ProductListPage openProductList(String categoryName, String subCategoryName, String productName){
        openCategory(categoryName);
        openSubCategory(categoryName, subCategoryName);
        openProduct(subCategoryName, productName);

        switchToCurrentWindow();
        return new ProductListPage(driver);
    }

    public void openCategory(String categoryName){
        int categoryId = getCategoryId(categoryName);

        String categoryItemPattern = "//li[@class='catalog-navigation-classifier__item ' and @data-id = '%s']";
        String categoryItemXpath = String.format(categoryItemPattern, categoryId);

        WebElement catalogElement = findElementAtElement(catalogNavigation, By.xpath(categoryItemXpath));
        clickOn(catalogElement);
    }

    public void openSubCategory(String categoryName, String subCategoryName){
        int categoryId = getCategoryId(categoryName);

        int subCategoryId = getSubCategoryId(categoryName, subCategoryName);

        String subCategoryItemsPattern = "//div[@class='catalog-navigation-list__category' and @data-id='%s']//div[@class='catalog-navigation-list__aside-item']";
        String subCategoryItemsXpath = String.format(subCategoryItemsPattern, categoryId);

        WebElement subCategoryElement = findAll(By.xpath(subCategoryItemsXpath)).get(subCategoryId);

        hoverOverAndClick(subCategoryElement);
    }

    public void openProduct(String subCategoryName, String productName){
        int productId = getProductId(subCategoryName, productName);

        WebElement productItem = findAll(By.xpath("//div[@class='catalog-navigation-list__aside-item catalog-navigation-list__aside-item_active']//a[@class='catalog-navigation-list__dropdown-item']")).get(productId);

        clickOn(productItem);
    }

    private int getCategoryId(String categoryName){
        String categoryNameWithoutWS = categoryName.replaceAll("\\s", "");

        if(categoryList.contains(categoryNameWithoutWS)) return categoryList.indexOf(categoryNameWithoutWS);
        else throw new RuntimeException("Category doesn't exist");
    }

    private int getSubCategoryId(String categoryName, String subCategoryName){
        List<String> subCategoryList;
        String categoryNameWithoutWS = categoryName.replaceAll("\\s", "");
        String subCategoryNameWithoutWS = subCategoryName.replaceAll("\\s", "");

        if (categorySubCategoriesMap.containsKey(categoryNameWithoutWS)) subCategoryList = categorySubCategoriesMap.get(categoryNameWithoutWS);
        else throw new RuntimeException("Category doesn't exist");

        if (subCategoryList.contains(subCategoryNameWithoutWS)) return subCategoryList.indexOf(subCategoryNameWithoutWS);
        else throw new RuntimeException("Sub-category doesn't exist");
    }

    private int getProductId(String subCategoryName, String productName){
        List<String> productList;
        String subCategoryNameWithoutWS = subCategoryName.replaceAll("\\s", "");
        String productNameWithoutWS = productName.replaceAll("\\s", "");

        if (subCategoryProductsMap.containsKey(subCategoryNameWithoutWS)) productList = subCategoryProductsMap.get(subCategoryNameWithoutWS);
        else throw new RuntimeException("Sub-category doesn't exist");

        if (productList.contains(productNameWithoutWS)) return productList.indexOf(productNameWithoutWS);
        else throw new RuntimeException("Product doesn't exist");
    }
}
