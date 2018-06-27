package org.open.source.factory.mapper.java;

import java.util.List;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 10:34
 */
public interface Decoratable {
    /**
     * 对行进行修饰
     */
    List<Line> decorate(List<Line> lines);
}
