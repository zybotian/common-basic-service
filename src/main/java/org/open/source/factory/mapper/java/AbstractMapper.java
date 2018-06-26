package org.open.source.factory.mapper.java;

import java.util.List;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 15:04
 */
public abstract class AbstractMapper implements Convertable, Decoratable {

    public AbstractMapper() {
        super();
    }

    public String process(String source, DBType dbType) {
        // step 1: 转换
        List<Line> lines = convert(source, dbType);
        // step 2: 修饰
        return decorate(lines);
    }
}
