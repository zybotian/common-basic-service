package org.open.source.biz;

import org.open.source.exception.ServiceException;
import org.open.source.factory.mapper.*;
import org.open.source.model.DBType;
import org.open.source.model.ViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Paul on 2018/6/25.
 */
@Service
public class ConvertBiz {
    @Autowired
    MapperFactory mapperFactory;

    public ViewObject convert(MapperType mapperType, String content, DBType dbType) throws ServiceException {
        AbstractMapper mapper = mapperFactory.getMapper(mapperType);
        return mapper.process(content, dbType);
    }
}
