package com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.tablepage;

import com.coherent.training.selenium.kapitsa.web.pages.base.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class TableSortSearchPage extends BasePageObject {
    private final By table = By.id("example");
    private final By showEntriesDropDown = By.name("example_length");
    private static final String MAX_NUM_OF_ENTRIES = "100";
    private static final String MORE_CONDITION = "more";
    private static final String LESS_CONDITION = "less";
    private static final String MORE_OR_EQUAL_CONDITION = "moreOrEqual";
    private static final String LESS_OR_EQUAL_CONDITION = "lessOrEqual";
    private static final String NAME_COLUMN = "Name";
    private static final String POSITION_COLUMN = "Position";
    private static final String OFFICE_COLUMN = "Office";
    private static final String AGE_COLUMN = "Age";
    private static final String SALARY_COLUMN = "Age";
    private static final String ATTRIBUTE_VALUE = "data-order";

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

    private List<Integer> getListOfRowNumbers(String numberOfEntries){
        List<Integer> listOfRowNumbers = new ArrayList<>();
        List<WebElement> rowElementInTable = getRowsInTable(numberOfEntries);
        int position = 1;

        for(WebElement ignored : rowElementInTable){
            listOfRowNumbers.add(position);
            position++;
        }

        return listOfRowNumbers;
    }

    private List<WebElement> getHeadings(){
        WebElement headingRow = findElementAtElement(table, By.xpath(".//tr[1]"));

        return findElementsAtElement(headingRow, By.xpath(".//th"));
    }

    private Map<String, WebElement> getRowHeadingsMap(List<WebElement> headingColumns, List<WebElement> row){
        Map<String, WebElement> rowByHeadings = new HashMap<>();

        for (int i = 0; i < headingColumns.size(); i++) {
            String heading = headingColumns.get(i).getText();
            WebElement cell = row.get(i);

            rowByHeadings.put(heading, cell);
        }

        return rowByHeadings;
    }

    private List<Map<String, WebElement>> getRowsWithColumnsByHeadings(){
        List<List<WebElement>> rowsWithColumns = getRowsWithColumns();
        List<Map<String, WebElement>> rowsWithColumnsByHeading = new ArrayList<>();
        Map<String, WebElement> rowByHeadings;
        List<WebElement> headingColumns = getHeadings();

        for(List<WebElement> row : rowsWithColumns){
            rowByHeadings = getRowHeadingsMap(headingColumns, row);
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

    private WebElement getElementFromCell(int rowNumber, String columnName){
        List<Map<String, WebElement>> rowsWithColumnsByHeadings = getRowsWithColumnsByHeadings();

        return rowsWithColumnsByHeadings.get(rowNumber - 1).get(columnName);
    }

    private int getIntValueFromCell(int rowNumber, String columnName){
        return columnName.equals(AGE_COLUMN) ?
                Integer.parseInt(getElementFromCell(rowNumber, columnName).getText()) :
                Integer.parseInt(getElementFromCell(rowNumber, columnName).getAttribute(ATTRIBUTE_VALUE));
    }

    private List<Integer> getSortedRowNumbersList(SortCriteria criteria, List<Integer> listForSorting){
        List<Integer> rowNumbersWhereValueMoreThan = new ArrayList<>();
        List<Integer> rowNumbersWhereValueMoreOrEqualThan = new ArrayList<>();
        List<Integer> rowNumbersWhereValueLessThan = new ArrayList<>();
        List<Integer> rowNumbersWhereValueLessOrEqualThan = new ArrayList<>();
        List<Integer> rowNumbersAfterFiltering = new ArrayList<>();

        for (Integer rowNumber : listForSorting) {
            String columnName = criteria.getColumnName();

            int intValue = getIntValueFromCell(rowNumber, columnName);

            int value = criteria.getValue();

            if(intValue > value){
                rowNumbersWhereValueMoreThan.add(rowNumber);
                rowNumbersWhereValueMoreOrEqualThan.add(rowNumber);
            } else if(intValue < value){
                rowNumbersWhereValueLessThan.add(rowNumber);
                rowNumbersWhereValueLessOrEqualThan.add(rowNumber);
            } else {
                rowNumbersWhereValueMoreOrEqualThan.add(rowNumber);
                rowNumbersWhereValueLessOrEqualThan.add(rowNumber);
            }
        }

        String condition = criteria.getCondition();

        switch (condition){
            case MORE_CONDITION:
                rowNumbersAfterFiltering = rowNumbersWhereValueMoreThan;
                break;
            case MORE_OR_EQUAL_CONDITION:
                rowNumbersAfterFiltering = rowNumbersWhereValueMoreOrEqualThan;
                break;
            case LESS_CONDITION:
                rowNumbersAfterFiltering = rowNumbersWhereValueLessThan;
                break;
            case LESS_OR_EQUAL_CONDITION:
                rowNumbersAfterFiltering = rowNumbersWhereValueLessOrEqualThan;
                break;
        }

        return rowNumbersAfterFiltering;
    }

    private List<Integer> getRowNumbersFilteredFirstlyBy(SortCriteria criteria){
        List<Integer> listOfRowNumbers = getListOfRowNumbers(MAX_NUM_OF_ENTRIES);

        return getSortedRowNumbersList(criteria, listOfRowNumbers);
    }

    private List<Integer> getRowNumbersFilteredNextBy(SortCriteria criteria, List<Integer> rowNumAfterFirstFilter){
        return getSortedRowNumbersList(criteria, rowNumAfterFirstFilter);
    }

    private List<Integer> getRowsNumbersSortedBySeveralColumns(List<SortCriteria> listOfSortCriteria) {
        List<Integer> rowNumbersAfterFirstFilter = new ArrayList<>();
        List<Integer> rowNumbersAfterLastFilter = new ArrayList<>();
        int count = 0;

        for (SortCriteria criteria : listOfSortCriteria) {
            if(count==0){
                rowNumbersAfterFirstFilter = getRowNumbersFilteredFirstlyBy(criteria);
            } else {
                rowNumbersAfterLastFilter = getRowNumbersFilteredNextBy(criteria, rowNumbersAfterFirstFilter);
            }

            count++;
        }

        return rowNumbersAfterLastFilter;
    }

    public List<TableRecord> getListOfRecordsSortedByAgeAndSalary(int age, int salary, String ageCondition, String salaryCondition){
        List<TableRecord> tableRecords = new ArrayList<>();
        SortCriteria ageCriteria = new SortCriteria(AGE_COLUMN, age, ageCondition);
        SortCriteria salaryCriteria = new SortCriteria(SALARY_COLUMN, salary, salaryCondition);
        List<SortCriteria> listOfSortCriteria = new ArrayList<>();
        Collections.addAll(listOfSortCriteria, ageCriteria, salaryCriteria);

        for(Integer rowNumber : getRowsNumbersSortedBySeveralColumns(listOfSortCriteria)){
            String name = getElementFromCell(rowNumber, NAME_COLUMN).getText();
            String position = getElementFromCell(rowNumber, POSITION_COLUMN).getText();
            String office = getElementFromCell(rowNumber, OFFICE_COLUMN).getText();

            TableRecord record = new TableRecord(name, position, office);
            tableRecords.add(record);
        }

        return tableRecords;
    }
}
