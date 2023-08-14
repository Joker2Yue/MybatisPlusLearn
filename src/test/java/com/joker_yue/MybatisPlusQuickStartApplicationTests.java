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

        int res = userMapper.insert(user);  // 没有设置id，却自动生成id
        System.out.println(res);
    }

    // 测试更新
    @Test
    public void testUpdate() {
        User user = new User();
        // 想改什么就写上去，不改就不写。会自动拼接sql
        user.setId(1690952684882145281L);
        user.setName("joker_yue");
        user.setAge(19);
        user.setEmail("joker_yue@qq.com");

        // 注意，虽然名字为byId，但是参数为一个对象
        int i = userMapper.updateById(user);
        System.out.println("affect rows:" + i);
    }


    // 测试乐观锁-修改成功
    @Test
    public void testOptimisticLockerSuccess(){
        // 查询用户信息
        User user = userMapper.selectById(1L);
        // 修改用户信息
        user.setName("Joker九歌");
        user.setEmail("Joker_yue@qq.com");
        // 执行更新操作
        userMapper.updateById(user);
    }

    // 测试乐观锁-修改失败
    @Test
    public void testOptimisticLockerFail(){
        // 线程1
        User user = userMapper.selectById(1L);
        user.setName("Joker111");
        user.setEmail("Joker_yue@qq.com");

        // 模拟另外一个线程执行了插队操作
        User user2 = userMapper.selectById(1L);
        user2.setName("Joker222");
        user2.setEmail("Joker_yue@qq.com");

        userMapper.updateById(user2);

        // 如果失败，可以使用自旋锁来进行多次提交尝试
        userMapper.updateById(user);    // 如果没有乐观锁，就会覆盖插队线程的值
    }

}
