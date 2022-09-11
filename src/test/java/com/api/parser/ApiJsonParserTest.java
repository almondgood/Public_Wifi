package com.api.parser;

import com.api.ApiConnector;
import com.config.WifiConfig;
import com.domain.Wifi;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ApiJsonParserTest {
    WifiConfig wifiConfig;

    @Test
    void parsing() {
        wifiConfig = new WifiConfig();
        ApiConnector apiConnector = wifiConfig.wifiApiConnector();
        ApiParser apiParser = wifiConfig.apiJsonParser();

        List<Wifi> wifiList = new ArrayList<>();

        try {
            wifiList.addAll(Arrays.asList(apiParser.parsing(apiConnector.loadApiData(1, 1000))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(wifiList);

    }

    @Test
    void loadApiAllOfData() {
        wifiConfig = new WifiConfig();
        ApiConnector apiConnector = wifiConfig.wifiApiConnector();
        ApiParser apiParser = wifiConfig.apiJsonParser();

        List<Wifi> wifiList = new ArrayList<>();

        int startIndex = 1;
        int endIndex = 1000;
        try {
            while (!apiParser.isPageEnd(startIndex)) {
                wifiList.addAll(Arrays.asList(apiParser.parsing(apiConnector.loadApiData(startIndex, endIndex))));

                startIndex = apiConnector.increaseIndex(startIndex);
                endIndex = apiConnector.increaseIndex(endIndex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(wifiList.size());
    }
}