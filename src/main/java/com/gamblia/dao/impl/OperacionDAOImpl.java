package com.gamblia.dao.impl;

import com.gamblia.dao.spi.OperacionDAO;
import com.gamblia.model.Operacion;

import java.sql.Connection;
import java.util.List;

public class OperacionDAOImpl implements OperacionDAO {
    @Override
    public Operacion findById(Connection connection, Integer id) {
        return null;
    }

    @Override
    public List<Operacion> findBy(Connection connection, Operacion operacion) {
        return null;
    }

    @Override
    public List<Operacion> findAll(Connection connection) {
        return null;
    }

    @Override
    public Operacion update(Connection connection, Operacion operacion) {
        return null;
    }
}
