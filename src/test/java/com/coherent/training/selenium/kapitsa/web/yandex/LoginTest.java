package com.coherent.training.selenium.kapitsa.web.yandex;

import com.coherent.training.selenium.kapitsa.web.base.BaseTest;
import com.coherent.training.selenium.kapitsa.web.pages.yandex.MainPage;
import com.coherent.training.selenium.kapitsa.web.utils.DataUtilization;
import lombok.SneakyThrows;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.coherent.training.selenium.kapitsa.web.providers.UrlProvider.YANDEX_MAIL;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @SneakyThrows
    @Test(dataProviderClass = DataUtilization.class, dataProvider = "credProvider")
    public void login(String username, String pass) {
        driver.get(YANDEX_MAIL.getUrl());
        mainPage = new MainPage(driver);

        loginPage = mainPage.getLoginPage();

        Thread.sleep(3_000);
    /*  this is the worst type of explicit wait because
        program will always be stopped executing for the time
        specified in method parameter */

        mailBoxPage = loginPage.login(username, pass);

        assertEquals(username, mailBoxPage.getProfileNickname(), "Expected and actual nickname mismatch");
    }

    @Parameters({"username", "pass"})
    @Test
    public void logout(String username, String pass){
        driver.get(YANDEX_MAIL.getUrl());

        mainPage = new MainPage(driver);
        loginPage = mainPage.getLoginPage();
        mailBoxPage = loginPage.login(username, pass);
        mainPage = mailBoxPage.logout();

        assertEquals(mainPage.getPageTitle(), "Yandex Mail — reliable and easy to use email with spam protection",
                "Expected and actual page title mismatch");
    }
}
