package fr.esigelec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.esigelec.config.DataBaseConfig;

public class ConnectionDAO {
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
        	DataBaseConfig.getUrl(),
            DataBaseConfig.getUsername(),
            DataBaseConfig.getPassword()
        );
    }
}
