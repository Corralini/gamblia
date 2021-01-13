package com.gamblia.dao.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionManager {
    private static final String CLASS_NAME = "classname";
    private static final String URL = "url";
    private static final String USER = "user";
    private static final String PSSWD = "password";
    private static final Logger logger = LogManager.getLogger(ConnectionManager.class.getName());
    private static final ResourceBundle configuration = ResourceBundle.getBundle("dbconfig.properties");
    private static ComboPooledDataSource dataSource = null;

    static {
        try {
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(configuration.getString(CLASS_NAME));
            dataSource.setJdbcUrl(configuration.getString(URL));
            dataSource.setUser(configuration.getString(USER));
            dataSource.setPassword(configuration.getString(PSSWD));
        } catch (Exception e) {
            logger.fatal(e.getMessage(), e);
        }
    }

    private ConnectionManager() {
    }

    public final static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
