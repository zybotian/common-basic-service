package org.open.source.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 13:30
 */
public class DataTypeMapper {

    private static Map<String, String> sql2Java = new HashMap<>();

    static {
        initSql2JavaMap();
    }

    public static String getJavaType(String key) {
        return sql2Java.get(StringUtils.upperCase(key));
    }

    private static void initSql2JavaMap() {
        sql2Java.put("BIT", "boolean");
        sql2Java.put("TINYINT", "int");
        sql2Java.put("SMALLINT", "int");
        sql2Java.put("MEDIUMINT", "int");
        sql2Java.put("INT", "int");
        sql2Java.put("BIGINT", "long");
        sql2Java.put("INTEGER", "long");
        sql2Java.put("CHAR", "String");
        sql2Java.put("VARCHAR", "String");
        sql2Java.put("TEXT", "String");
        sql2Java.put("BLOB", "byte[]");
        sql2Java.put("FLOAT", "float");
        sql2Java.put("DOUBLE", "double");
        sql2Java.put("DECIMAL", "BigDecimal");
        sql2Java.put("BOOLEAN", "boolean");
        sql2Java.put("ID", "long");
        sql2Java.put("DATE", "Date");
        sql2Java.put("TIME", "Time");
        sql2Java.put("DATETIME", "Timestamp");
        sql2Java.put("TIMESTAMP", "Timestamp");
        sql2Java.put("YEAR", "Date");
    }

}
