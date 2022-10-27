package com.coherent.training.selenium.kapitsa.web.providers;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.coherent.training.selenium.kapitsa.web.providers.ConfigProvider.*;

public class ConfigFileReader {
    private Properties properties;
    private Map<String, Properties> propsCache;
    private static final String CONFIG_PATH = "configs//config.properties";
    private static final String PROFILE_PATH = "configs//profile.properties";

    @SneakyThrows
    private ConfigFileReader(){
        setPropsCache();
    }

    @SneakyThrows
    private void setPropsCache(){
        List<String> propPathList = List.of(CONFIG_PATH, PROFILE_PATH);
        propsCache = new HashMap<>();
        BufferedReader reader;

        for(String propPath : propPathList){
            reader = new BufferedReader(new FileReader(propPath));
            Properties property = new Properties();
            property.load(reader);

            String propertyName = FilenameUtils.getName(propPath);

            propsCache.put(propertyName, property);
        }
    }

    private Properties getPropertyFromCache(String path){
        String propertyName = FilenameUtils.getName(path);

        return propsCache.get(propertyName);
    }

    private static class SingletonConfigFileReader{
        private static final ConfigFileReader INSTANCE = new ConfigFileReader();
    }

    public static ConfigFileReader getInstance(){
        return SingletonConfigFileReader.INSTANCE;
    }

    public String getSeleniumHubURL() {
        properties = getPropertyFromCache(PROFILE_PATH);
        String profileKey = String.format(SELENIUM_URL_KEY.getPropertyKey(), "HUB");

        String hubURL = properties.getProperty(profileKey);

        if (hubURL != null) return hubURL;
        else throw new RuntimeException("selenium hubURL is not specified");
    }

    public String getSeleniumSauceLabURL(){
        properties = getPropertyFromCache(PROFILE_PATH);
        String profileKey = String.format(SELENIUM_URL_KEY.getPropertyKey(), "SAUCELAB");

        String hubURL = properties.getProperty(profileKey);

        if (hubURL != null) return hubURL;
        else throw new RuntimeException("SauceLab hubURL is not specified");
    }

    public String getProjectDir(){
        properties = getPropertyFromCache(CONFIG_PATH);
        String projectDir = properties.getProperty(PROJECT_DIR.getPropertyKey());

        if (projectDir != null) return projectDir;
        else throw new RuntimeException("projectDir is not specified");
    }

    public String getDriverPath(String browserName){
        String webDriverPath;
        properties = getPropertyFromCache(CONFIG_PATH);

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
        properties = getPropertyFromCache(CONFIG_PATH);
        String sauceUsername = properties.getProperty(SAUCE_USERNAME.getPropertyKey());

        if (sauceUsername != null) return sauceUsername;
        else throw new RuntimeException("sauceUsername is not specified");
    }

    public String getSauceAccessKey(){
        properties = getPropertyFromCache(CONFIG_PATH);
        String sauceAccessKey = properties.getProperty(SAUCE_ACCESS_KEY.getPropertyKey());

        if (sauceAccessKey != null) return sauceAccessKey;
        else throw new RuntimeException("sauceUsername is not specified");
    }
}
