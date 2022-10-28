package com.coherent.training.selenium.kapitsa.web.base;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static com.coherent.training.selenium.kapitsa.web.providers.ConfigFileReader.getInstance;

public class BrowserDriverFactory {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final String browser;
    private static final String CHROME_DRIVER = getInstance().getDriverPath("chrome");
    private static final String FIREFOX_DRIVER = getInstance().getDriverPath("firefox");
    private static final String EDGE_DRIVER = getInstance().getDriverPath("edge");
    private static final String HUB_URL = getInstance().getHubURL();
    private static final String SAUCE_USERNAME = getInstance().getSauceUsername();
    private static final String SAUCE_ACCESS_KEY = getInstance().getSauceAccessKey();
    private static final String DOWNLOAD_FOLDER = "downloadFiles";
    private static final String PROJECT_DIR = getInstance().getProjectDir();
    private Map<String, Object> prefs;
    private final String browserVersion;
    private final String platformName;
    private final Method method;
    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private EdgeOptions edgeOptions;
    private RemoteWebDriver remoteWebDriver;


    public BrowserDriverFactory(String browser, String browserVersion, String platformName, Method method) {
        this.browser = browser.toLowerCase();
        this.browserVersion = browserVersion;
        this.platformName = platformName;
        this.method = method;
    }

    @SneakyThrows
    public WebDriver createDriver(){
        Map<String, Object> sauceOptions = setSauceOptions();

        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
                setChromeDownloadOptions();
                setBrowserVersionAndPlatform();
                chromeOptions.setCapability("sauce:options", sauceOptions);

                remoteWebDriver = new RemoteWebDriver(new URL(HUB_URL), chromeOptions);
                driver.set(remoteWebDriver);
            break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER);
                setFirefoxDownloadOptions();
                setBrowserVersionAndPlatform();
                firefoxOptions.setCapability("sauce:options", sauceOptions);

                remoteWebDriver = new RemoteWebDriver(new URL(HUB_URL), firefoxOptions);
                driver.set(remoteWebDriver);
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", EDGE_DRIVER);
                setEdgeOptions();
                setBrowserVersionAndPlatform();
                edgeOptions.setCapability("sauce:options", sauceOptions);

                remoteWebDriver = new RemoteWebDriver(new URL(HUB_URL), edgeOptions);
                driver.set(remoteWebDriver);
                break;
            default:
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
                setChromeDownloadOptions();
                setBrowserVersionAndPlatform();
                chromeOptions.setCapability("sauce:options", sauceOptions);

                remoteWebDriver = new RemoteWebDriver(new URL(HUB_URL), chromeOptions);
                driver.set(remoteWebDriver);
                break;
        }

        return driver.get();
    }

    private void setChromeDownloadOptions(){
        prefs = new HashMap<>();
        prefs.put("download.default_directory", PROJECT_DIR + File.separator + DOWNLOAD_FOLDER + File.separator + "chrome");
        chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        setBrowserVersionAndPlatform();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    }

    private void setFirefoxDownloadOptions(){
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", PROJECT_DIR + File.separator + DOWNLOAD_FOLDER + File.separator + "firefox");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip, application/x-zip, application/x-zip-compressed");
        firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
    }

    private void setEdgeOptions(){
        prefs = new HashMap<>();
        prefs.put("download.default_directory", PROJECT_DIR + File.separator + "downloadFiles" + File.separator + "edge");
        edgeOptions = new EdgeOptions();
        edgeOptions.setExperimentalOption("prefs", prefs);
    }

    private void setBrowserVersionAndPlatform(){
        switch (browser) {
            case "chrome":
                chromeOptions.setBrowserVersion(browserVersion)
                    .setPlatformName(platformName);
            break;
            case "firefox":
                firefoxOptions.setBrowserVersion(browserVersion)
                        .setPlatformName(platformName);
            break;
            case "edge":
                edgeOptions.setBrowserVersion(browserVersion)
                        .setPlatformName(platformName);
            break;
        }
    }

    private Map<String, Object> setSauceOptions(){
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", System.getenv(SAUCE_USERNAME));
        sauceOptions.put("access_key", System.getenv(SAUCE_ACCESS_KEY));
        sauceOptions.put("name", method.getName());

        return sauceOptions;
    }

    public void setTestStatusInSauceReport(String status){
        remoteWebDriver.executeScript("sauce:job-result=" + status);
    }
}
