package com.api.parser;

import com.domain.Wifi;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Arrays;
import java.util.List;


public class WifiApiJsonParser implements ApiParser {

    private static final WifiApiJsonParser instance = new WifiApiJsonParser();

    private int listTotalCount = Integer.MAX_VALUE;

    @Override
    public List<Wifi> parsing(String json) {
        return Arrays.asList(getWifiArray(json));
    }

    @Override
    public int getListTotalCount() {
        return listTotalCount;
    }

    private Wifi[] getWifiArray(String json) {
        return new Gson().fromJson(getWifiJsonArray(json), Wifi[].class);
    }

    private String getWifiJsonArray(String file) {
        JsonObject tbPublicWifiInfo = JsonParser.parseString(file)
                .getAsJsonObject()
                .getAsJsonObject("TbPublicWifiInfo");

        if (listTotalCount == Integer.MAX_VALUE) {
            listTotalCount = tbPublicWifiInfo.get("list_total_count")
                    .getAsInt();
        }

        return tbPublicWifiInfo.getAsJsonArray("row").toString();
    }


    private WifiApiJsonParser() {}

    public static WifiApiJsonParser getInstance() {
        return instance;
    }
}
