package com.joker_yue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joker_yue.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author Joker
 * @version 1.0
 * @date 2023/8/13 16:57
 */
// 现在在所对应的Mapper上实现基本的接口 BaseMapper 即可
@Repository // 表示持久层
public interface UserMapper extends BaseMapper<User> {
    // 现在，所有的CRUD操作都已经完成了
    // 不需要像以前那样配置一大堆文件了！！！
}
