package com.gamblia.service.spi;

import com.gamblia.model.Rol;
import com.gamblia.model.criteria.RolCriteria;

import java.sql.Connection;
import java.util.List;

public interface RolService {

    Rol findById(Integer id);

    List<Rol> findBy(RolCriteria rolCriteria);

    List<Rol> findAll();

    Rol update(Rol rol);
    
}
