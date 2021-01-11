package com.gamblia.dao.impl;

import com.gamblia.dao.spi.JuegoDAO;
import com.gamblia.model.Juego;

import java.sql.Connection;
import java.util.List;

public class JuegoDAOImpl implements JuegoDAO {
    @Override
    public Juego findById(Connection connection, Integer id) {
        return null;
    }

    @Override
    public List<Juego> findBy(Connection connection, Juego juego) {
        return null;
    }

    @Override
    public List<Juego> findAll(Connection connection) {
        return null;
    }

    @Override
    public Juego update(Connection connection, Juego juego) {
        return null;
    }

    @Override
    public void delete(Connection connection, Integer id) {

    }
}
