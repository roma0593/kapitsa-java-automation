package com.coherent.training.selenium.kapitsa.web.utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonParser{
    private final Gson gson;
    private BufferedReader reader;

    public JsonParser() {
        gson = new Gson();
    }

    @SneakyThrows
    public Credentials[] getCredArrayFromJson(File file) {
        reader = new BufferedReader(new FileReader(file));
        String credentials = reader.lines().collect(Collectors.joining());

        return gson.fromJson(credentials, Credentials[].class);
    }

    @SneakyThrows
    public List<Map<String, String>> getOnlinerDataFromJson(File file){
        reader = new BufferedReader(new FileReader(file));
        String onlinerData = reader.lines().collect(Collectors.joining());

        Type onlinerDataMapType = new TypeToken<List<Map<String, String>>>() {}.getType();
        return gson.fromJson(onlinerData, onlinerDataMapType);
    }

}
