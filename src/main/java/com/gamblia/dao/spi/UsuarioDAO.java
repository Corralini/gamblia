package com.gamblia.dao.spi;

import com.gamblia.model.Usuario;

import java.sql.Connection;
import java.util.List;

public interface UsuarioDAO {

    Usuario findById (Connection connection, Integer id);

    List<Usuario> findBy (Connection connection, Usuario usuario);

    List<Usuario> findAll (Connection connection);

    Usuario update (Connection connection, Usuario usuario);

}
