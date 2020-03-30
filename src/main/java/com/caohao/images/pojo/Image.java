package com.caohao.images.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tb_image")
public class Image extends Model<Image> {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer userId;
    String uri;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
