package com.api;

import com.config.WifiConfig;
import org.junit.jupiter.api.Test;

class WifiApiConnectorTest {

    @Test
    void loadApiData1To1000() {
        ApiConnector apiConnector = WifiConfig.wifiApiConnector();

    }

    @Test
    void loadApiAllOfData() {
        ApiConnector apiConnector = WifiConfig.wifiApiConnector();

    }
}