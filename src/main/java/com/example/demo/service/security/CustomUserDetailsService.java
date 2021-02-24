package com.example.demo.service.security;

import com.example.demo.entity.User;
import com.example.demo.enums.UserStatus;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService){
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username + " loadUserByUsername");
        User user = this.userService.findByUsernameAndStatus(username, UserStatus.ACTIVE);
        if(user == null) {
            System.out.println("jjjjsdlfjldksjf"+user.getUserStatus());
            throw new UsernameNotFoundException("Username not found");
        }
        return new UserPrincipal(user);
    }
}
