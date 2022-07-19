package com.codegym.service.role;


import com.codegym.constant.Constant;
import com.codegym.model.entity.Role;
import com.codegym.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(Constant.RoleName name) {
        return roleRepository.findByName(name);
    }
}
