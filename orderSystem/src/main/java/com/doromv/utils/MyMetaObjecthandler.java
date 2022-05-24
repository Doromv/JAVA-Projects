package com.doromv.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author Doromv
 * @create 2022-05-24-8:13
 */
@Component
public class MyMetaObjecthandler implements MetaObjectHandler {

    @Autowired
    HttpServletRequest request;

    /**
     * 插入操作自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("createUser",request
                .getSession().getAttribute("employee"));
        metaObject.setValue("updateUser",request
                .getSession().getAttribute("employee"));

    }

    /**
     * 更新操作自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("updateUser",request.getSession().getAttribute("employee"));

    }
}
