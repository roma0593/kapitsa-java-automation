package com.coherent.training.selenium.kapitsa.web.base;

import com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.*;
import com.coherent.training.selenium.kapitsa.web.pages.seleniumeasy.tablepage.TableSortSearchPage;
import com.coherent.training.selenium.kapitsa.web.pages.yandex.LoginPage;
import com.coherent.training.selenium.kapitsa.web.pages.yandex.MailBoxPage;
import com.coherent.training.selenium.kapitsa.web.pages.yandex.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
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
    public void setUp(String browser){
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
