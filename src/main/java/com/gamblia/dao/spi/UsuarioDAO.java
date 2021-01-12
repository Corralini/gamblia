package com.gamblia.dao.spi;

import com.gamblia.model.Usuario;
import com.gamblia.model.criteria.UsuarioCriteria;

import java.sql.Connection;
import java.util.List;

public interface UsuarioDAO {

    Usuario findById (Connection connection, Integer id);

    List<Usuario> findBy (Connection connection, UsuarioCriteria usuarioCriteria);

    List<Usuario> findAll (Connection connection);

    Usuario update (Connection connection, Usuario usuario);

}
