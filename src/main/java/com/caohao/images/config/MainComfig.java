package com.caohao.images.config;

import com.caohao.images.util.FdfsUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainComfig {

    @Bean("fdfsUtil")
    public FdfsUtil fdfsUtil(){
        return new FdfsUtil();
    }
}
