package com.coherent.training.selenium.kapitsa.web.utils;

import com.google.gson.Gson;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.stream.Collectors;

public class JsonParser{
    private final Gson gson;

    public JsonParser() {
        gson = new Gson();
    }

    @SneakyThrows
    public Credentials[] getArrayReadFromJson(File file) {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String credentials = reader.lines().collect(Collectors.joining());

        return gson.fromJson(credentials, Credentials[].class);
    }
}
