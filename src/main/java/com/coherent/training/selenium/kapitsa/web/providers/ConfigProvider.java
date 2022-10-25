package com.coherent.training.selenium.kapitsa.web.providers;

public enum ConfigProvider {
    HUB_URL("hubURL"),
    PROJECT_DIR("projectDir"),
    CHROME_WEBDRIVER_PATH("chromePath"),
    FIREFOX_WEBDRIVER_PATH("firefoxPath"),
    EDGE_WEBDRIVER_PATH("edgePath");

    private final String propertyKey;

    ConfigProvider(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyKey() {
        return propertyKey;
    }
}
