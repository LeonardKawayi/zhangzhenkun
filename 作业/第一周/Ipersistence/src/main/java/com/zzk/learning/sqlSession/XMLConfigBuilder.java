package com.zzk.learning.sqlSession;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zzk.learning.config.Configuration;
import com.zzk.learning.resource.Resources;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-26
 */
public class XMLConfigBuilder {

    private Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    public Configuration parseConfiguration (InputStream inputStream)
            throws DocumentException, PropertyVetoException, ClassNotFoundException {
        Document document = new SAXReader().read(inputStream);

        // 数据库的连接信息
        Element rootElement = document.getRootElement();
        List<Element> propertyElements = rootElement.selectNodes("//property");

        Properties properties = new Properties();
        for (Element element : propertyElements) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.setProperty(name, value);
        }

        // 连接池
        ComboPooledDataSource pool = new ComboPooledDataSource();
        pool.setDriverClass(properties.getProperty("driverClass"));
        pool.setJdbcUrl(properties.getProperty("jdbcUrl"));
        pool.setUser(properties.getProperty("username"));
        pool.setPassword(properties.getProperty("password"));

        configuration.setDataSource(pool);

        // mapper部分
        List<Element> mapperList = rootElement.selectNodes("//mapper");


        for (Element element : mapperList) {
            String path = element.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsSteam(path);
            XMLMapperBuilder mapperBuilder = new XMLMapperBuilder(configuration);
            mapperBuilder.parse(resourceAsStream);
        }

        return configuration;
    }
}
