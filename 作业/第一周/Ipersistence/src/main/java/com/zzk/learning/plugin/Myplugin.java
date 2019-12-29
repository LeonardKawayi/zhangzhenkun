package com.zzk.learning.plugin;


import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-29
 */
@Intercepts({ //可以定义多个@Signature
        @Signature(type = StatementHandler.class, //拦截哪个接口
        method = "prepare",//拦截方法名
        args = {Connection.class, Integer.class}) //方法参数
})
public class Myplugin implements Interceptor {

    /**
     * 方法描述 方法增强
     **/
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("方法增强");
        return invocation.proceed(); //执行原方法
    }

    /**
     * 方法描述 主要是为了将这个拦截器放入到一个拦截器链中
     **/
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
