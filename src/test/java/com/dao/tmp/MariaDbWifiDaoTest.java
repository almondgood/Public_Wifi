package com.dao.tmp;

import com.config.WifiConfig;
import com.dao.wifi.WifiDao;
import com.domain.Wifi;
import com.server.WifiApiServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MariaDbWifiDaoTest {

    WifiApiServer wifiApiServer;
    WifiDao wifiDao;
    List<Wifi> wifiList;

    @BeforeEach
    void beforeEach() {
        WifiConfig wifiConfig = new WifiConfig();
        wifiApiServer = new WifiApiServer();
        wifiDao = wifiConfig.wifiDao();

    }

    @Test
    void insertWifiList() {
        wifiList = new ArrayList<>();


        wifiDao.insertWifiList(wifiList);
    }

    @Test
    void selectWifiList() {
        List<Wifi> wifiList = wifiDao.selectWifiList();

        System.out.println(wifiList.size());
    }
}