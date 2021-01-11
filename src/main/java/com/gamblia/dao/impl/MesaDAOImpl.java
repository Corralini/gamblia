package com.gamblia.dao.impl;

import com.gamblia.dao.spi.MesaDAO;
import com.gamblia.model.Mesa;

import java.sql.Connection;
import java.util.List;

public class MesaDAOImpl implements MesaDAO {
    @Override
    public Mesa findById(Connection connection, Integer id) {
        return null;
    }

    @Override
    public List<Mesa> findBy(Connection connection, Mesa mesa) {
        return null;
    }

    @Override
    public List<Mesa> findAll(Connection connection) {
        return null;
    }

    @Override
    public Mesa update(Connection connection, Mesa mesa) {
        return null;
    }

    @Override
    public void delete(Connection connection, Integer id) {

    }
}
