package org.open.source.factory.mapper.java;

import org.springframework.stereotype.Service;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 14:47
 */
@Service
public class MapperFactory {
    private static AbstractMapper sqlJavaObjectMapper = new SqlJavaObjectMapper();

    private static AbstractMapper javaObjectSqlMapper = new JavaObjectSqlMapper();


    public AbstractMapper getMapper(MapperType type) {
        switch (type) {
            case JAVA_TO_SQL: {
                return javaObjectSqlMapper;
            }
            case SQL_TO_JAVA: {
                return sqlJavaObjectMapper;
            }
            default: {
                return null;
            }
        }

    }
}
