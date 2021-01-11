package com.gamblia.dao.spi;

import com.gamblia.model.Operacion;

import java.sql.Connection;
import java.util.List;

public interface OperacionDAO {

    Operacion findById(Connection connection, Integer id);

    List<Operacion> findBy(Connection connection, Operacion operacion);

    List<Operacion> findAll(Connection connection);

    Operacion update(Connection connection, Operacion operacion);

}
