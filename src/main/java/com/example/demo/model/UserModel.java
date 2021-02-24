package com.example.demo.model;

import com.example.demo.enums.Role;
import com.example.demo.enums.UserStatus;
import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.Data;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long id;
    private Role role;
    private String username;
    private String password;
    private UserStatus status;
    private Data createdDate;

}
