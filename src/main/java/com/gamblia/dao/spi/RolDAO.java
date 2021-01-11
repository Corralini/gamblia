package com.gamblia.dao.spi;

import com.gamblia.model.Rol;

import java.sql.Connection;
import java.util.List;

public interface RolDAO {

    Rol findById(Connection connection, Integer id);

    List<Rol> findBy(Connection connection, Rol rol);

    List<Rol> findAll(Connection connection);

    Rol update(Connection connection, Rol rol);

}
