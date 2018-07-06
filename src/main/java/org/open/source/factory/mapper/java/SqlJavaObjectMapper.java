package org.open.source.factory.mapper.java;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.open.source.model.*;
import org.open.source.util.FormatHelper;
import org.open.source.util.SqlParser;

import java.util.*;

import lombok.extern.slf4j.Slf4j;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 10:27
 */
@Slf4j
public class SqlJavaObjectMapper extends AbstractMapper {
    @Override
    public ViewObject convert(String source, DBType dbType) {
        log.info("start converting, db type:{}, source:{}", dbType, source);
        dbType = ObjectUtils.defaultIfNull(dbType, DBType.DEFAULT);
        source = SQLUtils.format(source, dbType.getValue());
        TableDescription tableDescription = SqlParser.extractTableDescription(dbType.getValue(), source);

        ViewObject viewObject = new ViewObject();
        if (StringUtils.isEmpty(tableDescription.getTableName()) || CollectionUtils.isEmpty(tableDescription
                .getSqlColumnDefinitionList())) {
            // 解析结果没有表名, 或者列, 返回空对象
            return viewObject;
        }

        List<Line> lines = new ArrayList<>();
        for (SQLColumnDefinition column : tableDescription.getSqlColumnDefinitionList()) {
            Line line = new Line()
                    .setType(column.getDataType().getName())
                    .setName(column.getName().getSimpleName())
                    // 注释可能是没有的
                    .setComment(column.getComment() != null ? column.getComment().toString() : StringUtils.EMPTY);
            lines.add(line);
        }
        viewObject.setObjectName(tableDescription.getTableName());
        viewObject.setLines(lines);
        return viewObject;
    }

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
