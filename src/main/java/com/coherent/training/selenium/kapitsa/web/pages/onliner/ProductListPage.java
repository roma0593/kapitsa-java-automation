package com.coherent.training.selenium.kapitsa.web.pages.onliner;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductListPage extends BasePageObject {
    @FindBy(xpath = "//span[contains(@class, 'button-style')]")
    private WebElement confirmLocationButton;
    @FindBy(xpath = "//a[@class='compare-button__sub compare-button__sub_main']")
    private WebElement compareButton;
    @FindBy(xpath = "//div[@class='schema-product__compare']")
    private List<WebElement> compareCheckboxes;
    @FindBy(xpath = "//div[@class='schema-product__group']")
    private List<WebElement> productList;

    private static final int NUMBER_OF_NOT_SORTED_PRODUCTS_ON_PAGE = 30;
    private static final String FILTER_XPATH_PATTERN = "//span[text()='%s']//preceding-sibling::span";
    private static final String FILTER_CHECKBOX_XPATH_PATTERN = "//span[text()='%s']//preceding-sibling::span//child::input";

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public void applyFilter(String... filters) {
        selectCheckbox(confirmLocationButton);

        for (String filterName : filters) {
            if (filterName != null) {
                String filterXpath = String.format(FILTER_XPATH_PATTERN, filterName);
                WebElement filterCheckbox = find(By.xpath(filterXpath));
                selectCheckbox(filterCheckbox);
            }
        }
    }

    public boolean isFiltersSelected(String... filters) {
        boolean isFilterSelected = false;

        for (String filterName : filters) {
            String filterXpath = String.format(FILTER_CHECKBOX_XPATH_PATTERN, filterName);

            WebElement filterCheckbox = driver.findElement(By.xpath(filterXpath));

            isFilterSelected = filterCheckbox.isSelected();
        }

        return isFilterSelected;
    }

    public CompareProductsPage compareProducts(String... productNumbersToCompare) {
        waitForElementsToBeStaleness(productList);

        List<WebElement> productListAfterFiltering = findAll(getWebElementsBy(productList));
        List<WebElement> compareCheckboxesAfterFiltering = findAll(getWebElementsBy(compareCheckboxes));

        if (productListAfterFiltering.size() <= 1) throw new RuntimeException("The only one product left after filtering");

        for (String productNumber : productNumbersToCompare) {
            int productNumberId = Integer.parseInt(productNumber);
            WebElement compareCheckbox = compareCheckboxesAfterFiltering.get(productNumberId - 1);

            if (productNumberId < compareCheckboxesAfterFiltering.size())
                scrollToElement(compareCheckbox);
                clickOn(compareCheckbox);
        }

        clickOn(compareButton);

        switchToCurrentWindow();

        return new CompareProductsPage(driver);
    }

    @SneakyThrows
    public boolean isProductsSorted() {
        waitForElementsToBeStaleness(productList);

        List<WebElement> productListAfterSorting = findAll(getWebElementsBy(productList));

        int numberOfSortedProducts = productListAfterSorting.size();

        return numberOfSortedProducts < NUMBER_OF_NOT_SORTED_PRODUCTS_ON_PAGE;
    }
}
