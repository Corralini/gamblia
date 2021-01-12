package com.gamblia.dao.impl;

import com.gamblia.dao.spi.MesaDAO;
import com.gamblia.dao.utils.DAOUtils;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Mesa;
import com.gamblia.model.criteria.MesaCriteria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesaDAOImpl implements MesaDAO {

    private final Logger logger = LogManager.getLogger(MesaDAOImpl.class.getName());
    private final String select = " SELECT ID, NOMBRE, MAX, ID_JUEGO, PSSWD, APUESTA_MIN, CODE ";

    public MesaDAOImpl() {
    }

    @Override
    public Mesa findById(Connection connection, Integer id) {
        if (logger.isDebugEnabled())
            logger.debug("id: {}", id);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null && id != null) {
            try {
                StringBuilder query = new StringBuilder(select).append(" FROM MESA WHERE ID = ?");
                if (logger.isDebugEnabled())
                    logger.debug(query.toString());

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                preparedStatement.setInt(i, id);
                resultSet = preparedStatement.executeQuery();

                Mesa mesa = null;
                if (resultSet.next()) {
                    mesa = loadNext(resultSet);
                } else {
                    if (logger.isDebugEnabled()) logger.debug("Mesa {} not found", id);
                }
                if (resultSet.next()) {
                    if (logger.isDebugEnabled()) logger.debug("Id {} duplicate", id);
                }

                return mesa;
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
    public List<Mesa> findBy(Connection connection, MesaCriteria mesaCriteria) {
        if (logger.isDebugEnabled()) logger.debug("criteria: {}", mesaCriteria);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder query;
        List<Mesa> mesas = new ArrayList<>();
        if (connection != null && mesaCriteria != null) {
            try {
                query = new StringBuilder(select).append(" FROM MESA ");

                boolean first = true;

                if (mesaCriteria.getId() != null) {
                    DAOUtils.addClause(query, true, " ID = ? ");
                    first = false;
                }
                if (mesaCriteria.getNombre() != null) {
                    DAOUtils.addClause(query, first, " NOMBRE = ? ");
                    first = false;
                }
                if (mesaCriteria.getMax() != null) {
                    DAOUtils.addClause(query, first, " MAX = ? ");
                    first = false;
                }
                if (mesaCriteria.getIdJuego() != null) {
                    DAOUtils.addClause(query, first, " ID_JUEGO = ? ");
                    first = false;
                }
                if (mesaCriteria.getPsswd() != null) {
                    DAOUtils.addClause(query, first, " PSSWD = ? ");
                    first = false;
                }
                if (mesaCriteria.getApuestaMin() != null) {
                    DAOUtils.addClause(query, first, " APUESTA_MIN = ? ");
                    first = false;
                }
                if (mesaCriteria.getCode() != null) {
                    DAOUtils.addClause(query, first, " CODE = ? ");
                }

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                if (mesaCriteria.getId() != null) preparedStatement.setInt(i++, mesaCriteria.getId());
                if (mesaCriteria.getNombre() != null) preparedStatement.setString(i, mesaCriteria.getNombre());
                if (mesaCriteria.getMax() != null) preparedStatement.setInt(i, mesaCriteria.getMax());
                if (mesaCriteria.getIdJuego() != null) preparedStatement.setInt(i, mesaCriteria.getIdJuego());
                if (mesaCriteria.getPsswd() != null) preparedStatement.setString(i, mesaCriteria.getPsswd());
                if (mesaCriteria.getApuestaMin() != null) preparedStatement.setDouble(i, mesaCriteria.getApuestaMin());
                if (mesaCriteria.getCode() != null) preparedStatement.setInt(i, mesaCriteria.getCode());

                resultSet = preparedStatement.executeQuery();

                Mesa mesa;

                while (resultSet.next()) {
                    mesa = loadNext(resultSet);
                    mesas.add(mesa);
                }


            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return mesas;
    }

    @Override
    public List<Mesa> findAll(Connection connection) {
        if (logger.isDebugEnabled()) logger.debug("all");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Mesa> mesas = new ArrayList<>();
        if (connection != null) {
            try {

                StringBuilder queryString = new StringBuilder(select).append(" FROM MESA ");
                if (logger.isDebugEnabled()) logger.debug(queryString.toString());
                preparedStatement = connection.prepareStatement(queryString.toString());

                resultSet = preparedStatement.executeQuery();


                Mesa mesa;

                while (resultSet.next()) {
                    mesa = loadNext(resultSet);
                    mesas.add(mesa);
                }

            } catch (SQLException e) {
                logger.warn(e.getMessage(), e);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }

        return mesas;
    }

    @Override
    public Mesa update(Connection connection, Mesa mesa) {
        if (logger.isDebugEnabled()) logger.debug("Update mesa: {}", mesa);
        PreparedStatement preparedStatement = null;
        StringBuilder query;
        if (connection != null && mesa != null && mesa.getId() != null) {
            try {
                query = new StringBuilder(" UPDATE MESA ");

                boolean first = true;
                if (mesa.getNombre() != null) {
                    DAOUtils.addUpdate(query, true, " NOMBRE = ? ");
                    first = false;
                }
                if (mesa.getMax() != null) {
                    DAOUtils.addUpdate(query, first, " MAX = ? ");
                    first = false;
                }
                if (mesa.getIdJuego() != null) {
                    DAOUtils.addUpdate(query, first, " ID_JUEGO = ? ");
                    first = false;
                }
                if (mesa.getPsswd() != null) {
                    DAOUtils.addUpdate(query, first, " PSSWD = ? ");
                    first = false;
                }
                if (mesa.getApuestaMin() != null) {
                    DAOUtils.addUpdate(query, first, " APUESTA_MIN = ? ");
                    first = false;
                }
                if (mesa.getCode() != null) {
                    DAOUtils.addUpdate(query, first, " CODE = ? ");
                }
                query.append(" WHERE ID = ?");
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;

                if (mesa.getNombre() != null) preparedStatement.setString(i++, mesa.getNombre());
                if (mesa.getMax() != null) preparedStatement.setInt(i++, mesa.getMax());
                if (mesa.getIdJuego() != null) preparedStatement.setInt(i++, mesa.getIdJuego());
                if (mesa.getPsswd() != null) preparedStatement.setString(i++, mesa.getPsswd());
                if (mesa.getApuestaMin() != null) preparedStatement.setDouble(i++, mesa.getApuestaMin());
                if (mesa.getCode() != null) preparedStatement.setInt(i++, mesa.getCode());
                preparedStatement.setInt(i, mesa.getId());

                int updateRows = preparedStatement.executeUpdate();

                if (updateRows == 0) {
                    if (logger.isDebugEnabled()) logger.debug("Mesa no actualizada");
                    return mesa;
                }

                if (updateRows > 1) {
                    if (logger.isDebugEnabled()) logger.debug("Id de mesa duplicado");
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
    public void delete(Connection connection, Integer id) {
        if (logger.isDebugEnabled())
            logger.debug("id: {}", id);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null && id != null) {
            try {
                StringBuilder query = new StringBuilder("DELETE FROM MESA WHERE ID = ?");
                if (logger.isDebugEnabled())
                    logger.debug(query.toString());

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                preparedStatement.setInt(i, id);
                resultSet = preparedStatement.executeQuery();

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
    }

    private Mesa loadNext(ResultSet resultSet) throws SQLException {
        int i = 1;
        Mesa mesa = new Mesa();
        mesa.setId(resultSet.getInt(i++));
        mesa.setNombre(resultSet.getString(i++));
        mesa.setMax(resultSet.getInt(i++));
        mesa.setIdJuego(resultSet.getInt(i++));
        mesa.setPsswd(resultSet.getString(i++));
        mesa.setApuestaMin(resultSet.getDouble(i++));
        mesa.setCode(resultSet.getInt(i));
        return mesa;
    }
}
