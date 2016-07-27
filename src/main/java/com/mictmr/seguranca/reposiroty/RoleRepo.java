package com.mictmr.seguranca.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mictmr.seguranca.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
