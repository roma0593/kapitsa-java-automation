package com.coherent.training.selenium.kapitsa.web.herokuapp;

import com.coherent.training.selenium.kapitsa.web.base.BaseTest;
import com.coherent.training.selenium.kapitsa.web.pages.herokuapp.DownloadPage;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import static com.coherent.training.selenium.kapitsa.web.providers.UrlProvider.*;
import static com.coherent.training.selenium.kapitsa.web.utils.RequestUtil.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class DownloadTest extends BaseTest {
    @SneakyThrows
    @Test
    public void downloadFile(){
        driver.get(HEROKUAPP_DOWNLOAD.getUrl());

        downloadPage = new DownloadPage(driver);

        String link = downloadPage.getJpegHrefAttr();

        assertThat(getContentType(link), is("application/octet-stream"));
        assertThat(getContentLength(link), is(not(0)));
    }
}
