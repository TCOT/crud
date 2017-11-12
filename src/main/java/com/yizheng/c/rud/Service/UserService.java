package com.yizheng.c.rud.Service;

import com.yizheng.c.rud.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService{


    List<User> listUsers();

    User getUserById(Integer id);

    void deleteUser(Integer id);

    List<User> listUserByNameLike(String username);
}
