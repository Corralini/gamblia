package com.gamblia.service.spi;

import com.gamblia.model.Usuario;
import com.gamblia.model.criteria.UsuarioCriteria;

import java.util.List;

public interface UsuarioService {

    Usuario findById(Integer id);

    List<Usuario> findBy(UsuarioCriteria usuarioCriteria);

    List<Usuario> findAll();

    Usuario update(Usuario usuario);

    Usuario login(String user, String psswd);

}
