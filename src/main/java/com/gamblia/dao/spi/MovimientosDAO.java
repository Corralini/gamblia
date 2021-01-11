package com.gamblia.dao.spi;

import com.gamblia.model.Movimiento;
import com.gamblia.model.Operacion;

import java.sql.Connection;
import java.util.List;

public interface MovimientosDAO {

    Movimiento findById(Connection connection, Integer id);

    List<Movimiento> findBy(Connection connection, Movimiento movimiento);

    List<Movimiento> findAll(Connection connection);

    Movimiento update(Connection connection, Movimiento movimiento);

}
