package com.coherent.training.selenium.kapitsa.web.providers.onlinerMenu;

import com.coherent.training.selenium.kapitsa.web.providers.onlinerMenu.category.Category;
import com.coherent.training.selenium.kapitsa.web.providers.onlinerMenu.products.Gadgets;
import com.coherent.training.selenium.kapitsa.web.providers.onlinerMenu.subcategories.Electronics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Linkage {
    private final Map<String, List<String>> categorySubCategoriesMap = new HashMap<>();
    private final Map<String, List<String>> subCategoryProductsMap = new HashMap<>();
    private static final List<String> LIST_OF_CATEGORIES = Category.getCategoryList();
    private static final List<String> ELECTRONICS_SUBCATEGORY_LIST = Electronics.getElectronicsSubCatList();
    private static final List<String> GADGET_PRODUCT_LIST = Gadgets.getGadgetProductList();

    public Map<String, List<String>> getCategorySubCategoriesMap() {
        return categorySubCategoriesMap;
    }

    public Map<String, List<String>> getSubCategoryProductsMap() {
        return subCategoryProductsMap;
    }

    public Linkage() {
        fillCategorySubCategoriesMap();
        fillSubCategoryProductsMap();
    }

    private void fillCategorySubCategoriesMap(){
        for(String category : LIST_OF_CATEGORIES){
            if(category.equals("Электроника")) categorySubCategoriesMap.put(category, ELECTRONICS_SUBCATEGORY_LIST);
        }
    }

    private void fillSubCategoryProductsMap(){
        for(String subCategory : ELECTRONICS_SUBCATEGORY_LIST){
            if(subCategory.equals("Гаджеты")) subCategoryProductsMap.put(subCategory, GADGET_PRODUCT_LIST);
        }
    }

    public List<String> getListOfCategories(){
        return LIST_OF_CATEGORIES;
    }
}
