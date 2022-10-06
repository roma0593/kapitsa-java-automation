package com.coherent.training.selenium.kapitsa.web.seleniumeasy;

import com.coherent.training.selenium.kapitsa.web.base.BaseTest;
import com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.AlertsPage;
import com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.DownloadProgressPage;
import com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.DropdownPage;
import com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.DynamicDataLoadingPage;
import com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.tablepage.TableSortSearchPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static com.coherent.training.selenium.kapitsa.web.providers.UrlProvider.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SeleniumEasyTests extends BaseTest {

    @Parameters({"numberOfOptionsToSelect"})
    @Test
    public void selectMultipleOptionsTest(int numberOfOptionsToSelect){
        driver.get(SELENIUM_EASY_DROPDOWN.getUrl());

        dropdownPage = new DropdownPage(driver);

        List<WebElement> selectedElements = dropdownPage.selectMultipleItems(numberOfOptionsToSelect);

        assertTrue(dropdownPage.checkSelectedOptions(selectedElements), "Not all options are selected");
    }

    @Test
    public void openAlertTest(){
        driver.get(SELENIUM_ALERT.getUrl());

        alertsPage = new AlertsPage(driver);

        Alert alert = alertsPage.callAlert();

        assertEquals(alert.getText(), "I am an alert box!", "Expected and actual result mismatch");
    }

    @Test
    public void acceptConfirmAlertTest(){
        driver.get(SELENIUM_ALERT.getUrl());

        alertsPage = new AlertsPage(driver);

        assertEquals(alertsPage.acceptAlert(), "You pressed OK!",
                "Expected and actual result mismatch");
    }

    @Test
    public void declineConfirmAlertTest(){
        driver.get(SELENIUM_ALERT.getUrl());

        alertsPage = new AlertsPage(driver);

        assertEquals(alertsPage.dismissAlert(), "You pressed Cancel!",
                "Expected and actual result mismatch");

    }

    @Parameters({"percent"})
    @Test
    public void refreshPageForPercentageTest(String percent){
        driver.get(SELENIUM_DOWNLOAD_PROGRESS.getUrl());

        downloadProgressPage = new DownloadProgressPage(driver);

        downloadProgressPage.refreshPageIfProgressBarMorThan(percent);
    }

    @Test
    public void loadingUserTest(){
        driver.get(SELENIUM_DYNAMIC_DATA.getUrl());

        dynamicDataLoadingPage = new DynamicDataLoadingPage(driver);

        dynamicDataLoadingPage.getRandomUser();
    }

    @Parameters({"numberOfEntries"})
    @Test
    public void selectShowNumberOfEntriesTest(String numberOfEntries){
        driver.get(SELENIUM_TABLE_SORT_SEARCH.getUrl());

        tableSortSearchPage = new TableSortSearchPage(driver);

        tableSortSearchPage.selectNumberOfEntries(String.valueOf(numberOfEntries));

        int rowsInTable = tableSortSearchPage.getRowsInTable(numberOfEntries).size();

        assertEquals(String.valueOf(rowsInTable), numberOfEntries,
                "Expected and actual result mismatch");
    }

    @Parameters({"age", "salary", "ageCondition", "salCondition"})
    @Test
    public void getTableRecordsSortedByAgeAndSalaryTest(int age, int salary, String ageCondition, String salCondition){
        driver.get(SELENIUM_TABLE_SORT_SEARCH.getUrl());

        tableSortSearchPage = new TableSortSearchPage(driver);

        System.out.println(tableSortSearchPage.getListOfRecordsSortedByAgeAndSalary(age, salary, ageCondition, salCondition));
    }
}
