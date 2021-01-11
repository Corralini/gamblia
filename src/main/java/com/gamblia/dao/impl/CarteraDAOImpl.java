package com.gamblia.dao.impl;

import com.gamblia.dao.spi.CarteraDAO;
import com.gamblia.model.Cartera;

import java.sql.Connection;
import java.util.List;

public class CarteraDAOImpl implements CarteraDAO {
    @Override
    public Cartera findById(Connection connection, Integer id) {
        return null;
    }

    @Override
    public List<Cartera> findBy(Connection connection, Cartera cartera) {
        return null;
    }

    @Override
    public List<Cartera> findAll(Connection connection) {
        return null;
    }

    @Override
    public Cartera update(Connection connection, Cartera cartera) {
        return null;
    }
}
