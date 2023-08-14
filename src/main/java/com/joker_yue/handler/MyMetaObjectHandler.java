package com.joker_yue.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Joker
 * @version 1.0
 * @date 2023/8/14 14:12
 */
@Slf4j      // 日志
@Component  // 把处理器加到Spring容器
public class MyMetaObjectHandler implements MetaObjectHandler {     // 元数据处理器
    // 插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill...");
        // default MetaObjectHandler setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        this.setFieldValByName("createTime", new Date(), metaObject);     // 参数分别为：字段名，填充信息，元对象
        this.setFieldValByName("updateTime", new Date(), metaObject);

    }

    // 更新式的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
