package com.codegym.service.user;

import com.codegym.model.entity.User;
import com.codegym.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserService extends IGeneralService<User> {
    //tim kiem co ton tai trong DB khong
    Optional<User> findByUsername(String name);

    //kt xem user da co torng DB chua khi tao du lieu
    Boolean existsByUsername(String name);

    //kt xem email da co torng DB chua khi tao du lieu
    Boolean existsByEmail(String email);
    Page<User> findAllByUsernameContaining(String username, Pageable pageable);
}
