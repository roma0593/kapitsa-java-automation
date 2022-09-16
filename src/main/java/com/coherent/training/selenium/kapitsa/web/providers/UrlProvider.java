package com.coherent.training.selenium.kapitsa.web.providers;

public enum UrlProvider {
    YANDEX_MAIL("https://mail.yandex.com/");
    private final String url;

    UrlProvider(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
