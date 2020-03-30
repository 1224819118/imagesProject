package com.caohao.images;

import com.caohao.images.pojo.User;
import com.caohao.images.service.UserService;
import com.caohao.images.util.FdfsUtil;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImagesApplicationTests {

    @Autowired
    FdfsUtil fdfsUtil;
    @Autowired
    UserService userService;
    @Autowired
    FastFileStorageClient fastFileStorageClient;
    @Test
    void contextLoads() {
        User user = userService.loginCheck("test", "123456");
        System.out.println(user);
    }
    @Test
    void contextLoads1() {
        fastFileStorageClient.deleteFile("group1","M00/00/00/rBEwmV6BSfaARYtpAAUR-s0cLvA085.jpg");


        // FdfsUtil.uploadImage();
    }

}
