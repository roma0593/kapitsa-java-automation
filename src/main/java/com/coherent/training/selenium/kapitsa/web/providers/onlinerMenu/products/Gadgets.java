package com.coherent.training.selenium.kapitsa.web.providers.onlinerMenu.products;

import java.util.ArrayList;
import java.util.List;

public enum Gadgets {
    SMART_WATCHES("Умные часы"),
    FITNESS_BRACELETS("Фитнес-браслеты"),
    HART_RATE_MONITOR("Пульсометры"),
    STRAPS_BRACELETS("Ремешки и браслеты"),
    VR_GLASSES("Очки виртуальной реальности"),
    SMART_SCALES("Умные весы"),
    SLEEP_GADGETS("Гаджеты для сна"),
    SCIENCE_TOOLS("Нитратометры, дозиметры, экотестеры"),
    E_STEAM_GENERATORS("Электронные парогенераторы");

    private final String productName;

    Gadgets(String productName) {
        this.productName = productName;
    }

    public static List<String> getGadgetProductList(){
        Gadgets[] productList = Gadgets.values();
        List<String> productStringList = new ArrayList<>();

        for (Gadgets product : productList){
            String productName = product.getProductName();
            productStringList.add(productName.replaceAll("\\s", ""));
        }

        return productStringList;
    }

    private String getProductName() {
        return productName;
    }
}
