package com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.tablepage;

public class TableRecord {
    private final String name;
    private final String position;
    private final String office;

    public TableRecord(String name, String position, String office) {
        this.name = name;
        this.position = position;
        this.office = office;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getOffice() {
        return office;
    }

    @Override
    public String toString() {
        return "{" + name + ", " + position + ", " + office + "}";
    }
}
