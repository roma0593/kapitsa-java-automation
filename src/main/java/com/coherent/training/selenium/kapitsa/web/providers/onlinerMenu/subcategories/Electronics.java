package com.coherent.training.selenium.kapitsa.web.providers.onlinerMenu.subcategories;

import com.coherent.training.selenium.kapitsa.web.providers.onlinerMenu.category.Category;

import java.util.ArrayList;
import java.util.List;

public enum Electronics {
    MOBPHONE_ACCESSORY("Мобильные телефоны и аксессуары"),
    TELEVISION_VIDEO("Телевидение и видео"),
    TABLET_EBOOKS("Планшеты, электронные книги"),
    AUDIOTECH("Аудиотехника"),
    HIFI("Hi-Fi аудио"),
    PHOTO_VIDEO("Фото- и видеотехника"),
    VIDEOGAMES("Видеоигры"),
    GADGETS("Гаджеты"),
    SMARTHOME_CAMERAS("Умный дом и видеонаблюдение"),
    ETRANSPORT("Электрически транспорт"),
    TEL_CONNECTION("Телефония и связь"),
    MUSIC_EQUIPMENT("Музыкальное оборудование"),
    OPTIC_TOOLS("Оптические приборы");

    private final String subCategoryName;

    Electronics(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public static List<String> getElectronicsSubCatList(){
        Electronics[] subCategoryList = Electronics.values();
        List<String> subCategoryStringList = new ArrayList<>();

        for (Electronics subCategory : subCategoryList){
            String categoryName = subCategory.getSubCategoryName();
            subCategoryStringList.add(categoryName.replaceAll("\\s", ""));
        }

        return subCategoryStringList;
    }

    private String getSubCategoryName() {
        return subCategoryName;
    }
}
