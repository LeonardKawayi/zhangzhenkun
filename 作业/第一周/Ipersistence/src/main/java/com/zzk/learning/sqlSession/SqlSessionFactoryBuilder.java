package com.zzk.learning.sqlSession;

import java.io.InputStream;

import com.zzk.learning.config.Configuration;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-26
 */
public class SqlSessionFactoryBuilder {

    private Configuration configuration;

    public SqlSessionFactoryBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSessionFactory build (InputStream inputStream) {
        // 1、解析配置文件，封装configuration

        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(configuration);

        // 2、创建sqlSessionFactory
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        return sqlSessionFactory;
    }
}
