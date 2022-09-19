package com.server;

import com.api.ApiConnector;
import com.api.parser.ApiParser;
import com.config.WifiConfig;
import com.dao.wifi.WifiDao;
import com.domain.Wifi;

import java.util.ArrayList;
import java.util.List;

public class WifiApiServer {

    private static final WifiApiServer instance = new WifiApiServer();
    private final ApiConnector apiConnector;
    private final ApiParser apiParser;
    private final WifiDao wifiDao;



    // 트랜잭션 필요
    public void run() {
        List<Wifi> wifiList = new ArrayList<>();

        // TODO 마지막 페이지 처리
        int page = 1;
        while (true) {
//                List<Object> parsing = apiParser.parsing(
//                        apiConnector.loadApiData(page));

            page++;
        }

//        wifiDao.insertWifiList(wifiList);
    }


    private WifiApiServer() {
        apiConnector = WifiConfig.wifiApiConnector();
        apiParser = WifiConfig.apiJsonParser();
        wifiDao = WifiConfig.wifiDao();
    }

    public static WifiApiServer getInstance() {
        return instance;
    }
}
