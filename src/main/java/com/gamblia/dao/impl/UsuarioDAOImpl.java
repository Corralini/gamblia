package com.gamblia.dao.impl;

import com.gamblia.dao.spi.UsuarioDAO;
import com.gamblia.model.Usuario;

import java.sql.Connection;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    @Override
    public Usuario findById(Connection connection, Integer id) {
        return null;
    }

    @Override
    public List<Usuario> findBy(Connection connection, Usuario usuario) {
        return null;
    }

    @Override
    public List<Usuario> findAll(Connection connection) {
        return null;
    }

    @Override
    public Usuario update(Connection connection, Usuario usuario) {
        return null;
    }
}
