package com.caohao.images.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.caohao.images.pojo.User;
import com.caohao.images.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/loginCheck")
    public String loginCheck(String userName, String password, HttpSession session){
        User user = userService.loginCheck(userName, password);
        System.out.println(user);
        if (user==null){
            return "redirect:/login";
        }
        session.setAttribute("user",user);
        return "redirect:/index/1";
    }
    @RequestMapping("/registerHtml")
    public String registerHtml(){
        return "register";
    }

    /**
     * 注销
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        userService.logout(session);
        return "redirect:/login";
    }
    /**
     * 注册新用户
     */
    @RequestMapping("/register")
    public String register(User user){
        User register = userService.register(user);
       if (register==null){ return "redirect:/registerHTML";}
        return "redirect:/login";
    }
    /**
     * 修改绑定手机
     */
    @RequestMapping("/changeTel")
    public String changeEmail(String newTel,HttpSession session){
        User user = (User) session.getAttribute("user");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("tel",newTel).eq("id",user.getId());
        userService.update(updateWrapper);
        return "redirect:/login";
    }
    /**
     * 修改密码
     */
    @RequestMapping("/changePassword")
    public String changePassword(String newPassword,HttpSession session){
        User user = (User) session.getAttribute("user");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("password",newPassword).eq("id",user.getId());
        userService.update(updateWrapper);
        userService.logout(session);
        return "login";
    }

    /**
     * 跳转到关于页面
     */
    @RequestMapping("/about")
    public String about(){
        return "about";
    }
    /**
     * 跳转到个人中心页面
     */
    @RequestMapping("/myInfo")
    public String myInfo(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("user",user);
        return "myinfo";
    }

}
