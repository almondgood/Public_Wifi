package com.server;

import com.api.ApiConnector;
import com.api.parser.ApiParser;
import com.config.WifiConfig;
import com.dao.wifi.WifiDao;
import com.domain.Wifi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WifiApiServer {

    private static final WifiApiServer instance = new WifiApiServer();
    private final ApiConnector apiConnector;
    private final ApiParser apiParser;
    private final WifiDao wifiDao;


    /**
     * @return 로드된 와이파이 데이터 개수를 반환합니다.
     */
    // 트랜잭션 필요
    public int run() {
        List<Wifi> wifiList = new ArrayList<>();

        // TODO 마지막 페이지 처리
        int page = 1;
        while (isPageEnd(page)) {
            try {
                wifiList.addAll(
                        apiParser.parsing(apiConnector.loadApiData(page)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            page++;
        }

        wifiDao.insertWifiList(wifiList);

        return wifiList.size();
    }

    private boolean isPageEnd(int page) {
        return 1 + (1000 * (page - 1)) < apiParser.getListTotalCount();
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
