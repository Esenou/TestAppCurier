package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.Role;
import com.example.demo.enums.UserStatus;
import com.example.demo.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

/*public class UserModel {
    private Long id;
    private Role role;
    private String username;
    private String password;
    private UserStatus status;
    private Data createdDate;

}*/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select user FROM  User  user where user.username LIKE %?1%")
    List<User> findAllByUsername(String userName);

    @Query(value = "select user FROM  User  user where user.username LIKE %?1%")
    Page<User> findAllByName(String username, Pageable pageable);

    List<User> findAllByRoles(Role role);

    User findByUsername(String username);

    User findByUsernameAndUserStatus(String userName, UserStatus status);

//    @Query("select new com.example.demo.model.UserModel(user.id,user.roles,user.username,user.password,user.userStatus,user.createdDate) FROM User user where user.username like %?1% ")
//    Page<UserModel> findAllUserListWithPagination(Pageable pageable);
}
