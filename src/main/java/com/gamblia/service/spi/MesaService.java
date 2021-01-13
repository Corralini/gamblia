package com.gamblia.service.spi;

import com.gamblia.model.Mesa;
import com.gamblia.model.criteria.MesaCriteria;

import java.util.List;

public interface MesaService {

    Mesa findById(Integer id);

    List<Mesa> findBy(MesaCriteria mesaCriteria);

    List<Mesa> findAll();

    Mesa update(Mesa mesa);

    void delete(Integer id);

    Mesa create(Mesa mesa);

}
