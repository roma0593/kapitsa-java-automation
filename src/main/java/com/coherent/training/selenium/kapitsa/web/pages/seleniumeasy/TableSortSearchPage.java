package com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class TableSortSearchPage extends BasePageObject {
    private final By table = By.id("example");
    private final By showEntriesDropDown = By.name("example_length");
    private static final String MAX_NUM_OF_ENTRIES = "100";

    public TableSortSearchPage(WebDriver driver) {
        super(driver);
    }

    public void selectNumberOfEntries(String dropdownValue){
        selectDropdownOptionByValue(showEntriesDropDown, dropdownValue);
    }

    public List<WebElement> getRowsInTable(String numberOfEntries){
        selectNumberOfEntries(numberOfEntries);

        List<WebElement> rows = findElementsAtElement(find(table), By.xpath(".//tr"));
        rows.remove(0);

        return rows;
    }

    private List<WebElement> getHeadings(){
        WebElement headingRow = findElementAtElement(table, By.xpath(".//tr[1]"));

        return findElementsAtElement(headingRow, By.xpath(".//th"));
    }

    private List<Map<String, WebElement>> getRowsWithColumnsByHeadings(){
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        List<Map<String, WebElement>> rowsWithColumnsByHeading = new ArrayList<>();
        Map<String, WebElement> rowByHeadings;
        List<WebElement> headingColumns = getHeadings();

        for(List<WebElement> row : rowsWithColumns){
            rowByHeadings = new HashMap<>();

            for (int i = 0; i < headingColumns.size(); i++) {
                String heading = headingColumns.get(i).getText();
                WebElement cell = row.get(i);

                rowByHeadings.put(heading, cell);
            }

            rowsWithColumnsByHeading.add(rowByHeadings);
        }

        return rowsWithColumnsByHeading;
    }

    private List<List<WebElement>> getRowsWithColumns(){
        List<WebElement> rows = getRowsInTable(MAX_NUM_OF_ENTRIES);
        List<List<WebElement>> rowsWithColumns = new ArrayList<>();

        for (WebElement row : rows){
            List<WebElement> rowWithColumns = findElementsAtElement(row, By.xpath(".//td"));
            rowsWithColumns.add(rowWithColumns);
        }

        return rowsWithColumns;
    }

    private WebElement getValueFromCell(int rowNumber, String columnName){
        List<Map<String, WebElement>> rowsWithColumnsByHeadings = getRowsWithColumnsByHeadings();

        return rowsWithColumnsByHeadings.get(rowNumber - 1).get(columnName);
    }

    private List<Integer> getRowsNumbersSortedByAgeAndSalary(int minAge, int maxSalary){
        int numberOfRows = getRowsInTable(MAX_NUM_OF_ENTRIES).size();

        List<Integer> rowNumbersAfterFilteringByAge = new ArrayList<>();
        List<Integer> rowNumbersAfterFilteringBySalary = new ArrayList<>();

        for (int i = 1; i < numberOfRows; i++) {
            String ageCellValue = getValueFromCell(i, "Age").getText();
            int age = Integer.parseInt(ageCellValue);

            if(age > minAge)
                rowNumbersAfterFilteringByAge.add(i);
        }

        for(Integer rowNumber : rowNumbersAfterFilteringByAge){
            String salaryCellValue = getValueFromCell(rowNumber, "Salary").getAttribute("data-order");
            int salary = Integer.parseInt(salaryCellValue);

            if(salary <= maxSalary)
                rowNumbersAfterFilteringBySalary.add(rowNumber);
        }

        return rowNumbersAfterFilteringBySalary;
    }

    public List<TableRecord> getListOfRecordsSortedByAgeAndSalary(int minAge, int maxSalary){
        List<TableRecord> tableRecords = new ArrayList<>();

        for(Integer rowNumber : getRowsNumbersSortedByAgeAndSalary(minAge, maxSalary)){
            String name = getValueFromCell(rowNumber, "Name").getText();
            String position = getValueFromCell(rowNumber, "Position").getText();
            String office = getValueFromCell(rowNumber, "Office").getText();

            TableRecord record = new TableRecord(name, position, office);
            tableRecords.add(record);
        }

        return tableRecords;
    }
}
