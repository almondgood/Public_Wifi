package com.server;

import com.api.ApiConnector;
import com.api.parser.ApiParser;
import com.config.WifiConfig;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WifiApiServerTest {

    @Test
    void runApi() {
        WifiConfig wifiConfig = new WifiConfig();

        ApiConnector apiConnector = wifiConfig.wifiApiConnector();
        ApiParser apiParser = wifiConfig.apiJsonParser();

        try {
            System.out.println(Arrays.asList(apiParser.parsing(apiConnector.loadApiData(1, 1000))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}