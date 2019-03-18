package edu.mum.repository.Impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.domain.Role;

@Repository("roleRepository")
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByRole(String role);

}
