package org.open.source.factory.mapper.java;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 11:32
 */
@Data
@Accessors(chain = true)
public class Line {
    /**
     * 数据类型
     */
    private String type;
    /**
     * 字段名称
     */
    private String name;
}
