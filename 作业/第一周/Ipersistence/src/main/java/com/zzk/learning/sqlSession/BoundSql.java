package com.zzk.learning.sqlSession;

import java.util.ArrayList;
import java.util.List;

import com.zzk.learning.util.ParameterMapping;

/**
 * @author zhangzhenkun <zhangzhenkun@kuaishou.com>
 * Created on 2019-12-26
 */
public class BoundSql {

    // 最后解析过的sql
    private String sqlText;

    // 解析出来的参数
    private List<ParameterMapping> parameterMapperList = new ArrayList<>();

    public BoundSql(String sqlText, List<ParameterMapping> parameterMapperList) {
        this.sqlText = sqlText;
        this.parameterMapperList = parameterMapperList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMapperList() {
        return parameterMapperList;
    }

    public void setParameterMapperList(List<ParameterMapping> parameterMapperList) {
        this.parameterMapperList = parameterMapperList;
    }
}
