package com.dao;

import com.domain.Wifi;
import com.log.Logging;

import java.sql.*;

// TODO 커넥션 풀
public class JdbcConnector {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    private JdbcConnector(Connection connection, PreparedStatement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    private JdbcConnector(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        this.connection = connection;
        this.statement = statement;
        this.resultSet = resultSet;
    }

    public static JdbcConnector.Builder builder() {
        return new JdbcConnector.Builder();
    }

    public ResultSet executeQuery() throws SQLException {
        return statement.executeQuery();
    }

    /////////// TODO 의존성 분리
    public void addBatch(Wifi wifi) {
        setWifiParam(wifi);

        try {
            statement.addBatch();
            statement.clearParameters();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeBatch() {
        try {
            statement.executeBatch();
            statement.clearBatch();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    ////////////////////////////

    public void setAutoCommit(boolean autoCommit) {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        closeResultSet();
        closeStatement();
        closeConnection();
    }


    // 디비 insert/select 컬럼 입력


    private void setWifiParam(Wifi wifi) {
        try {
            statement.setString(1, wifi.getMgrNo());
            statement.setString(2, wifi.getGu());
            statement.setString(3, wifi.getWifiName());
            statement.setString(4, wifi.getAdres1());
            statement.setString(5, wifi.getAdres2());
            statement.setString(6, wifi.getInstlFloor());
            statement.setString(7, wifi.getInstlTY());
            statement.setString(8, wifi.getInstlMBY());
            statement.setString(9, wifi.getSvc());
            statement.setString(10, wifi.getNetworkType());
            statement.setString(11, wifi.getInstlYear());
            statement.setString(12, wifi.getInOutDoor());
            statement.setString(13, wifi.getConnEnvironment());
            statement.setDouble(14, wifi.getLat());
            statement.setDouble(15, wifi.getLnt());
            statement.setString(16, wifi.getWorkDateTime());
        } catch (SQLException e) {
            Logging.currentMethodError(e);
        }
    }

    public JdbcConnector setParam(int columnIndex, String str) {
        try {
            statement.setString(columnIndex, str);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public JdbcConnector setParam(int columnIndex, int num) {
        try {
            statement.setInt(columnIndex, num);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public JdbcConnector setParam(int columnIndex, double num) {
        try {
            statement.setDouble(columnIndex, num);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    private void closeResultSet() {
        try {
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
            }
        } catch (SQLException e) {
            Logging.currentMethodError(e);
        }
    }

    private void closeStatement() {
        try {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // ============ Builder =============


    public static class Builder {
        private Connection connection = null;
        private PreparedStatement statement = null;
        private ResultSet resultSet = null;
        private final String driver = "org.mariadb.jdbc.Driver";
        private final String url = "jdbc:mariadb://localhost/public_wifi";
        private final String user = "wifi_user";
        private final String pass = "wifi1234";

        public Builder() {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, pass);
            } catch (SQLException e) {
                Logging.currentMethodError(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        public Builder executeDeleteAll(String table) {
            try {
                statement = connection.prepareStatement("TRUNCATE TABLE " + table + ";");
                resultSet = statement.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        public Builder writeQuery(String sql) {
            try {
                statement = connection.prepareStatement(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return this;
        }

        public Builder select(String column, String table){
            try {
                statement = connection.prepareStatement(selectQuery(column, table));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return this;
        }

        public Builder insert(String table, String values) {
            try {
                statement = connection.prepareStatement(insertQuery(table, values));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return this;
        }

        public Builder delete(String table, String condition) {
            try {
                statement = connection.prepareStatement(deleteQuery(table, condition));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return this;
        }

        public Builder setParam(int columnIndex, String str) {
            try {
                statement.setString(columnIndex, str);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        public Builder setParam(int columnIndex, int num) {
            try {
                statement.setInt(columnIndex, num);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return this;
        }

        public Builder setParam(int columnIndex, double num) {
            try {

                statement.setDouble(columnIndex, num);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return this;
        }


        /**
         * 결과 값을 반환받으려면 빌드 후 사용하십시오.
         */
        public void executeAndClose() {
            try {
                statement.executeQuery();
                close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public JdbcConnector build() {
            return new JdbcConnector(connection, statement);
        }


        private String selectQuery(String tableColumn, String table) {
            return "SELECT " +
                    tableColumn +
                    "FROM " +
                    table;
        }

        private String insertQuery(String table, String values) {
            return "INSERT INTO " +
                    table + " " +
                    values + ";";
        }

        private String deleteQuery(String table, String condition) {
            return "DELETE FROM " +
                    table + " " +
                    "WHERE " + condition + ";";
        }

        private void close() {
            closeResultSet();
            closeStatement();
            closeConnection();
        }

        private void closeResultSet() {
            try {
                if (resultSet != null && !resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                Logging.currentMethodError(e);
            }
        }

        private void closeStatement() {
            try {
                if (statement != null && !statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private void closeConnection() {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    // ============ Builder =============
}
