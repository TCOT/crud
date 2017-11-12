package com.yizheng.c.rud.Controller;

import com.yizheng.c.rud.DAO.UserRepository;
import com.yizheng.c.rud.Entity.User;
import com.yizheng.c.rud.Service.UserServiceImpl;
import com.yizheng.c.rud.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.sound.midi.Soundbank;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("{username}/search")
    public ModelAndView search(@PathVariable("username") String username){
        //System.out.println(username);
        List<User> searchList = userService.listUserByNameLike(username);
        //System.out.println(searchList);
        return new ModelAndView("user/list::#mainContainer","userModel",searchList);
    }

    @GetMapping
    public ModelAndView listUser(){
        //System.out.println("进入/users");
        List<User> allUser  = userService.listUsers();
        return new ModelAndView("user/list","userModel",allUser);
    }

    @PostMapping
    public ResponseEntity<Response> create(User user){
        userRepository.save(user);
        //userService.saveUser(user);
        return ResponseEntity.ok().body(new Response(true,"处理成功",user));
    }

    @GetMapping("/flush")
    public ModelAndView flushUser(){
        //System.out.println("flush maincontainer");
        List<User> allUser  = userService.listUsers();
        return new ModelAndView("user/list::#mainContainer","userModel",allUser);
    }

    @GetMapping("/add")
    public ModelAndView createForm(Model model){
        //System.out.println("进入/user/add");
        model.addAttribute("user",new User(null,null,null));
        return new ModelAndView("user/add","userModel",model);
    }

    @GetMapping("/{id}/edit")
    public ModelAndView modifyForm(@PathVariable("id") Integer id,Model model){
        //System.out.println("----------");
        //System.out.println(id);
        User user = userService.getUserById(id);
        //System.out.println(user);
        model.addAttribute(user);
        return new ModelAndView("user/edit","userModel",model);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body(new Response(true,"处理成功"));
    }
}
