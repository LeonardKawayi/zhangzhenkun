package com.zzk.learning.executor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.zzk.learning.config.Configuration;
import com.zzk.learning.config.MappedStatement;
import com.zzk.learning.sqlSession.BoundSql;
import com.zzk.learning.util.GenericTokenParser;
import com.zzk.learning.util.ParameterMapping;
import com.zzk.learning.util.ParameterMappingTokenHandler;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-26
 */
public class SimpleExecutor implements Executor {

    private Connection connection = null;

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object[] param)
            throws Exception {

        connection = configuration.getDataSource().getConnection();

        /**
         * 方法描述  select * from user where id = #{id} and username = #{username}
         **/
        String sql = mappedStatement.getSql();

        // 对sql处理
        BoundSql boundSql = getBoundSql(sql);

        // select * from where id = ? and username = ?
        String finalSql = boundSql.getSqlText();

        // 获取输入参数
        Class<?> parameterType = mappedStatement.getParameterType();

        PreparedStatement preparedStatement = connection.prepareStatement(finalSql);
        List<ParameterMapping> parameterMapperList = boundSql.getParameterMapperList();

        for (int i = 0; i < parameterMapperList.size(); i++) {
            ParameterMapping parameterMapping = parameterMapperList.get(i);
            String name = parameterMapping.getContent();
            Field declaredField = parameterType.getDeclaredField(name); declaredField.setAccessible(true);
            Object o = declaredField.get(param[0]);
            preparedStatement.setObject(i+1,o);
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        Class<?> resultType = mappedStatement.getResultType();

        List<E> results = new ArrayList<E>();

        // 封装结果机
        while (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            E o = (E) resultType.newInstance();

            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                // 属性名
                String columnName = metaData.getColumnName(i);
                // 属性值
                Object value = resultSet.getObject(columnName);
                // 创建属性描述器，为属性写生成读写放大
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultType);

                // 获取写方法
                Method writeMethod = propertyDescriptor.getWriteMethod();
                //向实体类中写入值
                writeMethod.invoke(o, value);
            }
            results.add(o);
        }
        return results;
    }

    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new
                ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        String parse = genericTokenParser.parse(sql);
        List<ParameterMapping> parameterMappings =
                parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parse, parameterMappings);
        return boundSql;
    }
}
