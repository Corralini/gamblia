package com.gamblia.service.impl;

import com.gamblia.dao.impl.OperacionDAOImpl;
import com.gamblia.dao.spi.OperacionDAO;
import com.gamblia.dao.utils.ConnectionManager;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Operacion;
import com.gamblia.model.criteria.OperacionCriteria;
import com.gamblia.service.spi.OperacionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperacionServiceImpl implements OperacionService {

    private final Logger logger = LogManager.getLogger(CarteraServiceImpl.class.getName());

    private final OperacionDAO operacionDAO;

    public OperacionServiceImpl() {
        operacionDAO = new OperacionDAOImpl();
    }

    @Override
    public Operacion findById(Integer id) {
        if (logger.isDebugEnabled()) logger.debug("id: {}", id);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return operacionDAO.findById(c, id);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public List<Operacion> findBy(OperacionCriteria operacionCriteria) {
        if (logger.isDebugEnabled()) logger.debug("Criteria: {}", operacionCriteria);
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return operacionDAO.findBy(connection, operacionCriteria);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Operacion> findAll() {
        if (logger.isDebugEnabled()) logger.debug("All");
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return operacionDAO.findAll(connection);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public Operacion update(Operacion operacion) {
        if (logger.isDebugEnabled()) logger.debug("update: {}", operacion);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return operacionDAO.update(c, operacion);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public Operacion create(Operacion operacion) {
        if (logger.isDebugEnabled()) logger.debug("create: {}", operacion);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return operacionDAO.create(c, operacion);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }
}
