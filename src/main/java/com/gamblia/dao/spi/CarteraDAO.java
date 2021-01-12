package com.gamblia.dao.spi;

import com.gamblia.model.Cartera;
import com.gamblia.model.criteria.CarteraCriteria;

import java.sql.Connection;
import java.util.List;

public interface CarteraDAO {

    Cartera findById(Connection connection, Integer id);

    List<Cartera> findBy(Connection connection, CarteraCriteria carteraCriteria);

    List<Cartera> findAll(Connection connection);

    Cartera update(Connection connection, Cartera cartera);

}
