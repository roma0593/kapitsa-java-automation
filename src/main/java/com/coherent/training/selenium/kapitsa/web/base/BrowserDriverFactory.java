package com.coherent.training.selenium.kapitsa.web.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserDriverFactory {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private final String browser;

    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public WebDriver createDriver(){
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\rkapitsa\\IdeaProjects\\kapitsa-java-automation\\src\\main\\resources\\drivers\\chromedriver.exe");
                driver.set(new ChromeDriver());
            break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\rkapitsa\\IdeaProjects\\kapitsa-java-automation\\src\\main\\resources\\drivers\\geckodriver.exe");
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "C:\\Users\\rkapitsa\\IdeaProjects\\kapitsa-java-automation\\src\\main\\resources\\drivers\\msedgedriver.exe");
                driver.set(new EdgeDriver());
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\rkapitsa\\IdeaProjects\\kapitsa-java-automation\\src\\main\\resources\\drivers\\chromedriver.exe");
                driver.set(new ChromeDriver());
                break;
        }

        return driver.get();
    }
}
