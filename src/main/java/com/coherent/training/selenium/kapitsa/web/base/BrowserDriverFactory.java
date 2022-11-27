package com.coherent.training.selenium.kapitsa.web.base;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
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

public class BrowserDriverFactory extends TestUtilities {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final String browser;
    private static final String PROFILE = getInstance().getProfile();
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
    public WebDriver createDriver() {
        setDownloadOptions();

        setDriver();

        return driver.get();
    }

    private void setChromeDownloadOptions() {
        prefs = new HashMap<>();
        prefs.put("download.default_directory", PROJECT_DIR + File.separator + DOWNLOAD_FOLDER + File.separator + "chrome");
        chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        setBrowserVersionAndPlatform();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    }

    private void setFirefoxDownloadOptions() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", PROJECT_DIR + File.separator + DOWNLOAD_FOLDER + File.separator + "firefox");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip, application/x-zip, application/x-zip-compressed");
        firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
    }

    private void setEdgeOptions() {
        prefs = new HashMap<>();
        prefs.put("download.default_directory", PROJECT_DIR + File.separator + "downloadFiles" + File.separator + "edge");
        edgeOptions = new EdgeOptions();
        edgeOptions.setExperimentalOption("prefs", prefs);
    }

    private void setBrowserVersionAndPlatform() {
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
            default:
                chromeOptions.setBrowserVersion(browserVersion)
                        .setPlatformName(platformName);
        }
    }

    private void setDownloadOptions() {
        switch (browser) {
            case "chrome":
                setChromeDownloadOptions();
                break;
            case "firefox":
                setFirefoxDownloadOptions();
                break;
            case "edge":
                setEdgeOptions();
                break;
            default:
                setChromeDownloadOptions();
        }
    }

    private Map<String, Object> setSauceOptions() {
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", System.getenv(SAUCE_USERNAME));
        sauceOptions.put("access_key", System.getenv(SAUCE_ACCESS_KEY));
        sauceOptions.put("method", method.getName());

        return sauceOptions;
    }

    private void setSauceCapability() {
        Map<String, Object> sauceOptions = setSauceOptions();

        switch (browser) {
            case "chrome":
                chromeOptions.setCapability("sauce:options", sauceOptions);
                break;
            case "firefox":
                firefoxOptions.setCapability("sauce:options", sauceOptions);
                break;
            case "edge":
                edgeOptions.setCapability("sauce:options", sauceOptions);
                break;
            default:
                chromeOptions.setCapability("sauce:options", sauceOptions);
        }
    }

    public void setTestStatusInSauceReport(String status) {
        remoteWebDriver.executeScript("sauce:job-result=" + status);
    }

    @SneakyThrows
    private void setDriver() {
        String hub = (PROFILE.equals("LOCAL")) ? getInstance().getHubURL(browser)
                : getInstance().getHubURL();

        if (PROFILE.equals("LOCAL")) {
            switch (browser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", hub);
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
                case "firefox":
                    System.setProperty("webdriver.gecko.driver", hub);
                    driver.set(new FirefoxDriver(firefoxOptions));
                    break;
                case "edge":
                    System.setProperty("webdriver.edge.driver", hub);
                    driver.set(new EdgeDriver(edgeOptions));
                    break;
                default:
                    System.setProperty("webdriver.chrome.driver", hub);
                    driver.set(new ChromeDriver(chromeOptions));
                    break;
            }
        } else {
            setBrowserVersionAndPlatform();
            setSauceCapability();

            switch (browser) {
                case "chrome":
                    remoteWebDriver = new RemoteWebDriver(new URL(hub), chromeOptions);
                    driver.set(remoteWebDriver);
                    break;
                case "firefox":
                    remoteWebDriver = new RemoteWebDriver(new URL(hub), firefoxOptions);
                    driver.set(remoteWebDriver);
                    break;
                case "edge":
                    remoteWebDriver = new RemoteWebDriver(new URL(hub), edgeOptions);
                    driver.set(remoteWebDriver);
                    break;
                default:
                    remoteWebDriver = new RemoteWebDriver(new URL(hub), chromeOptions);
                    driver.set(remoteWebDriver);
                    break;
            }
        }
    }
}
