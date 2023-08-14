package com.joker_yue.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Joker
 * @version 1.0
 * @date 2023/8/13 16:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;        // 对应主键中的id（uuid，自增id，雪花算法，redis、zookeeper）
    private String name;
    private Integer age;
    private String email;
    // 字段添加填充内容
    @TableField(fill = FieldFill.INSERT)           // 在插入的时候执行操作
    private Date createTime;    // 创建时间
    @TableField(fill = FieldFill.INSERT_UPDATE)    // 在插入和更新的时候操作
    private Date updateTime;    // 更新时间

    @Version    // 代表一个乐观锁注解
    private Integer version;

}
