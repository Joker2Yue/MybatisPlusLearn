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

    // 测试插入
    @Test
    public void testInsert() {
        User user = new User();
        user.setName("joker_yue");
        user.setAge(20);
        user.setEmail("joker_yue@qq.com");

        int res = userMapper.insert(user);  //没有设置id，却自动生成id
        System.out.println(res);
    }

}
