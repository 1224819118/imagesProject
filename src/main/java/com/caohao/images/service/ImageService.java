package com.caohao.images.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caohao.images.dao.ImageDao;
import com.caohao.images.dao.UserDao;
import com.caohao.images.pojo.Image;
import com.caohao.images.pojo.User;
import com.caohao.images.util.FdfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service("imageService")
public class ImageService extends ServiceImpl<ImageDao, Image> {
    @Autowired
    private ImageDao imageDao;
    @Autowired
    FdfsUtil fdfsUtil;
    /**
     * 添加图片为自己的占位图
     */
    public String uploadImage(MultipartFile file,Integer userId){
        String uri = "文件上传失败";
        try {
            uri = fdfsUtil.perfixMultipartFileToUploadImage(file);
        } catch (IOException e) {
            System.out.println("文件上传失败");
            return "文件上传失败";
        }
        Image image = new Image();
        image.setUri(uri);
        image.setUserId(userId);
        imageDao.insert(image);
        return uri;

    }
    /**
     * 展示自己全部的占位图
     */
    /**
     * 展示所有的图片
     */
    /**
     * 删除某个占位图
     */
    @Transactional
    public boolean deleteImage(Integer imageId){
        Image image = imageDao.selectById(imageId);
        boolean deleteImage = fdfsUtil.deleteImage(image.getUri());
        UpdateWrapper<Image> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("uri",image.getUri());
        int delete = imageDao.delete(updateWrapper);

        return deleteImage;
    }

}
