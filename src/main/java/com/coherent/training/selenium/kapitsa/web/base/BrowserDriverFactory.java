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
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserDriverFactory {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final String browser;
    private static final String CHROME_DRIVER = "src/test/resources/drivers/chromedriver.exe";
    private static final String FIREFOX_DRIVER = "src/test/resources/drivers/geckodriver.exe";
    private static final String EDGE_DRIVER = "src/test/resources/drivers/msedgedriver.exe";
    private static final String DOWNLOAD_FOLDER = "downloadFiles";
    private static final String PROJECT_DIR = System.getProperty("user.dir");
    private static final String HUB_URL = "http://172.29.192.1:4444";
    private Map<String, Object> prefs;


    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    @SneakyThrows
    public WebDriver createDriver(){
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
                ChromeOptions chromeOptions = setChromeDownloadOptions();
                driver.set(new RemoteWebDriver(new URL(HUB_URL), chromeOptions));
            break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER);
                FirefoxOptions firefoxOptions = setFirefoxDownloadOptions();
                driver.set(new RemoteWebDriver(new URL(HUB_URL), firefoxOptions));
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", EDGE_DRIVER);
                EdgeOptions edgeOptions = setEdgeDownloadOptions();
                driver.set(new RemoteWebDriver(new URL(HUB_URL), edgeOptions));
                break;
            default:
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
                ChromeOptions defChromeOptions = setChromeDownloadOptions();
                driver.set(new RemoteWebDriver(new URL(HUB_URL), defChromeOptions));
                break;
        }

        return driver.get();
    }

    private ChromeOptions setChromeDownloadOptions(){
        prefs = new HashMap<>();
        prefs.put("download.default_directory", PROJECT_DIR + File.separator + DOWNLOAD_FOLDER + File.separator + "chrome");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return chromeOptions;
    }

    private FirefoxOptions setFirefoxDownloadOptions(){
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", PROJECT_DIR + File.separator + DOWNLOAD_FOLDER + File.separator + "firefox");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip, application/x-zip, application/x-zip-compressed");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);

        return firefoxOptions;
    }

    private EdgeOptions setEdgeDownloadOptions(){
        prefs = new HashMap<>();
        prefs.put("download.default_directory", PROJECT_DIR + File.separator + "downloadFiles" + File.separator + "edge");
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setExperimentalOption("prefs", prefs);

        return edgeOptions;
    }
}
