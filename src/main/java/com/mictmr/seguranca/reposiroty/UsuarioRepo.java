package com.mictmr.seguranca.reposiroty;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mictmr.seguranca.model.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, Long> {

	Usuario findByEmailAndSenha(String email, String senha);

}
