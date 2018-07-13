package org.open.source.factory.mapper;

import org.open.source.factory.mapper.dao.SqlToDAOMapper;
import org.open.source.factory.mapper.java.SqlJavaObjectMapper;
import org.springframework.stereotype.Service;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 14:47
 */
@Service
public class MapperFactory {
    private static AbstractMapper sqlJavaObjectMapper = new SqlJavaObjectMapper();

    private static AbstractMapper sqlToDAOMapper = new SqlToDAOMapper();


    public AbstractMapper getMapper(MapperType type) {
        switch (type) {
            case SQL_TO_DAO_DEF: {
                return sqlToDAOMapper;
            }
            case SQL_TO_JAVA_OBJ: {
                return sqlJavaObjectMapper;
            }
            default: {
                return null;
            }
        }

    }
}
