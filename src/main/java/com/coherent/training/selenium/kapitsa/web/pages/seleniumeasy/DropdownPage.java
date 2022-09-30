package com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DropdownPage extends BasePageObject {
    private final By listOfItems = By.id("multi-select");

    public DropdownPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> selectMultipleItems(int numberToSelect){
        return multiselect(listOfItems, numberToSelect);
    }

    public boolean checkSelectedOptions(List<WebElement> selectedElements){
        boolean isOptionIsSelected = false;

        for(WebElement element : selectedElements){
            if(element.isSelected()) isOptionIsSelected = true;
        }

        return isOptionIsSelected;
    }
}
