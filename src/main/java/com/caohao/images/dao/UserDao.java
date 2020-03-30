package com.caohao.images.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caohao.images.pojo.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public interface UserDao extends BaseMapper<User> {
}
