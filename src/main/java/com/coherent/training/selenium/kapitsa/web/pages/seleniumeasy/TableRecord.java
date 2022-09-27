package com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy;

public class TableRecord {
    private String name;
    private String position;
    private String office;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "{" + name + ", " + position + ", " + office + "}";
    }
}
