package org.open.source.factory.mapper.java;

import org.open.source.model.DBType;
import org.open.source.model.ViewObject;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 15:04
 */
public abstract class AbstractMapper implements Convertable, Decoratable {

    public AbstractMapper() {
        super();
    }

    public ViewObject process(String source, DBType dbType) {
        // step 1: 转换
        ViewObject viewObject = convert(source, dbType);
        // step 2: 修饰
        return decorate(viewObject);
    }
}
