package com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.tablepage;

public class SortCriteria {
    private final String columnName;
    private final int value;
    private final String condition;

    public SortCriteria(String columnName, int value, String condition) {
        this.columnName = columnName;
        this.value = value;
        this.condition = condition;
    }

    public String getColumnName() {
        return columnName;
    }

    public int getValue() {
        return value;
    }

    public String getCondition() {
        return condition;
    }
}
