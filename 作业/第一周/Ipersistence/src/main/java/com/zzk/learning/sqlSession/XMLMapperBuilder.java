package com.zzk.learning.sqlSession;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zzk.learning.config.Configuration;
import com.zzk.learning.config.MappedStatement;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-26
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException, ClassNotFoundException {
        Document document = new SAXReader().read(inputStream);

        Element rootElement = document.getRootElement();
        List<Element> selectNodes = rootElement.selectNodes("select");
        String namespace = rootElement.attributeValue("namespace");

        for (Element element : selectNodes) {
            String id = element.attributeValue("id");
            String parameterType = element.attributeValue("parameterType");
            String resultType = element.attributeValue("resultType");

            // 输入参数class
            Class<?> parameterTypeClass = getClassType(parameterType);
            Class<?> resultTypeClass = getClassType(resultType);

            // statementId = namespace + "." + id
            String statementId = namespace + "." + id;

            // sql
            String sql = element.getTextTrim();

            // 封装mappedstatement
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(statementId);
            mappedStatement.setParameterType(parameterTypeClass);
            mappedStatement.setResultType(resultTypeClass);
            mappedStatement.setSql(sql);

            configuration.getMappedStatementMap().put(statementId, mappedStatement);
        }
    }


    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType == null || "".equals(parameterType)) {
            return null;
        }

        Class<?> aClass = Class.forName(parameterType);
        return aClass;
    }
}
