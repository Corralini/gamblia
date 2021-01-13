package com.gamblia.service.spi;

import com.gamblia.model.Cartera;
import com.gamblia.model.criteria.CarteraCriteria;

import java.util.List;

public interface CarteraService {

    Cartera findById(Integer id);

    List<Cartera> findBy(CarteraCriteria carteraCriteria);

    List<Cartera> findAll();

    Cartera update(Cartera cartera);

    Cartera create(Cartera cartera);

}
