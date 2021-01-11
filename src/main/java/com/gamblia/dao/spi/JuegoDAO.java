package com.gamblia.dao.spi;

import com.gamblia.model.Juego;
import com.gamblia.model.Mesa;

import java.sql.Connection;
import java.util.List;

public interface JuegoDAO {

    Juego findById(Connection connection, Integer id);

    List<Juego> findBy(Connection connection, Juego juego);

    List<Juego> findAll(Connection connection);

    Juego update(Connection connection, Juego juego);

    void delete(Connection connection, Integer id);

}
