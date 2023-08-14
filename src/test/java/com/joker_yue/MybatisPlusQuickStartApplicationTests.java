package com.joker_yue;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joker_yue.mapper.UserMapper;
import com.joker_yue.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
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
    public void testOptimisticLockerSuccess() {
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
    public void testOptimisticLockerFail() {
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

    // 查询操作
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    // 测试批量查询
    @Test
    public void testSelectByBatchId() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    // 条件查询
    @Test
    public void testSelectByMap() {
        HashMap<String, Object> map = new HashMap<>();
        // 自定义查询的条件
        map.put("name", "joker_yue");
        map.put("age", 19);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    // 测试分页查询
    @Test
    public void testPage() {
        // 参数：当前页，页面大小
        Page<User> userPage = new Page<>(1, 5);  // 查询第一页，而且只要5个数据
        userMapper.selectPage(userPage, null);

        userPage.getRecords().forEach(System.out::println);
        System.out.println(userPage.getTotal());    // 总记录数
        System.out.println(userPage.getPages());     // 总页数
    }

    // 删除测试
    @Test
    public void testDeletedById(){
        int i = userMapper.deleteById(1690988409157672961L);
        System.out.println("Affect rows :"+i);
    }

    // 批量删除
    @Test
    public void testDeleteBatchIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(1690952684882145281L, 1690972032879316993L));
        System.out.println("Affect rows :"+i);

    }

    // 通过Map删除
    @Test
    public void testDeleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "joker_yue");
        int i = userMapper.deleteByMap(map);
        System.out.println("Affect rows :"+i);
    }

}
