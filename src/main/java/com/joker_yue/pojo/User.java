package com.joker_yue.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Joker
 * @version 1.0
 * @date 2023/8/13 16:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;        // 对应主键中的id（uuid，自增id，雪花算法，redis、zookeeper）
    private String name;
    private Integer age;
    private String email;
}
