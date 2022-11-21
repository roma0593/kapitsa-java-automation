package com.coherent.training.selenium.kapitsa.web.utils;

import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataUtilization {
    private static final String CRED_JSON_PATH = "src/test/resources/browserTests/credentials.json";
    private static final String REG_DATA_PATH = "src/test/resources/browserTests/onliner_data.json";
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

    @DataProvider(name = "onlinerDataForFiltering")
    public static Object[][] onlinerDataProvider() {
        jsonParser = new JsonParser();

        List<Map<String, String>> onlinerDataMapList = jsonParser.getOnlinerDataFromJson(new File(REG_DATA_PATH));

        List<String> keyList = List.of("categoryName", "subCategoryName", "productName", "filter1", "filter2");

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
}

