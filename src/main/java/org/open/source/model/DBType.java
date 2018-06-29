package org.open.source.model;

import com.alibaba.druid.util.JdbcConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 11:12
 */
@Getter
@AllArgsConstructor
public enum DBType {
    /**
     * mysql
     */
    MYSQL(JdbcConstants.MYSQL),
    /**
     * 默认类型设置为mysql
     */
    DEFAULT(JdbcConstants.MYSQL),;

    private String value;
}
