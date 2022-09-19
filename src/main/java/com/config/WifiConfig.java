package com.config;

import com.api.ApiConnector;
import com.api.WifiApiConnector;
import com.api.detail.ApiDetail;
import com.api.detail.WifiApiDetail;
import com.api.parser.ApiParser;
import com.api.parser.WifiApiJsonParser;
import com.dao.history.HistoryDao;
import com.dao.history.MariaDbHistoryDao;
import com.dao.wifi.MariaDbWifiDao;
import com.dao.wifi.WifiDao;
import com.server.WifiApiServer;
import com.service.history.WifiHistoryService;
import com.service.wifi.NearWifiService;
import com.service.wifi.WifiService;

public class WifiConfig {

    public static WifiApiServer wifiApiServer() {
        return WifiApiServer.getInstance();
    }

    public static ApiConnector wifiApiConnector() {
        return WifiApiConnector.getInstance();
    }

    public static ApiDetail wifiApiDetail() {
        return WifiApiDetail.getInstance();
    }

    public static ApiParser apiJsonParser() {
        return WifiApiJsonParser.getInstance();
    }

    public static WifiDao wifiDao() {
        return MariaDbWifiDao.getInstance();
    }

    public static HistoryDao historyDao() { return MariaDbHistoryDao.getInstance();}

    public static WifiService wifiService() {
        return NearWifiService.getInstance();
    }

    public static WifiHistoryService wifiHistoryService() {
        return WifiHistoryService.getInstance();
    }



    private WifiConfig() {
    }
}
