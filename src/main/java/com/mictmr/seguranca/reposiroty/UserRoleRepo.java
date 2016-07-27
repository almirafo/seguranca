package com.mictmr.seguranca.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mictmr.seguranca.model.UserRole;


public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
	
}
