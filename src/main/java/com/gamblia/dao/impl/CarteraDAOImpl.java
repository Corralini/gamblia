package com.gamblia.dao.impl;

import com.gamblia.dao.spi.CarteraDAO;
import com.gamblia.dao.utils.DAOUtils;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Cartera;
import com.gamblia.model.criteria.CarteraCriteria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarteraDAOImpl implements CarteraDAO {
    private final Logger logger = LogManager.getLogger(CarteraDAOImpl.class.getName());
    private final String select = " SELECT ID, SALDO, TARJETA ";

    public CarteraDAOImpl() {
    }

    @Override
    public Cartera findById(Connection connection, Integer id) {
        if (logger.isDebugEnabled())
            logger.debug("id: {}", id);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null && id != null) {
            try {
                StringBuilder query = new StringBuilder(select).append(" FROM DIA WHERE ID = ?");
                if (logger.isDebugEnabled())
                    logger.debug(query.toString());

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                preparedStatement.setInt(i, id);
                resultSet = preparedStatement.executeQuery();

                Cartera cartera = null;
                if (resultSet.next()) {
                    cartera = loadNext(resultSet);
                } else {
                    if (logger.isDebugEnabled()) logger.debug("Cartera {} not found", id);
                }
                if (resultSet.next()) {
                    if (logger.isDebugEnabled()) logger.debug("Id {} duplicate", id);
                }

                return cartera;
            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }

        return null;
    }

    @Override
    public List<Cartera> findBy(Connection connection, CarteraCriteria carteraCriteria) {
        if (logger.isDebugEnabled()) logger.debug("criteria: {}", carteraCriteria);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder query;
        List<Cartera> carteras = new ArrayList<>();
        if (connection != null && carteraCriteria != null) {
            try {
                query = new StringBuilder(select).append(" FROM CARTERA ");

                boolean first = true;

                if (carteraCriteria.getId() != null) {
                    DAOUtils.addClause(query, true, " id = ? ");
                    first = false;
                }
                if (carteraCriteria.getSaldo() != null) {
                    DAOUtils.addClause(query, first, " saldo = ? ");
                    first = false;
                }
                if (carteraCriteria.getTarjeta() != null) {
                    DAOUtils.addClause(query, first, " tarjeta = ? ");
                }

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                if (carteraCriteria.getId() != null) preparedStatement.setInt(i++, carteraCriteria.getId());
                if (carteraCriteria.getSaldo() != null) preparedStatement.setDouble(i++, carteraCriteria.getSaldo());
                if (carteraCriteria.getTarjeta() != null) preparedStatement.setInt(i, carteraCriteria.getTarjeta());

                resultSet = preparedStatement.executeQuery();

                Cartera cartera;

                while (resultSet.next()) {
                    cartera = loadNext(resultSet);
                    carteras.add(cartera);
                }


            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return carteras;
    }

    @Override
    public List<Cartera> findAll(Connection connection) {
        if (logger.isDebugEnabled()) logger.debug("all");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Cartera> carteras = new ArrayList<>();
        if (connection != null) {
            try {

                StringBuilder queryString = new StringBuilder(select).append(" FROM CARTERA ");
                if (logger.isDebugEnabled()) logger.debug(queryString.toString());
                preparedStatement = connection.prepareStatement(queryString.toString());

                resultSet = preparedStatement.executeQuery();

                Cartera cartera;

                while (resultSet.next()) {
                    cartera = loadNext(resultSet);
                    carteras.add(cartera);
                }

            } catch (SQLException e) {
                logger.warn(e.getMessage(), e);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }

        return carteras;
    }

    @Override
    public Cartera update(Connection connection, Cartera cartera) {
        if (logger.isDebugEnabled()) logger.debug("Update cartera: {}", cartera);
        PreparedStatement preparedStatement = null;
        StringBuilder query;
        if (connection != null && cartera != null && cartera.getId() != null) {
            try {
                query = new StringBuilder(" UPDATE CARTERA ");

                boolean first = true;

                if (cartera.getSaldo() != null) {
                    DAOUtils.addUpdate(query, true, " saldo = ? ");
                    first = false;
                }
                if (cartera.getTarjeta() != null) {
                    DAOUtils.addUpdate(query, first, " tarjeta = ? ");
                }
                query.append(" WHERE ID = ?");
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;

                if (cartera.getSaldo() != null) preparedStatement.setDouble(i++, cartera.getSaldo());
                if (cartera.getSaldo() != null) preparedStatement.setInt(i++, cartera.getTarjeta());
                preparedStatement.setInt(i, cartera.getId());

                int updateRows = preparedStatement.executeUpdate();

                if (updateRows == 0) {
                    if (logger.isDebugEnabled()) logger.debug("Cartera no actualizada");
                    return cartera;
                }

                if (updateRows > 1) {
                    if (logger.isDebugEnabled()) logger.debug("Id de cartera duplicado");
                }

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return null;
    }

    private Cartera loadNext(ResultSet resultSet) throws SQLException {
        int i = 1;
        Cartera cartera = new Cartera();
        cartera.setId(resultSet.getInt(i++));
        cartera.setSaldo(resultSet.getDouble(i++));
        cartera.setTarjeta(resultSet.getInt(i));
        return cartera;
    }
}
