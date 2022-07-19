package com.codegym.service.role;



import com.codegym.constant.Constant;
import com.codegym.model.entity.Role;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(Constant.RoleName name);

}
