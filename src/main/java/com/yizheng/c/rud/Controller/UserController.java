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

/**
 * Controllers是控制中心，该层负责具体的业务模块流程的控制，
 * 在此层里面要调用Service层的接口来控制业务流程
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     *使用@Autowired注解，这个叫做依赖注入，通过注入的方式获得依赖
     */
    @Autowired
    private UserRepository userRepository;

    //从数据库调出数据渲染页面并返回
    @GetMapping
    public ModelAndView listUser(){
        List<User> allUser  = userService.listUsers();
        return new ModelAndView("user/list","userModel",allUser);
    }

    //接受POSTURL,进行操作后返回状态给AJAX
    @PostMapping
    public ResponseEntity<Response> create(User user){
        /**
         * 要完成这个保存的行为，需要在controller层中使用其他对象的方法，一旦这样的事情发生，
         * 我们便称controller层依赖于另一个对象，只要两个对象之间存在一方依赖于另一方的关系，
         * 那么我们就称这两个对象之间存在耦合。我们已经把DAO层的对象注入到了Service层，
         * 把他注入到controller层的话，controller层依赖DAO层,破坏了模块的单向依赖，产生了不必要的耦合，
         * 违背了软件设计“高内聚，低耦合”的设计原则，是应该被规避的行为。
         */
        //userRepository.save(user);
        userService.save(user);
        return ResponseEntity.ok().body(new Response(true,"处理成功",user));
    }

    //渲染局部页面数据并返回给AJAX刷新
    @GetMapping("/flush")
    public ModelAndView flushUser(){
        List<User> allUser  = userService.listUsers();
        return new ModelAndView("user/list::#mainContainerRepleace","userModel",allUser);
    }

    //模糊搜索后返回渲染后的局部页面,并由AJAX刷新
    @GetMapping("{username}/search")
    public ModelAndView search(@PathVariable("username") String username){
        List<User> searchList = userService.listUserByNameLike(username);
        return new ModelAndView("user/list::#mainContainer","userModel",searchList);
    }

    //返回渲染后的局部页面并交由AJAX刷新
    @GetMapping("/add")
    public ModelAndView createForm(Model model){
        model.addAttribute("user",new User(null,null,null));
        return new ModelAndView("user/add","userModel",model);
    }

    //返回渲染后的局部页面后交由AJAX刷新
    @GetMapping("/{id}/edit")
    public ModelAndView modifyForm(@PathVariable("id") Integer id,Model model){
        User user = userService.getUserById(id);
        model.addAttribute(user);
        return new ModelAndView("user/edit","userModel",model);
    }

    //删除后返回状态给AJAX进行下一步操作
    @DeleteMapping("{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body(new Response(true,"处理成功"));
    }
}
