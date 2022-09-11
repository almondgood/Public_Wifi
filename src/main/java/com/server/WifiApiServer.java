package com.server;

import com.api.ApiConnector;
import com.api.parser.ApiParser;
import com.config.WifiConfig;
import com.dao.wifi.WifiDao;
import com.domain.Wifi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WifiApiServer {

    private ApiConnector apiConnector;
    private ApiParser apiParser;
    private WifiDao wifiDao;
    public WifiApiServer() {
        WifiConfig wifiConfig = new WifiConfig();

        apiConnector = wifiConfig.wifiApiConnector();
        apiParser = wifiConfig.apiJsonParser();
        wifiDao = wifiConfig.wifiDao();
    }

    public void run() {
        List<Wifi> wifiList = new ArrayList<>();


        // TODO 마지막 페이지 처리
        int startIndex = 1;
        int endIndex = 1000;
        try {
            while (!apiParser.isPageEnd(startIndex)) {
                wifiList.addAll(Arrays.asList(apiParser.parsing(
                        apiConnector.loadApiData(startIndex, endIndex))));

                startIndex = apiConnector.increaseIndex(startIndex);
                endIndex = apiConnector.increaseIndex(endIndex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        wifiDao.insertWifiList(wifiList);
    }
}
