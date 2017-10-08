package com.shera.shikkhok.sherashikkhok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shera.shikkhok.sherashikkhok.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByRole(String role);
	
}
