package com.gamblia.dao.impl;

import com.gamblia.dao.spi.RolDAO;
import com.gamblia.model.Rol;

import java.sql.Connection;
import java.util.List;

public class RolDAOImpl implements RolDAO {
    @Override
    public Rol findById(Connection connection, Integer id) {
        return null;
    }

    @Override
    public List<Rol> findBy(Connection connection, Rol rol) {
        return null;
    }

    @Override
    public List<Rol> findAll(Connection connection) {
        return null;
    }

    @Override
    public Rol update(Connection connection, Rol rol) {
        return null;
    }
}
