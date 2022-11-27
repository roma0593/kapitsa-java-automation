package com.coherent.training.selenium.kapitsa.web.providers.onlinerMenu.category;

import java.util.ArrayList;
import java.util.List;

public enum Category {
    ONLINER_PRIME("Onliner Prime"),
    ELECTRONICS("Электроника"),
    COMP_NET("Компьютеры и сети"),
    APPLIANCES("Бытовая техника"),
    BUILD_REPAIR("Стройка и ремонт"),
    HOUSE_GARDEN("Дом и сад"),
    AUTO_MOTO("Авто и мото"),
    BEAUTY_SPORT("Красота и спорт"),
    CHILD_MOMS("Детям и мамам"),
    WORK_OFFICE("Работа и офис");

    private final String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public static List<String> getCategoryList(){
        Category[] categoryList = Category.values();
        List<String> categoryStringList = new ArrayList<>();

        for (Category category : categoryList){
            String categoryName = category.getCategoryName();
            categoryStringList.add(categoryName.replaceAll("\\s", ""));
        }

        return categoryStringList;
    }

    private String getCategoryName(){
        return categoryName;
    }
}
