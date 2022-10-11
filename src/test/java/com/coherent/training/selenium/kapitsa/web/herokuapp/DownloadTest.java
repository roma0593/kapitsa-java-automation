package com.coherent.training.selenium.kapitsa.web.herokuapp;

import com.coherent.training.selenium.kapitsa.web.base.BaseTest;
import com.coherent.training.selenium.kapitsa.web.pages.herokuapp.DownloadPage;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import static com.coherent.training.selenium.kapitsa.web.providers.UrlProvider.HEROKUAPP_DOWNLOAD;
import static com.coherent.training.selenium.kapitsa.web.utils.RequestUtil.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class DownloadTest extends BaseTest {
    @SneakyThrows
    @Test
    public void downloadFile(){
        driver.get(HEROKUAPP_DOWNLOAD.getUrl());

        downloadPage = new DownloadPage(driver);

        String link = downloadPage.getJpegHrefAttr();

        assertEquals(getContentType(link), "application/octet-stream",
                "Expected and actual Content Type mismatch");
        assertNotEquals(getContentLength(link), 0, "Content length equals 0");
    }
}
