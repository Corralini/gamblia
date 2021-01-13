package com.gamblia.dao.spi;

import com.gamblia.model.Mesa;
import com.gamblia.model.criteria.MesaCriteria;

import java.sql.Connection;
import java.util.List;

public interface MesaDAO {

    Mesa findById(Connection connection, Integer id);

    List<Mesa> findBy(Connection connection, MesaCriteria mesaCriteria);

    List<Mesa> findAll(Connection connection);

    Mesa update(Connection connection, Mesa mesa);

    void delete(Connection connection, Integer id);

    Mesa create(Connection connection, Mesa mesa);

}
