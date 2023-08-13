package com.joker_yue;

import com.joker_yue.mapper.UserMapper;
import com.joker_yue.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusQuickStatApplicationTests {
    // 继承了BaseMapper。所有的方法都来自父类，我们可以直接拿来用
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        // 查询全部用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
