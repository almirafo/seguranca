package com.mictmr.seguranca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mictmr.seguranca.model.User;
import com.mictmr.seguranca.model.UserRole;
import com.mictmr.seguranca.model.Usuario;
import com.mictmr.seguranca.reposiroty.RoleRepo;
import com.mictmr.seguranca.reposiroty.UserRepo;
import com.mictmr.seguranca.reposiroty.UserRoleRepo;
import com.mictmr.seguranca.reposiroty.UsuarioRepo;

@Service
public class SecurityService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserRoleRepo userRoleRepo;
	
	@Autowired
	UsuarioRepo usuarioRepo;
	
	@Autowired
	RoleRepo roleRepo;
	
	
	public void save(Usuario usuario){
		
		 Usuario usuario2= usuarioRepo.findOneByEmail(usuario.getEmail());
		if (usuario2==null){
			User user = new User();
			
			user.setEnabled(1);
			user.setPassword(usuario.getSenha());
			user.setUsername(usuario.getNome());
			
			
			userRepo.save(user);
			
			UserRole userRole= new UserRole();
			userRole.setUsername(usuario.getNome());
			userRole.setRole("ROLE_USER");
			
			userRoleRepo.save(userRole);
			
			usuarioRepo.save(usuario);
			
		}
		
		
		
		
	}
	
	
	
	
	
}
