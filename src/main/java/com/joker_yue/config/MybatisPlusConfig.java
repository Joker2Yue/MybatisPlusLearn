package com.joker_yue.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Joker
 * @version 1.0
 * @date 2023/8/14 15:30
 */
@MapperScan("com.joker_yue.mapper")     // 改写到这里，是因为这个类管理MybatisPlus的配置
@EnableTransactionManagement    // 自动管理事务
@Configuration  // 配置类
public class MybatisPlusConfig {
    // 注册乐观锁插件


    /* 旧版
     @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
     */

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

}
