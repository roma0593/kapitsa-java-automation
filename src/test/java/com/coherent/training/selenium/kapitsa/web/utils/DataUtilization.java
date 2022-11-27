package com.coherent.training.selenium.kapitsa.web.utils;

import com.coherent.training.selenium.kapitsa.web.utils.pojos.Credentials;
import com.coherent.training.selenium.kapitsa.web.utils.pojos.Filter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.DataProvider;
import org.testng.collections.Lists;

import java.io.File;
import java.util.*;

public class DataUtilization {
    private static final String CRED_JSON_PATH = "src/test/resources/browserTests/credentials.json";
    private static final String ONLINER_DATA_PATH = "src/test/resources/browserTests/onliner_data.json";
    private static JsonParser jsonParser;

    @SneakyThrows
    @DataProvider(name = "credProvider")
    public static Object[][] credProvider() {
        jsonParser = new JsonParser();

        Credentials[] credentialArray = jsonParser.getCredArrayFromJson(new File(CRED_JSON_PATH));

        String[][] dataArray = new String[credentialArray.length][2];

        String username, password;

        int arrayIndex = 0;

        for (Credentials credentials : credentialArray) {
            username = credentials.getUsername();
            password = credentials.getPassword();

            dataArray[arrayIndex][0] = username;
            dataArray[arrayIndex][1] = password;

            arrayIndex++;
        }

        return dataArray;
    }


    private static Object[][] filterProvider(){
        jsonParser = new JsonParser();

        Filter[] filterArray = jsonParser.getFilterArrayFromJson(new File(ONLINER_DATA_PATH));

        String[][] dataArray = new String[filterArray.length][5];

        String vendor, shop, type, color, pickUpInOneClick;

        int arrayIndex = 0;

        for (Filter filter : filterArray) {
            vendor = filter.getVendor();
            shop = filter.getShop();
            type = filter.getType();
            color = filter.getColor();
            pickUpInOneClick = filter.getPickUpInOneClick();

            List<String> filterValues = Arrays.asList(vendor, shop, type, color, pickUpInOneClick);

            int filterValueId = 0;

            for(String value : filterValues){
                if (StringUtils.isNotBlank(value)) {
                    dataArray[arrayIndex][filterValueId++] = value;
                }
            }

            arrayIndex++;
        }

        filterNulls(dataArray);

        return dataArray;
    }

    private static Object[][] onlinerMenu() {
        List<String> keyList = List.of("categoryName", "subCategoryName", "productName");

        return onlinerDataProvider(keyList);
    }

    @DataProvider(name = "onlinerDataForFiltering")
    public static Object[][] onlinerDataProviderForFiltering() {
        return mergeDataProviders(onlinerMenu(), filterProvider());
    }

    private static Object[][] onlinerComparing() {
        List<String> keyList = List.of("productNoToCompare1", "productNoToCompare2");

        return onlinerDataProvider(keyList);
    }

    @DataProvider(name = "onlinerDataForComparing")
    public static Object[][] onlinerComparingProvider() {
        return mergeDataProviders(onlinerMenu(), filterProvider(), onlinerComparing());
    }

    private static Object[][] onlinerDataProvider(List<String> keyList){
        jsonParser = new JsonParser();

        List<Map<String, String>> onlinerDataMapList = jsonParser.getOnlinerDataFromJson(new File(ONLINER_DATA_PATH));

        String[][] dataArray = new String[onlinerDataMapList.size()][keyList.size()];

        int arrayIndex = 0;

        for (Map<String, String> regDataMap : onlinerDataMapList) {

            List<String> listOfOnlinerData = getListOfValuesFromMap(keyList, regDataMap);

            for (int i = 0; i < listOfOnlinerData.size(); i++) {
                dataArray[arrayIndex][i] = listOfOnlinerData.get(i);
            }

            arrayIndex++;
        }

        return dataArray;
    }

    private static List<String> getListOfValuesFromMap(List<String> keysList, Map<String, String> regDataMap){
        List<String> listOfData = new ArrayList<>();

        for(String key : keysList){
            listOfData.add(regDataMap.get(key));
        }

        return listOfData;
    }

    private static Object[][] mergeDataProviders(Object[][]... dataProviders){
        List<Object[]> providers = Lists.newArrayList();

        int size = 1;

        for(Object[][] dataProvider : dataProviders){
            providers.addAll(Arrays.asList(dataProvider));
            size = dataProvider.length;
        }

        String[][] dataArray = new String[size][10];

        for(int i = 0; i < size; i++) {
            int index = 0;

            for (int j = i; j < providers.size() * size; j += size) {
                Object[] notNullProviders = filterNulls(providers.get(j));

                for(Object object : notNullProviders){
                    dataArray[i][index++] = (String) object;
                }
            }
        }

        return filterNullsInInnerArray(dataArray);
    }

    private static Object[][] filterNullsInInnerArray(Object[][] arr){
        List<Object[]> outerList = new ArrayList<>(arr.length);

        for (Object[] inner : arr) {
            outerList.add(filterNulls(inner));
        }

        return outerList.toArray(new Object[outerList.size()][]);
    }

    private static Object[] filterNulls(Object[] arr){
        return Arrays.stream(arr).filter(Objects::nonNull).toArray();
    }
}

