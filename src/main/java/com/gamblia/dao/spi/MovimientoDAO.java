package com.gamblia.dao.spi;

import com.gamblia.model.Cartera;
import com.gamblia.model.Mesa;
import com.gamblia.model.Movimiento;
import com.gamblia.model.criteria.MovimientoCriteria;

import java.sql.Connection;
import java.util.List;

public interface MovimientoDAO {

    Movimiento findById(Connection connection, Integer id);

    List<Movimiento> findBy(Connection connection, MovimientoCriteria movimientoCriteria);

    List<Movimiento> findAll(Connection connection);

    Movimiento update(Connection connection, Movimiento movimiento);

    Movimiento create(Connection connection, Movimiento movimiento);

}
