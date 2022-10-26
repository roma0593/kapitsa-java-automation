package com.coherent.training.selenium.kapitsa.web.providers;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import static com.coherent.training.selenium.kapitsa.web.providers.ConfigProvider.*;

public class ConfigFileReader {
    private final Properties properties;
    private static final String CONFIG_PATH = "configs//config.properties";

    @SneakyThrows
    private ConfigFileReader(){
        BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH));
        properties = new Properties();
        properties.load(reader);
    }

    private static class SingletonConfigFileReader{
        private static final ConfigFileReader INSTANCE = new ConfigFileReader();
    }

    public static ConfigFileReader getInstance(){
        return SingletonConfigFileReader.INSTANCE;
    }

    public String getHubURL() {
        String hubURL = properties.getProperty(HUB_URL.getPropertyKey());

        if (hubURL != null) return hubURL;
        else throw new RuntimeException("hubURL is not specified");
    }

    public String getProjectDir(){
        String projectDir = properties.getProperty(PROJECT_DIR.getPropertyKey());

        if (projectDir != null) return projectDir;
        else throw new RuntimeException("projectDir is not specified");
    }

    public String getDriverPath(String browserName){
        String webDriverPath;

        switch (browserName){
            case "chrome":
                webDriverPath = properties.getProperty(CHROME_WEBDRIVER_PATH.getPropertyKey());
            break;
            case "firefox":
                webDriverPath = properties.getProperty(FIREFOX_WEBDRIVER_PATH.getPropertyKey());
            break;
            case "edge":
                webDriverPath = properties.getProperty(EDGE_WEBDRIVER_PATH.getPropertyKey());
            break;
            default:
                throw new RuntimeException("webdriver path is not specified");
        }

        return webDriverPath;
    }

    public String getSauceUsername(){
        String sauceUsername = properties.getProperty(SAUCE_USERNAME.getPropertyKey());

        if (sauceUsername != null) return sauceUsername;
        else throw new RuntimeException("sauceUsername is not specified");
    }

    public String getSauceAccessKey(){
        String sauceAccessKey = properties.getProperty(SAUCE_ACCESS_KEY.getPropertyKey());

        if (sauceAccessKey != null) return sauceAccessKey;
        else throw new RuntimeException("sauceUsername is not specified");
    }
}
