package com.gamblia.service.impl;

import com.gamblia.dao.spi.MovimientoDAO;
import com.gamblia.dao.utils.ConnectionManager;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Movimiento;
import com.gamblia.model.criteria.MovimientoCriteria;
import com.gamblia.service.spi.MovimientoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimientoServiceImpl implements MovimientoService {

    private final Logger logger = LogManager.getLogger(CarteraServiceImpl.class.getName());

    private MovimientoDAO movimientoDAO;

    @Override
    public Movimiento findById(Integer id) {
        if (logger.isDebugEnabled()) logger.debug("id: {}", id);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return movimientoDAO.findById(c, id);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public List<Movimiento> findBy(MovimientoCriteria movimientoCriteria) {
        if (logger.isDebugEnabled()) logger.debug("Criteria: {}", movimientoCriteria);
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return movimientoDAO.findBy(connection, movimientoCriteria);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Movimiento> findAll() {
        if (logger.isDebugEnabled()) logger.debug("All");
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return movimientoDAO.findAll(connection);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public Movimiento update(Movimiento movimiento) {
        if (logger.isDebugEnabled()) logger.debug("update: {}", movimiento);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return movimientoDAO.update(c, movimiento);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public Movimiento create(Movimiento movimiento) {
        if (logger.isDebugEnabled()) logger.debug("create: {}", movimiento);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return movimientoDAO.create(c, movimiento);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }
}
