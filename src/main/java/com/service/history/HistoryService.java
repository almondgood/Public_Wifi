package com.service.history;

import com.domain.History;

import java.util.List;

public interface HistoryService {
    List<History> loadHistoryList();
    void insertHistory(double lat, double lnt, String DateTime);
}
