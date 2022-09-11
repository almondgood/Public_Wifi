package com.service.history;

import com.config.WifiConfig;
import com.dao.history.HistoryDao;
import com.domain.History;

import java.util.List;

public class WifiHistoryService implements HistoryService{

    WifiConfig wifiConfig;
    HistoryDao historyDao;

    public WifiHistoryService() {
        wifiConfig = new WifiConfig();
        historyDao = wifiConfig.historyDao();
    }

    @Override
    public List<History> loadHistoryList() {
        return historyDao.selectHistoryList();
    }

    @Override
    public void insertHistory(double lat, double lnt, String DateTime) {
        historyDao.insertHistory(History.of(lat, lnt, DateTime));
    }
}
