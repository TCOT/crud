package com.yizheng.c.rud.Service;

import com.yizheng.c.rud.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 把提供业务功能的分一层，就是service
 * Service层是建立在DAO层之上的，建立了DAO层后才可以建立Service层，
 * 而Service层又是在Controller层之下的，因而Service层应该既调用DAO层的接口，
 * 又要提供接口给Controller层的类来进行调用，它刚好处于一个中间层的位置。
 */
public interface UserService{


    List<User> listUsers();

    User getUserById(Integer id);

    void deleteUser(Integer id);

    List<User> listUserByNameLike(String username);
}
