package com.zzk.learning.sqlSession;

import java.util.List;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-26
 */
public interface SqlSession {

    <E> List<E> selectList(String statementId, Object... params) throws Exception;

    <T> T selectOne(String statementId, Object... params) throws Exception;

    <T> T getMapper(Class<?> mapperClass);
}
