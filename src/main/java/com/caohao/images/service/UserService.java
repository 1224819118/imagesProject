package com.caohao.images.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caohao.images.dao.UserDao;
import com.caohao.images.pojo.User;
import com.caohao.images.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service("userService")
public class UserService extends ServiceImpl<UserDao, User> {
    @Autowired
    UserDao userDao;
    /**
     * 登录
     */
    public User loginCheck(String userName,String password){
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("user_name",userName)
//                    .eq("password",password);
//        User user = userDao.selectOne(queryWrapper);
        if (CacheUtil.getInstance().size()<=0){
            System.out.println("---------------->update Cache");
            CacheUtil.updateUserCache(userDao);
        }
        User user = CacheUtil.getUserByUserNameInUserCache(userName);
        if (user==null||!user.getPassword().equals(password)){
            return null;
        }

        User returnUser = user.clone();
        System.out.println(returnUser);
        return returnUser;
    }
    /**
     * 注册
     */
    public User register(User user){
        System.out.println(user);
        userDao.insert(user);
        CacheUtil.updateUserCache(userDao);
        return user;
    }
    /**
     * 注销
     */
    public void logout(HttpSession session){
        session.removeAttribute("user");
    }
    /**
     * 删除用户
     */
//    public User deleteUser(String userName){
//        userDao.delete()
//    }
}
