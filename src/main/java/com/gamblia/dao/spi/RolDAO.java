package com.gamblia.dao.spi;

import com.gamblia.model.Rol;
import com.gamblia.model.criteria.RolCriteria;

import java.sql.Connection;
import java.util.List;

public interface RolDAO {

    Rol findById(Connection connection, Integer id);

    List<Rol> findBy(Connection connection, RolCriteria rolCriteria);

    List<Rol> findAll(Connection connection);

    Rol update(Connection connection, Rol rol);

}
