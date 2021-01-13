package com.gamblia.service.impl;

import com.gamblia.dao.impl.JuegoDAOImpl;
import com.gamblia.dao.spi.JuegoDAO;
import com.gamblia.dao.utils.ConnectionManager;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Juego;
import com.gamblia.model.criteria.JuegoCriteria;
import com.gamblia.service.spi.JuegoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JuegoServiceImpl implements JuegoService {

    private final Logger logger = LogManager.getLogger(JuegoServiceImpl.class.getName());
    private final JuegoDAO juegoDAO;

    public JuegoServiceImpl() {
        juegoDAO = new JuegoDAOImpl();
    }

    @Override
    public Juego findById(Integer id) {
        if (logger.isDebugEnabled()) logger.debug("id: {}", id);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return juegoDAO.findById(c, id);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public List<Juego> findBy(JuegoCriteria juegoCriteria) {
        if (logger.isDebugEnabled()) logger.debug("Criteria: {}", juegoCriteria);
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return juegoDAO.findBy(connection, juegoCriteria);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Juego> findAll() {
        if (logger.isDebugEnabled()) logger.debug("All");
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return juegoDAO.findAll(connection);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public Juego update(Juego juego) {
        if (logger.isDebugEnabled()) logger.debug("juego: {}", juego);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return juegoDAO.update(c, juego);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public void delete(Integer id) {
        if (logger.isDebugEnabled()) logger.debug("id delete: {}", id);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            juegoDAO.delete(c, id);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }
    }

    @Override
    public Juego create(Juego juego) {
        if (logger.isDebugEnabled()) logger.debug("juego: {}", juego);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return juegoDAO.create(c, juego);
        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }
        return null;
    }
}
