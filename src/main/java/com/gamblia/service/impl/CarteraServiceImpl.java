package com.gamblia.service.impl;

import com.gamblia.dao.impl.CarteraDAOImpl;
import com.gamblia.dao.spi.CarteraDAO;
import com.gamblia.dao.utils.ConnectionManager;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Cartera;
import com.gamblia.model.criteria.CarteraCriteria;
import com.gamblia.service.spi.CarteraService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarteraServiceImpl implements CarteraService {

    private final Logger logger = LogManager.getLogger(CarteraServiceImpl.class.getName());

    private final CarteraDAO carteraDAO;

    public CarteraServiceImpl() {
        carteraDAO = new CarteraDAOImpl();
    }

    @Override
    public Cartera findById(Integer id) {
        if (logger.isDebugEnabled()) logger.debug("id: {}", id);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return carteraDAO.findById(c, id);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;

    }

    @Override
    public List<Cartera> findBy(CarteraCriteria carteraCriteria) {
        if (logger.isDebugEnabled()) logger.debug("Criteria: {}", carteraCriteria);
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return carteraDAO.findBy(connection, carteraCriteria);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Cartera> findAll() {
        if (logger.isDebugEnabled()) logger.debug("All");
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return carteraDAO.findAll(connection);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public Cartera update(Cartera cartera) {
        if (logger.isDebugEnabled()) logger.debug("update: {}", cartera);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return carteraDAO.update(c, cartera);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public Cartera create(Cartera cartera) {
        if (logger.isDebugEnabled()) logger.debug("create: {}", cartera);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return carteraDAO.create(c, cartera);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }
}
