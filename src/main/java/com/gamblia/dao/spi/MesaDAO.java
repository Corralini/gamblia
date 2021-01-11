package com.gamblia.dao.spi;

import com.gamblia.model.Mesa;
import com.gamblia.model.Movimiento;

import java.sql.Connection;
import java.util.List;

public interface MesaDAO {

    Mesa findById(Connection connection, Integer id);

    List<Mesa> findBy(Connection connection, Mesa mesa);

    List<Mesa> findAll(Connection connection);

    Mesa update(Connection connection, Mesa mesa);

    void delete(Connection connection, Integer id);

}
