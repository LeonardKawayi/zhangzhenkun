package com.zzk.learning.resource;

import java.io.InputStream;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-26
 */
public class Resources {

    public static InputStream getResourceAsStream (String path) {
        InputStream inputStream = Resources.class.getResourceAsStream(path);

        return inputStream;
    }
}
