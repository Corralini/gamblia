package com.gamblia.service.spi;

import com.gamblia.model.Juego;
import com.gamblia.model.criteria.JuegoCriteria;

import java.util.List;

public interface JuegoService {
    Juego findById(Integer id);

    List<Juego> findBy(JuegoCriteria juegoCriteria);

    List<Juego> findAll();

    Juego update(Juego juego);

    void delete(Integer id);
}
