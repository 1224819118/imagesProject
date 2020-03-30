package com.caohao.images.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caohao.images.pojo.Image;
import com.caohao.images.pojo.User;
import org.springframework.stereotype.Repository;

@Repository("imageDao")
public interface ImageDao extends BaseMapper<Image> {
}
