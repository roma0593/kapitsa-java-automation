package com.coherent.training.selenium.kapitsa.web.utils;

import lombok.SneakyThrows;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;

import java.io.FileReader;

public class DataUtilization {
    private static final String CRED_JSON_PATH = "src/test/resources/browserTests/credentials.json";
    private static final String CRED_JSON_NAME = "credentials";
    private static final String CRED_JSON_KEY1 = "Username";
    private static final String CRED_JSON_KEY2 = "Password";

    @SneakyThrows
    @DataProvider(name = "credProvider")
    public static Object[][] credProvider(){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;

        Object object = jsonParser.parse(new FileReader(CRED_JSON_PATH));
        jsonObject = (JSONObject) object;

        assert jsonObject != null;

        JSONArray credentials = (JSONArray) jsonObject.get(CRED_JSON_NAME);

        String[][] dataArray = new String[credentials.size()][2];

        JSONObject credentialsData;

        String username, password;

        for (int i = 0; i < credentials.size(); i++) {
            credentialsData = (JSONObject) credentials.get(i);
            username = (String) credentialsData.get(CRED_JSON_KEY1);
            password = (String) credentialsData.get(CRED_JSON_KEY2);

            dataArray[i][0] = username;
            dataArray[i][1] = password;
        }

        return dataArray;
    }
}
