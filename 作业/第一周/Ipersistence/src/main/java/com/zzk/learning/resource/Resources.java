package com.zzk.learning.resource;

import java.io.InputStream;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-26
 */
public class Resources {

    public static InputStream getResourceAsSteam (String path) {
        InputStream inputStream = Resources.class.getClassLoader().getResourceAsStream(path);

        return inputStream;
    }
}
