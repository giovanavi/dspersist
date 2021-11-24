package dspersist.dao;

import dspersist.config.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(AppConfig.PROP.getProperty("jdbc.url"),
                AppConfig.PROP.getProperty("jdbc.usuario"), AppConfig.PROP.getProperty("jdbc.senha"));
    }
}
