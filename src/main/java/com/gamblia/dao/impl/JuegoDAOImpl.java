package com.gamblia.dao.impl;

import com.gamblia.dao.spi.JuegoDAO;
import com.gamblia.dao.utils.DAOUtils;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Juego;
import com.gamblia.model.criteria.JuegoCriteria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JuegoDAOImpl implements JuegoDAO {

    private final Logger logger = LogManager.getLogger(JuegoDAOImpl.class.getName());
    private final String select = " SELECT ID, NOMBRE ";

    public JuegoDAOImpl() {
    }

    @Override
    public Juego findById(Connection connection, Integer id) {
        if (logger.isDebugEnabled())
            logger.debug("id: {}", id);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null && id != null) {
            try {
                StringBuilder query = new StringBuilder(select).append(" FROM JUEGO WHERE ID = ?");
                if (logger.isDebugEnabled())
                    logger.debug(query.toString());

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                preparedStatement.setInt(i, id);
                resultSet = preparedStatement.executeQuery();

                Juego juego = null;
                if (resultSet.next()) {
                    juego = loadNext(resultSet);
                } else {
                    if (logger.isDebugEnabled()) logger.debug("Juego {} not found", id);
                }
                if (resultSet.next()) {
                    if (logger.isDebugEnabled()) logger.debug("Id {} duplicate", id);
                }

                return juego;
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
    public List<Juego> findBy(Connection connection, JuegoCriteria juegoCriteria) {
        if (logger.isDebugEnabled()) logger.debug("criteria: {}", juegoCriteria);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder query;
        List<Juego> juegos = new ArrayList<>();
        if (connection != null && juegoCriteria != null) {
            try {
                query = new StringBuilder(select).append(" FROM JUEGO ");

                boolean first = true;

                if (juegoCriteria.getId() != null) {
                    DAOUtils.addClause(query, first, " id = ? ");
                    first = false;
                }
                if (juegoCriteria.getNombre() != null) {
                    DAOUtils.addClause(query, first, " nombre = ? ");
                }

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                if (juegoCriteria.getId() != null) preparedStatement.setInt(i++, juegoCriteria.getId());
                if (juegoCriteria.getNombre() != null) preparedStatement.setString(i, juegoCriteria.getNombre());

                resultSet = preparedStatement.executeQuery();

                Juego juego;

                while (resultSet.next()) {
                    juego = loadNext(resultSet);
                    juegos.add(juego);
                }


            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return juegos;
    }

    @Override
    public List<Juego> findAll(Connection connection) {
        if (logger.isDebugEnabled()) logger.debug("all");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Juego> juegos = new ArrayList<>();
        if (connection != null) {
            try {

                StringBuilder queryString = new StringBuilder(select).append(" FROM JUEGO ");
                if (logger.isDebugEnabled()) logger.debug(queryString.toString());
                preparedStatement = connection.prepareStatement(queryString.toString());

                resultSet = preparedStatement.executeQuery();


                Juego juego;

                while (resultSet.next()) {
                    juego = loadNext(resultSet);
                    juegos.add(juego);
                }

            } catch (SQLException e) {
                logger.warn(e.getMessage(), e);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }

        return juegos;
    }

    @Override
    public Juego update(Connection connection, Juego juego) {
        if (logger.isDebugEnabled()) logger.debug("Update cartera: {}", juego);
        PreparedStatement preparedStatement = null;
        StringBuilder query;
        if (connection != null && juego != null && juego.getId() != null) {
            try {
                query = new StringBuilder(" UPDATE JUEGO ");


                if (juego.getNombre() != null) {
                    DAOUtils.addUpdate(query, true, " nombre = ? ");
                }
                query.append(" WHERE ID = ?");
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;

                if (juego.getNombre() != null) preparedStatement.setString(i++, juego.getNombre());
                preparedStatement.setInt(i, juego.getId());

                int updateRows = preparedStatement.executeUpdate();

                if (updateRows == 0) {
                    if (logger.isDebugEnabled()) logger.debug("Juego no actualizado");
                    return juego;
                }

                if (updateRows > 1) {
                    if (logger.isDebugEnabled()) logger.debug("Id de juego duplicado");
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
                StringBuilder query = new StringBuilder("DELETE FROM JUEGO WHERE ID = ?");
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

    @Override
    public Juego create(Connection connection, Juego juego) {
        if (logger.isDebugEnabled()) logger.debug("Create juego: {}", juego);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (juego != null && juego.getNombre() != null) {
            try {
                String query = " INSERT INTO JUEGO (NOMBRE) values (?) ";
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                int i = 1;
                preparedStatement.setString(i, juego.getNombre());

                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows == 0) {
                    throw new SQLException("Can not add row to table 'Juego'");
                }

                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    Integer pk = resultSet.getInt(1);
                    juego.setId(pk);
                    return juego;
                } else {
                    logger.warn("Unable to fetch autogenerated primary key");
                }

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            }
        }

        return null;
    }

    private Juego loadNext(ResultSet resultSet) throws SQLException {
        int i = 1;
        Juego juego = new Juego();
        juego.setId(resultSet.getInt(i++));
        juego.setNombre(resultSet.getString(i));
        return juego;
    }

}
