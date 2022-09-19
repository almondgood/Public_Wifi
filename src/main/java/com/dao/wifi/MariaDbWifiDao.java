package com.dao.wifi;

import com.dao.JdbcConnector;
import com.domain.Wifi;
import com.log.Logging;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MariaDbWifiDao implements WifiDao {

    private static final MariaDbWifiDao instance = new MariaDbWifiDao();

    @Override
    public List<Wifi> selectWifiList() {
        List<Wifi> wifiList = new ArrayList<>();

        JdbcConnector jdbcConnector = JdbcConnector.builder()
                .select("*", "seoul_wifi_location")
                .build();

        try {
            ResultSet resultSet = jdbcConnector.executeQuery();
            while (resultSet.next()) {
                wifiList.add(createWifi(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        jdbcConnector.close();

        return wifiList;
    }

    // TODO Connector 의존 분리... 클래스 가져오면 될 듯 한데 너무 어려울 듯
    @Override
    public void insertWifiList(List<Wifi> wifiList) {
        JdbcConnector jdbcConnector = JdbcConnector.builder()
                .executeDeleteAll("seoul_wifi_location")
                .insert("seoul_wifi_location", "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
                .build();

        for (Wifi wifi : wifiList) {
            jdbcConnector.addBatch(wifi);
        }

        jdbcConnector.setAutoCommit(false);
        jdbcConnector.executeBatch();
        jdbcConnector.setAutoCommit(true);

        jdbcConnector.close();
    }

    private Wifi createWifi(ResultSet resultSet) {
        Wifi wifi = null;
        try {
            wifi = Wifi.of(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getString(11),
                    resultSet.getString(12),
                    resultSet.getString(13),
                    resultSet.getDouble(14),
                    resultSet.getDouble(15),
                    resultSet.getString(16)
            );
        } catch (SQLException e) {
            Logging.currentMethodError(e);
        }

        if (wifi == null) {
            Logging.error("wifi is null");
        }

        return wifi;
    }

    private MariaDbWifiDao() {
    }

    public static MariaDbWifiDao getInstance() {
        return instance;
    }
}
