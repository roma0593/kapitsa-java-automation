package com.coherent.training.selenium.kapitsa.web.pages.onliner;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import com.coherent.training.selenium.kapitsa.web.providers.onlinerMenu.Filter;
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

    public void filterProductsBy(String... filterNames) {
        Filter.Builder builder = Filter.Builder.newInstance();

        for (int i = 0; i < filterNames.length; i++) {
            switch (i) {
                case 0:
                    builder.setFilter1(filterNames[i]);
                    break;
                case 1:
                    builder.setFilter2(filterNames[i]);
                    break;
                case 2:
                    builder.setFilter3(filterNames[i]);
                    break;
                case 3:
                    builder.setFilter4(filterNames[i]);
                    break;
                case 4:
                    builder.setFilter5(filterNames[i]);
                    break;
                case 5:
                    builder.setFilter6(filterNames[i]);
                    break;
                case 6:
                    builder.setFilter7(filterNames[i]);
                    break;
                case 7:
                    builder.setFilter8(filterNames[i]);
                    break;
                case 8:
                    builder.setFilter9(filterNames[i]);
                    break;
                case 9:
                    builder.setFilter10(filterNames[i]);
                    break;
                default:
                    throw new RuntimeException("Number of filters exceed allowable");
            }
        }

        Filter filter = builder.build();

        applyFilter(filter);
    }

    private void applyFilter(Filter filter) {
        String[] filterArray = filter.getFilterArray();

        selectCheckbox(confirmLocationButton);

        for (String filterName : filterArray) {
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
