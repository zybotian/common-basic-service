package org.open.source.factory.mapper;

import org.open.source.model.ViewObject;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 10:34
 */
public interface Decoratable {
    /**
     * 对行进行修饰
     */
    ViewObject decorate(ViewObject viewObject);
}
