package com.zzk.learning.test;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.zzk.learning.dao.UserDao;
import com.zzk.learning.entity.User;
import com.zzk.learning.resource.Resources;
import com.zzk.learning.sqlSession.SqlSession;
import com.zzk.learning.sqlSession.SqlSessionFactory;
import com.zzk.learning.sqlSession.SqlSessionFactoryBuilder;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-31
 */
public class IpersistenceTest {

    @Test
    public void test1 () throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

//        List<User> objectList = sqlSession.selectList("com.zzk.learning.dao.UserDao.findAll");
//        for (User user : objectList) {
//            System.out.println(user);
//        }

        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> all = mapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }
}
