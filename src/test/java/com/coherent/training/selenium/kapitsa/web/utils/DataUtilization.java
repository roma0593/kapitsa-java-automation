package com.coherent.training.selenium.kapitsa.web.utils;

import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;

import java.io.File;

public class DataUtilization {
    private static final String CRED_JSON_PATH = "src/test/resources/browserTests/credentials.json";

    @SneakyThrows
    @DataProvider(name = "credProvider")
    public static Object[][] credProvider(){
        JsonParser jsonParser = new JsonParser();

        Credentials[] credentialArray = jsonParser.getArrayReadFromJson(new File(CRED_JSON_PATH));

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
}
