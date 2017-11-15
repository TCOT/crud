package com.yizheng.c.rud.Service;

import com.yizheng.c.rud.DAO.UserRepository;
import com.yizheng.c.rud.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public  User save(User user){
        return userRepository.save(user);
    }

    @Transactional
    public User getUserById(Integer id){
        return userRepository.getOne(id);
    }

    @Transactional
    public void deleteUser(Integer id){
        userRepository.delete(id);
    }

    @Transactional
    public List<User> listUserByNameLike(String username){
        username = "%" + username + "%";
        List<User> users = userRepository.findByUsernameLike(username);
        return users;
    }
}
