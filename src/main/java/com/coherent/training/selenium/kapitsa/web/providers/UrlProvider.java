package com.coherent.training.selenium.kapitsa.web.providers;

public enum UrlProvider {
    YANDEX_MAIL("https://mail.yandex.com/"),
    SELENIUM_EASY_DROPDOWN("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html"),
    SELENIUM_ALERT("https://demo.seleniumeasy.com/javascript-alert-box-demo.html"),
    SELENIUM_DOWNLOAD_PROGRESS("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html"),
    SELENIUM_DYNAMIC_DATA("https://demo.seleniumeasy.com/dynamic-data-loading-demo.html"),
    SELENIUM_TABLE_SORT_SEARCH("https://demo.seleniumeasy.com/table-sort-search-demo.html"),
    HEROKUAPP_DOWNLOAD("http://the-internet.herokuapp.com/download"),
    CATALOG_ONLINER("https://catalog.onliner.by/");

    private final String url;

    UrlProvider(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
