package com.joker_yue;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joker_yue.mapper.UserMapper;
import com.joker_yue.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author Joker
 * @version 1.0
 * @date 2023/8/14 19:36
 */
@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;

    // greater or equal to 大于等于
    @Test
    void test1() {
        // 查询name不为空，且邮箱不为空的。年龄大于等于20
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age", 20);
        userMapper.selectList(wrapper).forEach(System.out::println); // 和刚才学习的map对比
    }

    // equals 等于
    @Test
    public void test2(){
        // 查询名字为Joker的
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","Joker");
        System.out.println(userMapper.selectOne(wrapper));  // 查询一个数据，出现多个结果使用List或者Map
    }

    // between 区间
    @Test
    public void test3(){
        // 查询年龄从21-30岁的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",21,30);   // 区间
        Long count = userMapper.selectCount(wrapper);   // 查询结果数
        System.out.println(count);

    }

    // Like 模糊查询
    @Test
    public void test4(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        // likeRight,likeLeft：表示通配的位置。左为%t，右为t%
        wrapper.notLike("name","t")
                .likeRight("email","t");    // t%

        List<Map<String,Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    // 内查询
    @Test
    public void test5(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        // id在子查询中查出来
        wrapper.inSql("id","select id from user where id<3");

        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }

    // 查询排序
    @Test
    public void test6(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 通过id进行排序
        wrapper.orderByDesc("id");

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);

    }

}
