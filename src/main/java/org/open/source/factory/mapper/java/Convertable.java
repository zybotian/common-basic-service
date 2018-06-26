package org.open.source.factory.mapper.java;

import java.util.List;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 10:25
 */
public interface Convertable {
    /**
     * 输入转换为行
     * @param source
     * @param dbType
     * @return
     */
    List<Line> convert(String source, DBType dbType);
}
