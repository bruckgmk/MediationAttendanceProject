package edu.mum.service.Impl;

import edu.mum.domain.Role;
import edu.mum.repository.RoleRepository;
import edu.mum.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> listRoles() {
        return (List<Role>)roleRepository.findAll();
    }


}
