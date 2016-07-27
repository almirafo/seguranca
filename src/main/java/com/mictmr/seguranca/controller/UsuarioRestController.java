package com.mictmr.seguranca.controller;


import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.mictmr.seguranca.model.Usuario;
import com.mictmr.seguranca.model.UsuarioRest;
import com.mictmr.seguranca.reposiroty.UsuarioRepo;

@RestController
public class UsuarioRestController {

	
	@Autowired
	UsuarioRepo usuarioRepo;
	
	@RequestMapping(name ="/usuarioRest" )
	public  Hashtable<String,UsuarioRest> pesquisaUsuarios(){
		Hashtable<String,UsuarioRest> hUsuario = new Hashtable<>();
		List<Usuario> usuarios=   usuarioRepo.findAll();
		for(Usuario usuario:usuarios){
			UsuarioRest usuarioRest = new UsuarioRest();
			usuarioRest.setEmail(usuario.getEmail());
			usuarioRest.setIdUsuario(usuario.getIdUsuario());
			usuarioRest.setNome(usuario.getNome());
			usuarioRest.setStatus(usuario.getStatus().getDescricao());
			hUsuario.put(usuario.getNome(), usuarioRest);
		}

		return hUsuario;
	}
	
	
}
