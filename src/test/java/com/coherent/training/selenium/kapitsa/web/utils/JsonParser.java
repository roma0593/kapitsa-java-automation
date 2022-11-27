package com.coherent.training.selenium.kapitsa.web.utils;

import com.coherent.training.selenium.kapitsa.web.utils.pojos.Credentials;
import com.coherent.training.selenium.kapitsa.web.utils.pojos.Filter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public JsonParser() {
        gson = new Gson();
    }

    @SneakyThrows
    public Credentials[] getCredArrayFromJson(File file) {
        String credentials = readFromJson(file);

        return gson.fromJson(credentials, Credentials[].class);
    }

    @SneakyThrows
    public Filter[] getFilterArrayFromJson(File file) {
        String filters = readFromJson(file);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);


        return mapper.readValue(filters, new TypeReference<>(){});
    }

    @SneakyThrows
    public List<Map<String, String>> getOnlinerDataFromJson(File file){
        String onlinerData = readFromJson(file);

        Type onlinerDataMapType = new TypeToken<List<Map<String, String>>>() {}.getType();
        return gson.fromJson(onlinerData, onlinerDataMapType);
    }

    @SneakyThrows
    private String readFromJson(File file){
        BufferedReader reader = new BufferedReader(new FileReader(file));

        return reader.lines().collect(Collectors.joining());
    }

}
