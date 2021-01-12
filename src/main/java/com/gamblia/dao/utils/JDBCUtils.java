package com.gamblia.dao.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {

    private static final Logger logger = LogManager.getLogger(JDBCUtils.class.getName());

    private JDBCUtils() {
    }

    public static void closeResultSet(ResultSet resultSet) {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            }
        }

    }

    public static void closeStatement(Statement statement) {

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            }
        }

    }

    public static void closeConnection(Connection connection) {

        if (connection != null) {
            try {
                if (!connection.getAutoCommit()) {
                    throw new SQLException("Autocommit disabled. Commit or Rollback should be done explicitly.");
                }

                connection.close();
            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            }
        }
    }

    public static void closeConnection(Connection connection, boolean commitOrRollback) {
        if (connection != null) {
            try {
                if (commitOrRollback) {
                    connection.commit();
                } else {
                    connection.rollback();
                }
                connection.close();
            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            }

        }

    }


}
