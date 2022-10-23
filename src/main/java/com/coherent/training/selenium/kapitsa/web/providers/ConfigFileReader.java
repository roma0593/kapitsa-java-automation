package com.coherent.training.selenium.kapitsa.web.providers;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class ConfigFileReader {
    private final Properties properties;
    private static final String CONFIG_PATH = "configs//config.properties";

    @SneakyThrows
    public ConfigFileReader() {
        BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH));
        properties = new Properties();
        properties.load(reader);
    }

    public String getHubURL(){
        String hubURL = properties.getProperty("hubURL");

        if (hubURL != null) return hubURL;
        else throw new RuntimeException("hubURL is not specified");
    }
}
