package com.gamblia.service.spi;

import com.gamblia.model.Operacion;
import com.gamblia.model.criteria.OperacionCriteria;

import java.sql.Connection;
import java.util.List;

public interface OperacionService {

    Operacion findById(Integer id);

    List<Operacion> findBy(OperacionCriteria operacionCriteria);

    List<Operacion> findAll();

    Operacion update(Operacion operacion);

}
