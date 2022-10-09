package com.coherent.training.selenium.kapitsa.web.utils;

import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class RequestUtil {
    @SneakyThrows
    private static HttpHeaders getHeadersFromHeadRequest(String link){
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(link))
                .method("HEAD", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

        return response.headers();
    }

    public static String getContentType(String link){
        HttpHeaders headers = getHeadersFromHeadRequest(link);
        Optional<String> headerType = headers.firstValue("Content-Type");

        return headerType.orElse("content type is empty");
    }

    public static int getContentLength(String link){
        HttpHeaders headers = getHeadersFromHeadRequest(link);
        Optional<String> contentLength = headers.firstValue("Content-Length");


        return Integer.parseInt(contentLength.orElse("content length is empty"));
    }
}
