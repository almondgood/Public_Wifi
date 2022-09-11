package com.config;

import com.api.ApiConnector;
import com.api.WifiApiConnector;
import com.api.detail.ApiDetail;
import com.api.detail.WifiApiDetail;
import com.api.parser.ApiJsonParser;
import com.api.parser.ApiParser;
import com.dao.history.HistoryDao;
import com.dao.history.MariaDbHistoryDao;
import com.dao.wifi.WifiDao;
import com.dao.wifi.MariaDbWifiDao;

public class WifiConfig {

    public ApiConnector wifiApiConnector() {
        return new WifiApiConnector(wifiApiDetail());
    }

    private ApiDetail wifiApiDetail() {
        return new WifiApiDetail();
    }

    public ApiParser apiJsonParser() {
        return new ApiJsonParser();
    }

    public WifiDao wifiDao() {
        return new MariaDbWifiDao();
    }

    public HistoryDao historyDao() { return new MariaDbHistoryDao();}
}
