package com.mictmr.seguranca.reposiroty;

import com.mictmr.seguranca.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

	public User findOneByUsername(String name);
	
	
}
