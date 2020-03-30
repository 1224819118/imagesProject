package com.caohao.images.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@TableName("tb_user")
@Data
public class User extends Model<User> implements Cloneable{

    @TableId(type = IdType.AUTO)
    Integer id;
    String userName;
    String password;
    String nickName;
    String tel;
    @TableLogic(value = "0",delval = "1")
    Integer status=0;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


    public User clone(){
        User user = new User();
        user.setId(this.id);
        user.setUserName(this.userName);
        user.setNickName(this.nickName);
        user.setTel(this.tel);
        return user;
    }
}
