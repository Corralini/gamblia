package com.gamblia.dao.impl;

import com.gamblia.dao.spi.OperacionDAO;
import com.gamblia.dao.utils.DAOUtils;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Operacion;
import com.gamblia.model.criteria.OperacionCriteria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperacionDAOImpl implements OperacionDAO {

    private final Logger logger = LogManager.getLogger(OperacionDAOImpl.class.getName());
    private final String select = " SELECT ID, OPERACION ";

    public OperacionDAOImpl() {
    }

    @Override
    public Operacion findById(Connection connection, Integer id) {
        if (logger.isDebugEnabled())
            logger.debug("id: {}", id);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null && id != null) {
            try {
                StringBuilder query = new StringBuilder(select).append(" FROM OPERACION WHERE ID = ?");
                if (logger.isDebugEnabled())
                    logger.debug(query.toString());

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                preparedStatement.setInt(i, id);
                resultSet = preparedStatement.executeQuery();

                Operacion operacion = null;
                if (resultSet.next()) {
                    operacion = loadNext(resultSet);
                } else {
                    if (logger.isDebugEnabled()) logger.debug("Operacion {} not found", id);
                }
                if (resultSet.next()) {
                    if (logger.isDebugEnabled()) logger.debug("Id {} duplicate", id);
                }

                return operacion;
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
    public List<Operacion> findBy(Connection connection, OperacionCriteria operacionCriteria) {
        if (logger.isDebugEnabled()) logger.debug("criteria: {}", operacionCriteria);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder query;
        List<Operacion> operacions = new ArrayList<>();
        if (connection != null && operacionCriteria != null) {
            try {
                query = new StringBuilder(select).append(" FROM OPERACION ");

                boolean first = true;

                if (operacionCriteria.getId() != null) {
                    DAOUtils.addClause(query, true, " ID = ? ");
                    first = false;
                }
                if (operacionCriteria.getOperacion() != null) {
                    DAOUtils.addClause(query, first, " OPERACION = ? ");
                }

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                if (operacionCriteria.getId() != null) preparedStatement.setInt(i++, operacionCriteria.getId());
                if (operacionCriteria.getOperacion() != null)
                    preparedStatement.setString(i, operacionCriteria.getOperacion());

                resultSet = preparedStatement.executeQuery();

                Operacion operacion;

                while (resultSet.next()) {
                    operacion = loadNext(resultSet);
                    operacions.add(operacion);
                }


            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return operacions;
    }

    @Override
    public List<Operacion> findAll(Connection connection) {
        if (logger.isDebugEnabled()) logger.debug("all");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Operacion> operacions = new ArrayList<>();
        if (connection != null) {
            try {

                StringBuilder queryString = new StringBuilder(select).append(" FROM OPERACION ");
                if (logger.isDebugEnabled()) logger.debug(queryString.toString());
                preparedStatement = connection.prepareStatement(queryString.toString());

                resultSet = preparedStatement.executeQuery();


                Operacion operacion;

                while (resultSet.next()) {
                    operacion = loadNext(resultSet);
                    operacions.add(operacion);
                }

            } catch (SQLException e) {
                logger.warn(e.getMessage(), e);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }

        return operacions;
    }

    @Override
    public Operacion update(Connection connection, Operacion operacion) {
        if (logger.isDebugEnabled()) logger.debug("Update mesa: {}", operacion);
        PreparedStatement preparedStatement = null;
        StringBuilder query;
        if (connection != null && operacion != null && operacion.getId() != null) {
            try {
                query = new StringBuilder(" UPDATE JUEGO ");

                if (operacion.getOperacion() != null) {
                    DAOUtils.addUpdate(query, true, " OPERACION = ? ");
                }
                query.append(" WHERE ID = ?");
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;

                if (operacion.getOperacion() != null) preparedStatement.setString(i++, operacion.getOperacion());
                preparedStatement.setInt(i, operacion.getId());

                int updateRows = preparedStatement.executeUpdate();

                if (updateRows == 0) {
                    if (logger.isDebugEnabled()) logger.debug("Operacion no actualizada");
                    return operacion;
                }

                if (updateRows > 1) {
                    if (logger.isDebugEnabled()) logger.debug("Id de operacion duplicado");
                }

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return null;
    }

    @Override
    public Operacion create(Connection connection, Operacion operacion) {
        if (logger.isDebugEnabled()) logger.debug("Create operacion: {}", operacion);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (operacion != null && operacion.getOperacion() != null) {
            try {
                String query = " INSERT INTO OPERACION (OPERACON) values (?) ";
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                int i = 1;
                preparedStatement.setString(i, operacion.getOperacion());

                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows == 0) {
                    throw new SQLException("Can not add row to table 'Operacion'");
                }

                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    Integer pk = resultSet.getInt(1);
                    operacion.setId(pk);
                    return operacion;
                } else {
                    logger.warn("Unable to fetch autogenerated primary key");
                }

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            }
        }

        return null;
    }

    private Operacion loadNext(ResultSet resultSet) throws SQLException {
        int i = 1;
        Operacion operacion = new Operacion();
        operacion.setId(resultSet.getInt(i++));
        operacion.setOperacion(resultSet.getString(i));
        return operacion;
    }
}
