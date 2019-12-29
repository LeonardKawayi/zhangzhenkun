package com.zzk.learning.executor;

import java.util.List;

import com.zzk.learning.config.Configuration;
import com.zzk.learning.config.MappedStatement;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-26
 */
public interface Executor {

    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object[] param) throws Exception;
}
