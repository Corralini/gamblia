package com.gamblia.dao.impl;

import com.gamblia.dao.spi.UsuarioDAO;
import com.gamblia.dao.utils.DAOUtils;
import com.gamblia.dao.utils.JDBCUtils;
import com.gamblia.model.Usuario;
import com.gamblia.model.criteria.UsuarioCriteria;
import com.gamblia.utils.BooleanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    private final Logger logger = LogManager.getLogger(UsuarioDAOImpl.class.getName());
    private final String select = " SELECT ID, NOMBRE, APELLIDO1, APELLIDO2, USERNAME, EMAIL, PSSWD, ID_MESA, ID_ROL,"
            + " ID_CARTERA, ACTIVE ";

    public UsuarioDAOImpl() {
    }

    @Override
    public Usuario findById(Connection connection, Integer id) {
        if (logger.isDebugEnabled())
            logger.debug("id: {}", id);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection != null && id != null) {
            try {
                StringBuilder query = new StringBuilder(select).append(" FROM USUARIO WHERE ID = ?");
                if (logger.isDebugEnabled())
                    logger.debug(query.toString());

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                preparedStatement.setInt(i, id);
                resultSet = preparedStatement.executeQuery();

                Usuario usuario = null;
                if (resultSet.next()) {
                    usuario = loadNext(resultSet);
                } else {
                    if (logger.isDebugEnabled()) logger.debug("Operacion {} not found", id);
                }
                if (resultSet.next()) {
                    if (logger.isDebugEnabled()) logger.debug("Id {} duplicate", id);
                }

                return usuario;
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
    public List<Usuario> findBy(Connection connection, UsuarioCriteria usuarioCriteria) {
        if (logger.isDebugEnabled()) logger.debug("criteria: {}", usuarioCriteria);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StringBuilder query;
        List<Usuario> usuarios = new ArrayList<>();
        if (connection != null && usuarioCriteria != null) {
            try {
                query = new StringBuilder(select).append(" FROM USUARIO ");

                boolean first = true;

                if (usuarioCriteria.getId() != null) {
                    DAOUtils.addClause(query, true, " ID = ? ");
                    first = false;
                }
                if (usuarioCriteria.getNombre() != null) {
                    DAOUtils.addClause(query, first, " NOMBRE = ? ");
                    first = false;
                }
                if (usuarioCriteria.getApellido1() != null) {
                    DAOUtils.addClause(query, first, " APELLIDO1 = ? ");
                    first = false;
                }
                if (usuarioCriteria.getApellido2() != null) {
                    DAOUtils.addClause(query, first, " APELLIDO2 = ? ");
                    first = false;
                }
                if (usuarioCriteria.getUsername() != null) {
                    DAOUtils.addClause(query, first, " USERNAME = ? ");
                    first = false;
                }
                if (usuarioCriteria.getEmail() != null) {
                    DAOUtils.addClause(query, first, " EMAIL = ? ");
                    first = false;
                }
                if (usuarioCriteria.getPsswd() != null) {
                    DAOUtils.addClause(query, first, " PSSWD = ? ");
                    first = false;
                }
                if (usuarioCriteria.getIdMesa() != null) {
                    DAOUtils.addClause(query, first, " ID_MESA = ? ");
                    first = false;
                }
                if (usuarioCriteria.getIdRol() != null) {
                    DAOUtils.addClause(query, first, " ID_ROL = ? ");
                    first = false;
                }
                if (usuarioCriteria.getIdCartera() != null) {
                    DAOUtils.addClause(query, first, " ID_CARTERA = ? ");
                    first = false;
                }
                if (usuarioCriteria.getActive() != null) {
                    DAOUtils.addClause(query, first, " ACTIVE = ? ");
                }

                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;
                if (usuarioCriteria.getId() != null) preparedStatement.setInt(i++, usuarioCriteria.getId());
                if (usuarioCriteria.getNombre() != null)
                    preparedStatement.setString(i++, usuarioCriteria.getNombre());
                if (usuarioCriteria.getApellido1() != null)
                    preparedStatement.setString(i++, usuarioCriteria.getApellido1());
                if (usuarioCriteria.getApellido2() != null)
                    preparedStatement.setString(i++, usuarioCriteria.getApellido2());
                if (usuarioCriteria.getUsername() != null)
                    preparedStatement.setString(i++, usuarioCriteria.getUsername());
                if (usuarioCriteria.getEmail() != null)
                    preparedStatement.setString(i++, usuarioCriteria.getEmail());
                if (usuarioCriteria.getPsswd() != null)
                    preparedStatement.setString(i++, usuarioCriteria.getPsswd());
                if (usuarioCriteria.getIdMesa() != null)
                    preparedStatement.setInt(i++, usuarioCriteria.getIdMesa());
                if (usuarioCriteria.getIdRol() != null)
                    preparedStatement.setInt(i++, usuarioCriteria.getIdRol());
                if (usuarioCriteria.getIdCartera() != null)
                    preparedStatement.setInt(i++, usuarioCriteria.getIdCartera());
                if (usuarioCriteria.getActive() != null)
                    preparedStatement.setInt(i, BooleanUtils.booleanToInteger(usuarioCriteria.getActive()));


                resultSet = preparedStatement.executeQuery();

                Usuario usuario;

                while (resultSet.next()) {
                    usuario = loadNext(resultSet);
                    usuarios.add(usuario);
                }


            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return usuarios;
    }

    @Override
    public List<Usuario> findAll(Connection connection) {
        if (logger.isDebugEnabled()) logger.debug("all");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Usuario> usuarios = new ArrayList<>();
        if (connection != null) {
            try {

                StringBuilder queryString = new StringBuilder(select).append(" FROM USUARIO ");
                if (logger.isDebugEnabled()) logger.debug(queryString.toString());
                preparedStatement = connection.prepareStatement(queryString.toString());

                resultSet = preparedStatement.executeQuery();


                Usuario usuario;

                while (resultSet.next()) {
                    usuario = loadNext(resultSet);
                    usuarios.add(usuario);
                }

            } catch (SQLException e) {
                logger.warn(e.getMessage(), e);
            } finally {
                JDBCUtils.closeResultSet(resultSet);
                JDBCUtils.closeStatement(preparedStatement);
            }
        }

        return usuarios;
    }

    @Override
    public Usuario update(Connection connection, Usuario usuario) {
        if (logger.isDebugEnabled()) logger.debug("Update usuario: {}", usuario);
        PreparedStatement preparedStatement = null;
        StringBuilder query;
        if (connection != null && usuario != null && usuario.getId() != null) {
            try {
                query = new StringBuilder(" UPDATE USUARIO ");

                boolean first = true;

                if (usuario.getNombre() != null) {
                    DAOUtils.addUpdate(query, true, " NOMBRE = ? ");
                    first = false;
                }
                if (usuario.getApellido1() != null) {
                    DAOUtils.addUpdate(query, first, " APELLIDO1 = ? ");
                    first = false;
                }
                if (usuario.getApellido2() != null) {
                    DAOUtils.addUpdate(query, first, " APELLIDO2 = ? ");
                    first = false;
                }
                if (usuario.getUsername() != null) {
                    DAOUtils.addUpdate(query, first, " USERNAME = ? ");
                    first = false;
                }
                if (usuario.getEmail() != null) {
                    DAOUtils.addUpdate(query, first, " EMAIL = ? ");
                    first = false;
                }
                if (usuario.getPsswd() != null) {
                    DAOUtils.addUpdate(query, first, " PSSWD = ? ");
                    first = false;
                }
                if (usuario.getIdMesa() != null) {
                    DAOUtils.addUpdate(query, first, " ID_MESA = ? ");
                    first = false;
                }
                if (usuario.getIdRol() != null) {
                    DAOUtils.addUpdate(query, first, " ID_ROL = ? ");
                    first = false;
                }
                if (usuario.getIdCartera() != null) {
                    DAOUtils.addUpdate(query, first, " ID_CARTERA = ? ");
                    first = false;
                }
                if (usuario.getActive() != null) {
                    DAOUtils.addUpdate(query, first, " ACTIVE = ? ");
                }
                query.append(" WHERE ID = ?");
                if (logger.isDebugEnabled()) logger.debug(query);
                preparedStatement = connection.prepareStatement(query.toString());

                int i = 1;

                if (usuario.getNombre() != null) preparedStatement.setString(i++, usuario.getNombre());
                if (usuario.getApellido1() != null) preparedStatement.setString(i++, usuario.getApellido1());
                if (usuario.getApellido2() != null) preparedStatement.setString(i++, usuario.getApellido2());
                if (usuario.getUsername() != null) preparedStatement.setString(i++, usuario.getUsername());
                if (usuario.getEmail() != null) preparedStatement.setString(i++, usuario.getEmail());
                if (usuario.getPsswd() != null) preparedStatement.setString(i++, usuario.getPsswd());
                if (usuario.getIdMesa() != null) preparedStatement.setInt(i++, usuario.getIdMesa());
                if (usuario.getIdRol() != null) preparedStatement.setInt(i++, usuario.getIdRol());
                if (usuario.getIdCartera() != null) preparedStatement.setInt(i++, usuario.getIdCartera());
                if (usuario.getActive() != null)
                    preparedStatement.setInt(i++, BooleanUtils.booleanToInteger(usuario.getActive()));
                preparedStatement.setInt(i, usuario.getId());

                int updateRows = preparedStatement.executeUpdate();

                if (updateRows == 0) {
                    if (logger.isDebugEnabled()) logger.debug("Usuario no actualizado");
                    return usuario;
                }

                if (updateRows > 1) {
                    if (logger.isDebugEnabled()) logger.debug("Id de usuario duplicado");
                }

            } catch (SQLException sqlException) {
                logger.warn(sqlException.getMessage(), sqlException);
            } finally {
                JDBCUtils.closeStatement(preparedStatement);
            }
        }
        return null;
    }

    private Usuario loadNext(ResultSet resultSet) throws SQLException {
        int i = 1;
        Usuario usuario = new Usuario();
        usuario.setId(resultSet.getInt(i++));
        usuario.setNombre(resultSet.getString(i++));
        usuario.setApellido1(resultSet.getString(i++));
        usuario.setApellido2(resultSet.getString(i++));
        usuario.setUsername(resultSet.getString(i++));
        usuario.setEmail(resultSet.getString(i++));
        usuario.setPsswd(resultSet.getString(i++));
        usuario.setIdMesa(resultSet.getInt(i++));
        usuario.setIdRol(resultSet.getInt(i++));
        usuario.setIdCartera(resultSet.getInt(i++));
        usuario.setActive(BooleanUtils.integerToBoolean(resultSet.getInt(i)));
        return usuario;
    }
}
