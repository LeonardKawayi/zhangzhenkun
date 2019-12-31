package com.zzk.learning.dao;

import java.util.List;

import com.zzk.learning.entity.User;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-31
 */
public interface UserDao {

    List<User> findAll();

    User findByCondition(User user);
}
