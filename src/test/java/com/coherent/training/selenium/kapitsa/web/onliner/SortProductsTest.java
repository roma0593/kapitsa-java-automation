package com.coherent.training.selenium.kapitsa.web.onliner;

import com.coherent.training.selenium.kapitsa.web.base.BaseTest;
import com.coherent.training.selenium.kapitsa.web.pages.onliner.HomePage;
import com.coherent.training.selenium.kapitsa.web.utils.DataUtilization;
import io.qameta.allure.*;
import lombok.SneakyThrows;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.coherent.training.selenium.kapitsa.web.providers.UrlProvider.CATALOG_ONLINER;
import static org.testng.Assert.assertTrue;

@Feature("Products filtering")
@Listeners(com.coherent.training.selenium.kapitsa.web.utils.TestListener.class)
public class SortProductsTest extends BaseTest {
    @SneakyThrows
    @Issue("ONLINER-1")
    @Test(dataProviderClass = DataUtilization.class, dataProvider = "onlinerDataForFiltering", description = "Sort product list scenario")
    @Description("Sort product list by two parameters")
    @Severity(SeverityLevel.CRITICAL)
    public void sortProductListTest(String categoryName, String subCategoryName, String productName, String filter1, String filter2){
        driver.get(CATALOG_ONLINER.getUrl());

        homePage = new HomePage(driver);

        productListPage = homePage.openProductList(categoryName, subCategoryName, productName);

        productListPage.filterProductsBy(filter1, filter2);

        Thread.sleep(5000);

        assertTrue(productListPage.isFiltersSelected(filter1, filter2), "Not all filters are selected");

        assertTrue(productListPage.isProductsSorted(), "Products are not sorted");
    }

    @SneakyThrows
    @Issue("ONLINER-2")
    @Test(dataProviderClass = DataUtilization.class, dataProvider = "onlinerDataForFiltering", description = "Compare products by price scenario")
    @Description("Compare products and get product with the lowest price")
    @Severity(SeverityLevel.CRITICAL)
    public void compareProductsTest(String categoryName, String subCategoryName, String productName, String filter1, String filter2){
        driver.get(CATALOG_ONLINER.getUrl());

        homePage = new HomePage(driver);

        productListPage = homePage.openProductList(categoryName, subCategoryName, productName);

        productListPage.filterProductsBy(filter1, filter2);

        Thread.sleep(5000);

        compareProductsPage = productListPage.compareProducts(1, 2);

        compareProductsPage.getCheapestProductByLowerPrice();
    }
}
