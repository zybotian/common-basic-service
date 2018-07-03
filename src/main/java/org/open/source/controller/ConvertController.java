package org.open.source.controller;

import org.apache.commons.lang3.StringUtils;
import org.open.source.biz.ConvertBiz;
import org.open.source.exception.ErrorCode;
import org.open.source.exception.ServiceException;
import org.open.source.factory.mapper.java.MapperType;
import org.open.source.model.DBType;
import org.open.source.model.ViewObject;
import org.open.source.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Paul on 2018/6/25.
 */
@Controller
@RequestMapping("/convert")
@Slf4j
public class ConvertController {

    @Autowired
    ConvertBiz convertBiz;

    @ResponseBody
    @RequestMapping(value = "/sqlToObj", method = RequestMethod.POST)
    public Object convertSqlToObject(@RequestParam(name = "content") String content,
                                     @RequestParam(name = "operation") String operation)
            throws Exception {
        log.info("convert sql to java object, content:{}, operation:{}", content, operation);
        MapperType mapperType = MapperType.findByValue(operation);
        if (mapperType == null) {
            throw new ServiceException(ErrorCode.INVALID_PARAM_ERROR);
        }
        if (StringUtils.isEmpty(content)) {
            throw new ServiceException(ErrorCode.INVALID_PARAM_ERROR);
        }
        ViewObject viewObject = convertBiz.convert(mapperType, content, DBType.MYSQL);
        return new JsonResult(ErrorCode.SUCCESS, viewObject);
    }
}
