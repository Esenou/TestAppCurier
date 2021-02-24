package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.enums.UserStatus;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long addUser(UserModel userModel) {
        User user = new User();
        user.setUsername(userModel.getUsername());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setRoles(userModel.getRole());

        //user.setUserStatus(userModel.getStatus());
        return userRepository.save(user).getId();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUsernameAndStatus(String username, UserStatus status) {
        return userRepository.findByUsernameAndUserStatus(username, status);
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findByName(String name) {
        if (name != null) {
            return userRepository.findAllByUsername(name);
        }
        else return userRepository.findAll();
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);

        return this.userRepository.findAll(pageable) ;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findAllByName(String search, Pageable pageable) {
        return userRepository.findAllByName(search, pageable);
    }

    @Override
    public List<User> findAllByRole(Role role) {
        return userRepository.findAllByRoles(role);
    }

    @Override
    public void updateUser(Long id, UserModel userModel) {
        User user = findById(id);
        user.setUsername(userModel.getUsername());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setUserStatus(userModel.getStatus());
        user.setRoles(userModel.getRole());
        userRepository.save(user);
    }


    @Override
    public UserModel userModel(User user) {
        UserModel userModel = UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .status(user.getUserStatus())
                .role(user.getRoles()).build();
        return userModel;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found!!"));
    }
}
