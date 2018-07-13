package org.open.source.factory.mapper;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 14:51
 */
@AllArgsConstructor
public enum MapperType {
    /**
     * create table sql转成java对象
     */
    SQL_TO_JAVA_OBJ("sql_to_java_obj"),
    /**
     * create table sql转成dao定义
     */
    SQL_TO_DAO_DEF("sql_to_dao_def"),;

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
