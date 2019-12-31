package com.zzk.learning.sqlSession;

import java.beans.PropertyVetoException;
import java.io.InputStream;

import org.dom4j.DocumentException;

import com.zzk.learning.config.Configuration;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-26
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build (InputStream inputStream)
            throws DocumentException, PropertyVetoException, ClassNotFoundException {
        // 1、解析配置文件，封装configuration

        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfiguration(inputStream);

        // 2、创建sqlSessionFactory
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        return sqlSessionFactory;
    }
}
