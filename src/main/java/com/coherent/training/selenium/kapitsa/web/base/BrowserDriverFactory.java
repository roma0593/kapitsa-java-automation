package com.coherent.training.selenium.kapitsa.web.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final String browser;
    private static final String CHROME_DRIVER = "src/test/resources/drivers/chromedriver.exe";
    private static final String FIREFOX_DRIVER = "src/test/resources/drivers/geckodriver.exe";
    private static final String EDGE_DRIVER = "src/test/resources/drivers/msedgedriver.exe";


    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public WebDriver createDriver(){
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
                driver.set(new ChromeDriver());
            break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER);
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", EDGE_DRIVER);
                driver.set(new EdgeDriver());
                break;
            default:
                System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
                driver.set(new ChromeDriver());
                break;
        }

        return driver.get();
    }
}
