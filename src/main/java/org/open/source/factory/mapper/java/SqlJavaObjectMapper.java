package org.open.source.factory.mapper.java;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.open.source.factory.mapper.AbstractMapper;
import org.open.source.model.Line;
import org.open.source.model.ViewObject;
import org.open.source.util.FormatHelper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 10:27 根据create table语句生成对应的java POJO
 */
@Slf4j
public class SqlJavaObjectMapper extends AbstractMapper {

    @Override
    public ViewObject decorate(ViewObject viewObject) {
        if (StringUtils.isEmpty(viewObject.getObjectName()) || CollectionUtils.isEmpty(viewObject.getLines())) {
            return viewObject;
        }

        viewObject.setObjectName(formatObjectName(viewObject.getObjectName()));
        for (Line line : viewObject.getLines()) {
            formatLine(line);
        }
        return viewObject;
    }

    private String formatObjectName(String objectName) {
        return FormatHelper.convert2UpperCamel(StringUtils.replaceAll(objectName, "`", ""));
    }

    private void formatLine(Line line) {
        FormatHelper.formatToJavaLine(line);
    }
}
