package com.amane.mybatistest.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.annotations.ResultMap;

@TableName(value = "t_user")
@Data
@ToString
public class User extends Model<User> {
    @TableId
    private Integer id;

    private Integer age;

    private String name;

    private String address;
}
