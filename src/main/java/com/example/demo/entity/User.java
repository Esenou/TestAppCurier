package com.example.demo.entity;


import com.example.demo.enums.Role;
import com.example.demo.enums.UserStatus;
import com.example.demo.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;



    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "role")
    private Role roles = Role.Admin;

    /**
     * Username
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * Password
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * User status
     * ACTIVE / NOT_ACTIVE / DELETED
     */
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "status")
    private UserStatus userStatus = UserStatus.ACTIVE;

    /**
     * Created Date
     */
    @CreationTimestamp
    @Column(name = "create_date")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private Date createdDate;


}
