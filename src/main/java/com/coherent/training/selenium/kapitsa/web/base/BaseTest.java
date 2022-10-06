package com.coherent.training.selenium.kapitsa.web.base;

import com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.AlertsPage;
import com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.DownloadProgressPage;
import com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.DropdownPage;
import com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.DynamicDataLoadingPage;
import com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.tablepage.TableSortSearchPage;
import com.coherent.training.selenium.kapitsa.web.pages.yandex.LoginPage;
import com.coherent.training.selenium.kapitsa.web.pages.yandex.MailBoxPage;
import com.coherent.training.selenium.kapitsa.web.pages.yandex.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected MailBoxPage mailBoxPage;
    protected DropdownPage dropdownPage;
    protected AlertsPage alertsPage;
    protected DownloadProgressPage downloadProgressPage;
    protected DynamicDataLoadingPage dynamicDataLoadingPage;
    protected TableSortSearchPage tableSortSearchPage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String browser, Method method){
        BrowserDriverFactory factory = new BrowserDriverFactory(browser);
        driver = factory.createDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
