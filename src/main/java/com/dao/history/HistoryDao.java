package com.dao.history;

import com.domain.History;

import java.util.List;

public interface HistoryDao {
    List<History> selectHistoryList();
    void insertHistory(History history);
    void deleteHistory(int id);
}
