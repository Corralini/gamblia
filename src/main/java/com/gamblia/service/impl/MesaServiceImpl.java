package com.gamblia.service.impl;

import com.gamblia.dao.impl.MesaDAOImpl;
import com.gamblia.dao.spi.MesaDAO;
import com.gamblia.dao.utils.ConnectionManager;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Mesa;
import com.gamblia.model.criteria.MesaCriteria;
import com.gamblia.service.spi.MesaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesaServiceImpl implements MesaService {

    private final Logger logger = LogManager.getLogger(MesaServiceImpl.class.getName());

    private final MesaDAO mesaDAO;

    public MesaServiceImpl() {
        mesaDAO = new MesaDAOImpl();
    }

    @Override
    public Mesa findById(Integer id) {
        if (logger.isDebugEnabled()) logger.debug("id: {}", id);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return mesaDAO.findById(c, id);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public List<Mesa> findBy(MesaCriteria mesaCriteria) {
        if (logger.isDebugEnabled()) logger.debug("Criteria: {}", mesaCriteria);
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return mesaDAO.findBy(connection, mesaCriteria);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Mesa> findAll() {
        if (logger.isDebugEnabled()) logger.debug("All");
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return mesaDAO.findAll(connection);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public Mesa update(Mesa mesa) {
        if (logger.isDebugEnabled()) logger.debug("update: {}", mesa);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return mesaDAO.update(c, mesa);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public void delete(Integer id) {
        if (logger.isDebugEnabled()) logger.debug("delete: {}", id);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            mesaDAO.delete(c, id);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }
    }

    @Override
    public Mesa create(Mesa mesa) {
        if (logger.isDebugEnabled()) logger.debug("create: {}", mesa);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return mesaDAO.create(c, mesa);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }
}
