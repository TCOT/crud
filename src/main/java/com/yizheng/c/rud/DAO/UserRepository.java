package com.yizheng.c.rud.DAO;

import com.yizheng.c.rud.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer>{
    List<User> findByUsernameLike(String username);
}
