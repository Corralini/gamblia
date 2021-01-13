package com.gamblia.service.spi;

import com.gamblia.model.Movimiento;
import com.gamblia.model.criteria.MovimientoCriteria;

import java.util.List;

public interface MovimientoService {

    Movimiento findById(Integer id);

    List<Movimiento> findBy(MovimientoCriteria movimientoCriteria);

    List<Movimiento> findAll();

    Movimiento update(Movimiento movimiento);

    Movimiento create(Movimiento movimiento);

}
