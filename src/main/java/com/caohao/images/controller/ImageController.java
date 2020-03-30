package com.caohao.images.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caohao.images.pojo.Image;
import com.caohao.images.pojo.User;
import com.caohao.images.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class ImageController {
    @Autowired
    ImageService imageService;
    @RequestMapping("/index/{page}")
    public String index(Model model, HttpSession session,@PathVariable Integer page){
        User user = (User) session.getAttribute("user");
        QueryWrapper<Image> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getId());
        IPage<Image> iPage = imageService.page(new Page<>(page,10), queryWrapper);
        model.addAttribute("user",user);
        model.addAttribute("pre",iPage.getCurrent()-1);
        model.addAttribute("images",iPage.getRecords());
        model.addAttribute("next",iPage.getCurrent()+1);
        model.addAttribute("total",iPage.getTotal());
        return "index";
    }
    @RequestMapping("/upload")
    public String upload(MultipartFile uploadFile,HttpSession session){
        User user = (User) session.getAttribute("user");
        String s = imageService.uploadImage(uploadFile,user.getId());
        System.out.println(s);
        return "redirect:/index/1";
    }
    @RequestMapping("/deleteImage/{imageId}")
    public String deleteImage(@PathVariable Integer imageId){
        imageService.deleteImage(imageId);
        return "redirect:/index/1";
    }
}
