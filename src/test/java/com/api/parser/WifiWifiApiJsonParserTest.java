package com.api.parser;

import com.config.WifiConfig;
import com.domain.Wifi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class WifiWifiApiJsonParserTest {

    ApiParser apiParser;

    @BeforeEach
    void beforeEach() {
        apiParser = WifiConfig.apiJsonParser();
    }


    @Test
    @DisplayName("json 파싱 성공")
    void jsonParsingSuccess() throws IOException, URISyntaxException {
        //given
        URL resource = this.getClass().getClassLoader().getResource("test.json");
        List<String> strings = Files.readAllLines(Paths.get(resource.toURI()));
        StringBuilder file = new StringBuilder();

        //when
        strings.forEach(file::append);

        //then
        List<Wifi> wifiList = apiParser.parsing(file.toString());
        Assertions.assertEquals("ARI00001", wifiList.get(0).getMgrNo());
        Assertions.assertEquals("ARI00002", wifiList.get(1).getMgrNo());
    }

}