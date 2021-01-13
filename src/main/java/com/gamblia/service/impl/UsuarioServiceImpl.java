package com.gamblia.service.impl;

import com.gamblia.dao.impl.UsuarioDAOImpl;
import com.gamblia.dao.spi.UsuarioDAO;
import com.gamblia.dao.utils.ConnectionManager;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Usuario;
import com.gamblia.model.criteria.UsuarioCriteria;
import com.gamblia.service.spi.UsuarioService;
import com.gamblia.utils.EncryptionUtils;
import com.gamblia.utils.ValidationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {

    private final Logger logger = LogManager.getLogger(UsuarioServiceImpl.class.getName());

    private final UsuarioDAO usuarioDAO;

    public UsuarioServiceImpl() {
        usuarioDAO = new UsuarioDAOImpl();
    }

    @Override
    public Usuario findById(Integer id) {
        if (logger.isDebugEnabled()) logger.debug("id: {}", id);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return usuarioDAO.findById(c, id);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public List<Usuario> findBy(UsuarioCriteria usuarioCriteria) {
        if (logger.isDebugEnabled()) logger.debug("Criteria: {}", usuarioCriteria);
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return usuarioDAO.findBy(connection, usuarioCriteria);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public List<Usuario> findAll() {
        if (logger.isDebugEnabled()) logger.debug("All");
        Connection connection = null;

        try {

            connection = ConnectionManager.getConnection();
            connection.setAutoCommit(true);

            return usuarioDAO.findAll(connection);

        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            JDBCUtils.closeConnection(connection);
        }

        return new ArrayList<>();
    }

    @Override
    public Usuario update(Usuario usuario) {
        if (logger.isDebugEnabled()) logger.debug("update: {}", usuario);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return usuarioDAO.update(c, usuario);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public Usuario create(Usuario usuario) {
        if (logger.isDebugEnabled()) logger.debug("create: {}", usuario);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            c.setAutoCommit(true);
            return usuarioDAO.create(c, usuario);

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }

        return null;
    }

    @Override
    public Usuario login(String user, String psswd) {
        if (logger.isDebugEnabled()) logger.debug("email: {}; psswd: {}", user, psswd == null);
        Connection c = null;
        try {
            c = ConnectionManager.getConnection();
            if (user == null || psswd == null) {
                return null;
            }

            Usuario usuario;

            if (ValidationUtils.validateEmail(user)) {
                usuario = usuarioDAO.findByEmail(c, user);
            } else {
                usuario = usuarioDAO.findByUser(c, user);
            }

            if (usuario == null) {
                return usuario;
            }

            if (EncryptionUtils.checkPassword(psswd, usuario.getPsswd())) {
                return usuario;
            } else {
                if (logger.isDebugEnabled()) logger.debug("Psswd inorrecta");
            }

        } catch (SQLException ex) {
            logger.warn(ex.getMessage(), ex);
        } finally {
            JDBCUtils.closeConnection(c);
        }
        return null;
    }
}
