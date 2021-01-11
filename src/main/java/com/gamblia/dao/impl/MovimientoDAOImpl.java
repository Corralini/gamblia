package com.gamblia.dao.impl;

import com.gamblia.dao.spi.MovimientosDAO;
import com.gamblia.model.Movimiento;

import java.sql.Connection;
import java.util.List;

public class MovimientoDAOImpl implements MovimientosDAO {
    @Override
    public Movimiento findById(Connection connection, Integer id) {
        return null;
    }

    @Override
    public List<Movimiento> findBy(Connection connection, Movimiento movimiento) {
        return null;
    }

    @Override
    public List<Movimiento> findAll(Connection connection) {
        return null;
    }

    @Override
    public Movimiento update(Connection connection, Movimiento movimiento) {
        return null;
    }
}
