package com.caohao.images.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caohao.images.dao.UserDao;
import com.caohao.images.pojo.User;
import com.caohao.images.service.UserService;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CacheUtil {

    private static ConcurrentHashMap<String, User> userCache;
    public static ConcurrentHashMap<String, User> getInstance(){
        if (userCache==null){
            userCache=new ConcurrentHashMap<String,User>();
        }
        return userCache;
    }

    /**
     * 更新缓存
     */
    public static void updateUserCache(UserDao userDao){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",0);
        List<User> userList = userDao.selectList(queryWrapper);
        getInstance();
        for (User u:userList) {
            userCache.put(u.getUserName(),u);
        }
    }
    /**
     * 通过username查询user
     */
    public static User getUserByUserNameInUserCache(String userName){
        User user = userCache.get(userName);
        return user;
    }

}
