package org.open.source.factory.mapper;

import org.open.source.model.DBType;
import org.open.source.model.ViewObject;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 10:25
 */
public interface Convertable {
    /**
     * 输入转换为行
     */
    ViewObject convert(String source, DBType dbType);
}
