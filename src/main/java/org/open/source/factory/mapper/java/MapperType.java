package org.open.source.factory.mapper.java;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 14:51
 */
@AllArgsConstructor
public enum MapperType {
    SQL_TO_JAVA_OBJ("sql_to_java_obj"),
    JAVA_OBJ_TO_SQL("java_obj_to_sql"),;

    private static Map<String, MapperType> valueMap = new HashMap<>();

    static {
        for (MapperType mapperType : MapperType.values()) {
            valueMap.put(mapperType.value, mapperType);
        }
    }

    private String value;

    public static MapperType findByValue(String value) {
        return valueMap.get(value);
    }
}
