package com.caohao.images.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caohao.images.pojo.Image;
import com.caohao.images.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class PublicController {
    @Autowired
    ImageService imageService;

    @RequestMapping("/index/{page}")
    public String publicIndex(Model model, @PathVariable Integer page){
        if (page==null){
            page=1;
        }
        Page<Image> iPage = imageService.page(new Page<>(page, 10));
        model.addAttribute("pre",iPage.getCurrent()-1);
        model.addAttribute("images",iPage.getRecords());
        model.addAttribute("next",iPage.getCurrent()+1);
        model.addAttribute("total",iPage.getTotal());

        return "public_index";
    }
}
