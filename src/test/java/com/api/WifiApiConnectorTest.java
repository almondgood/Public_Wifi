package com.api;

import com.config.WifiConfig;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WifiApiConnectorTest {

    WifiConfig wifiConfig;

    @Test
    void loadApiData1To1000() {
        wifiConfig = new WifiConfig();
        ApiConnector apiConnector = wifiConfig.wifiApiConnector();

        try {
            System.out.println(apiConnector.loadApiData(1, 1000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void loadApiAllOfData() {
        wifiConfig = new WifiConfig();
        ApiConnector apiConnector = wifiConfig.wifiApiConnector();

        try {
            System.out.println(apiConnector.loadApiData(1, 1000));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}