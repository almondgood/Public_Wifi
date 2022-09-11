package com.dao.wifi;

import com.domain.Wifi;

import java.util.List;

public interface WifiDao {
    List<Wifi> selectWifiList();
    void insertWifiList(List<Wifi> list);
}
