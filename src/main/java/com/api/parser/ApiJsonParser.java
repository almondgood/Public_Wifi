package com.api.parser;

import com.domain.Wifi;
import com.google.gson.*;


public class ApiJsonParser implements ApiParser {

    private int listMaxCount = Integer.MAX_VALUE;

    @Override
    public Wifi[] parsing(String json) {
        return getWifiArray(json);
    }

    @Override
    public boolean isPageEnd(int endIndex) {
        return endIndex >= listMaxCount;
    }

    private Wifi[] getWifiArray(String json) {
        JsonElement wifiJsonArray = getWifiJsonArray(json);

        Gson gson = new Gson();
        return gson.fromJson(wifiJsonArray.toString(), Wifi[].class);
    }

    private JsonArray getWifiJsonArray(String file) {
        JsonObject tbPublicWifiInfo = JsonParser.parseString(file)
                .getAsJsonObject()
                .getAsJsonObject("TbPublicWifiInfo");

        if (listMaxCount == Integer.MAX_VALUE) {
            listMaxCount = tbPublicWifiInfo.get("list_total_count")
                    .getAsInt();
        }

        return tbPublicWifiInfo.getAsJsonArray("row");
    }
}
