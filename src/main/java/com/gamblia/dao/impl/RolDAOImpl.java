package com.gamblia.dao.impl;

import com.gamblia.dao.spi.RolDAO;
import com.gamblia.dao.utils.DAOUtils;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Rol;
import com.gamblia.model.criteria.RolCriteria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDAOImpl implements RolDAO {

    private final Logger logger = LogManager.getLogger(RolDAOImpl.class.getName());
    private final String select = " SELECT ID, ROL ";

    @Override
    public Rol findById(Connection connection, Integer id) {
        if (logger.isDebugEnabled())
            logger.debug("id: {}", id);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null && id != null) {
            try {
                StringBuilder query = new StringBuilder(select).append(" FROM ROL WHERE ID = ?");
                if (logger.isDebugEnabled())
                    logger.debug(query.toString());

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                preparedStatement.setInt(i, id);
                resultSet = preparedStatement.executeQuery();

                Rol rol = null;
                if (resultSet.next()) {
                    rol = loadNext(resultSet);
                } else {
                    if (logger.isDebugEnabled()) logger.debug("Rol {} not found", id);
                }
                if (resultSet.next()) {
                    if (logger.isDebugEnabled()) logger.debug("Id {} duplicate", id);
                }

                return rol;
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
    public List<Rol> findBy(Connection connection, RolCriteria rolCriteria) {
        if (logger.isDebugEnabled()) logger.debug("criteria: {}", rolCriteria);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder query;
        List<Rol> rols = new ArrayList<>();
        if (connection != null && rolCriteria != null) {
            try {
                query = new StringBuilder(select).append(" FROM ROL ");

                boolean first = true;

                if (rolCriteria.getId() != null) {
                    DAOUtils.addClause(query, true, " ID = ? ");
                    first = false;
                }
                if (rolCriteria.getRol() != null) {
                    DAOUtils.addClause(query, first, " ROL = ? ");
                }

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                if (rolCriteria.getId() != null) preparedStatement.setInt(i++, rolCriteria.getId());
                if (rolCriteria.getRol() != null)
                    preparedStatement.setString(i, rolCriteria.getRol());

                resultSet = preparedStatement.executeQuery();

                Rol rol;

                while (resultSet.next()) {
                    rol = loadNext(resultSet);
                    rols.add(rol);
                }


            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return rols;
    }

    @Override
    public List<Rol> findAll(Connection connection) {
        if (logger.isDebugEnabled()) logger.debug("all");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Rol> rols = new ArrayList<>();
        if (connection != null) {
            try {

                StringBuilder queryString = new StringBuilder(select).append(" FROM ROL ");
                if (logger.isDebugEnabled()) logger.debug(queryString.toString());
                preparedStatement = connection.prepareStatement(queryString.toString());

                resultSet = preparedStatement.executeQuery();


                Rol rol;

                while (resultSet.next()) {
                    rol = loadNext(resultSet);
                    rols.add(rol);
                }

            } catch (SQLException e) {
                logger.warn(e.getMessage(), e);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }

        return rols;
    }

    @Override
    public Rol update(Connection connection, Rol rol) {
        if (logger.isDebugEnabled()) logger.debug("Update rol: {}", rol);
        PreparedStatement preparedStatement = null;
        StringBuilder query;
        if (connection != null && rol != null && rol.getId() != null) {
            try {
                query = new StringBuilder(" UPDATE ROL ");

                if (rol.getRol() != null) {
                    DAOUtils.addUpdate(query, true, " ROL = ? ");
                }
                query.append(" WHERE ID = ?");
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;

                if (rol.getRol() != null) preparedStatement.setString(i++, rol.getRol());
                preparedStatement.setInt(i, rol.getId());

                int updateRows = preparedStatement.executeUpdate();

                if (updateRows == 0) {
                    if (logger.isDebugEnabled()) logger.debug("Operacion no actualizada");
                    return rol;
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
    public Rol create(Connection connection, Rol rol) {
        if (logger.isDebugEnabled()) logger.debug("Create rol: {}", rol);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (rol != null && rol.getRol() != null) {
            try {
                String query = " INSERT INTO ROL (ROL) values (?) ";
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                int i = 1;
                preparedStatement.setString(i, rol.getRol());

                int insertedRows = preparedStatement.executeUpdate();

                if (insertedRows == 0) {
                    throw new SQLException("Can not add row to table 'Rol'");
                }

                resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    Integer pk = resultSet.getInt(1);
                    rol.setId(pk);
                    return rol;
                } else {
                    logger.warn("Unable to fetch autogenerated primary key");
                }

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            }
        }

        return null;
    }

    private Rol loadNext(ResultSet resultSet) throws SQLException {
        int i = 1;
        Rol rol = new Rol();
        rol.setId(resultSet.getInt(i++));
        rol.setRol(resultSet.getString(i));
        return rol;
    }

}
