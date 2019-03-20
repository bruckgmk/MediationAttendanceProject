package edu.mum.service;

import edu.mum.domain.Role;

import java.util.List;

public interface RoleService {
    public Role save(Role role);
    public List<Role> listRoles();


}
