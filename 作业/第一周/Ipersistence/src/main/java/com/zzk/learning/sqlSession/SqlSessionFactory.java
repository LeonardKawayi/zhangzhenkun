package com.zzk.learning.sqlSession;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-26
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
