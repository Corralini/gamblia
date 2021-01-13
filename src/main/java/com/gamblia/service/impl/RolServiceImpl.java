package com.gamblia.service.impl;

import com.gamblia.dao.impl.RolDAOImpl;
import com.gamblia.dao.spi.CarteraDAO;
import com.gamblia.dao.spi.RolDAO;
import com.gamblia.dao.utils.ConnectionManager;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Rol;
import com.gamblia.model.criteria.RolCriteria;
import com.gamblia.service.spi.RolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolServiceImpl implements RolService {

    private final Logger logger = LogManager.getLogger(CarteraServiceImpl.class.getName());

    private final RolDAO rolDAO;

    public RolServiceImpl() {
        rolDAO = new RolDAOImpl();
    }

    @Override
    public Rol findById(Integer id) {
        if (logger.isDebugEnabled()) logger.debug("id: {}", id);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return rolDAO.findById(c, id);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public List<Rol> findBy(RolCriteria rolCriteria) {
        if (logger.isDebugEnabled()) logger.debug("Criteria: {}", rolCriteria);
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return rolDAO.findBy(connection, rolCriteria);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Rol> findAll() {
        if (logger.isDebugEnabled()) logger.debug("All");
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return rolDAO.findAll(connection);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public Rol update(Rol rol) {
        if (logger.isDebugEnabled()) logger.debug("update: {}", rol);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return rolDAO.update(c, rol);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public Rol create(Rol rol) {
        if (logger.isDebugEnabled()) logger.debug("create: {}", rol);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return rolDAO.create(c, rol);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }
}
