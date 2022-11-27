package com.coherent.training.selenium.kapitsa.web.pages.onliner;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompareProductsPage extends BasePageObject {
    @FindBy(xpath = "//div[@class='product-summary__price']//child::a")
    private List<WebElement> productPriceSummaries;

    public CompareProductsPage(WebDriver driver) {
        super(driver);
    }

    private List<String> getSummaryPrices(){
        List<String> summaryPrices = new ArrayList<>();

        for(int i = 0; i < productPriceSummaries.size() / 2; i++){
            String summaryPrice = productPriceSummaries.get(i).getText();
            summaryPrices.add(summaryPrice);
        }

        return summaryPrices;
    }

    public void getCheapestProductByLowerPrice(){
        List<String> summaryPrices = getSummaryPrices();
        List<Integer> lowerPrices = new ArrayList<>();

        for (String summaryPrice : summaryPrices) {
            int lowerPrice = Integer.parseInt(summaryPrice.substring(0, summaryPrice.indexOf(",")));
            lowerPrices.add(lowerPrice);
        }

        int lowerPrice = Collections.min(lowerPrices);
        int lowerPriceIndex = lowerPrices.indexOf(lowerPrice);

        System.out.println("Product " + lowerPriceIndex + 1  + " has the lowest price " + lowerPrice);
    }
}
