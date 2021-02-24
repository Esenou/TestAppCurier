package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.enums.UserStatus;
import com.example.demo.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface UserService {
    Long addUser(UserModel userModel);

    User findByUsername(String username);

    User findByUsernameAndStatus(String username, UserStatus status);

    List<User> findAll();

    User findById(Long id);

    void updateUser(Long id, UserModel user);

    void deleteUser(Long id);

    List<User> findByName(String name);

    Page<User> findPaginated(int pageNo, int pageSize);

    Page<User> findAll(Pageable pageable);

    Page<User> findAllByName(String search, Pageable pageable);

    List<User> findAllByRole(Role role);


    UserModel userModel(User id);
}
