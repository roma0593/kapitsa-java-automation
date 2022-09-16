package com.coherent.training.selenium.kapitsa.web.base;

import com.coherent.training.selenium.kapitsa.web.pages.LoginPage;
import com.coherent.training.selenium.kapitsa.web.pages.MailBoxPage;
import com.coherent.training.selenium.kapitsa.web.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected MailBoxPage mailBoxPage;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String browser){
        BrowserDriverFactory factory = new BrowserDriverFactory(browser);
        driver = factory.createDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
