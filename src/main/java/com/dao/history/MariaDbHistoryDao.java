package com.dao.history;

import com.dao.JdbcConnector;
import com.domain.History;
import com.log.Logging;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MariaDbHistoryDao implements HistoryDao {

    private static final MariaDbHistoryDao instance = new MariaDbHistoryDao();
    @Override
    public List<History> selectHistoryList() {
        JdbcConnector jdbcConnector = JdbcConnector.builder()
                .select("*", "my_location_history")
                .build();

        List<History> historyList = new ArrayList<>();

        try {
            ResultSet resultSet = jdbcConnector.executeQuery();

            while (resultSet.next()) {
                historyList.add(createHistory(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            jdbcConnector.close();
        }

        return historyList;
    }

    @Override
    public void insertHistory(History history) {
        JdbcConnector.builder()
                .insert("my_location_history(lat, lnt, history_datetime)", "values(?, ?, ?)")
                .setParam(1, history.getLat())
                .setParam(2, history.getLnt())
                .setParam(3, history.getHistoryDateTime())
                .executeAndClose();
    }

    @Override
    public void deleteHistory(int id) {
        JdbcConnector.builder()
                .delete("my_location_history", "id = ?")
                .setParam(1, id)
                .executeAndClose();
    }

    private History createHistory(ResultSet resultSet) {
        History history = null;
        try {
            history = History.of(
                    resultSet.getInt(1),
                    resultSet.getDouble(2),
                    resultSet.getDouble(3),
                    resultSet.getString(4)
            );
        } catch (SQLException e) {
            Logging.currentMethodError(e);
        }

        if (history == null) {
            Logging.error("history is null");
        }

        return history;
    }


    private MariaDbHistoryDao() {
    }

    public static MariaDbHistoryDao getInstance() {
        return instance;
    }
}
