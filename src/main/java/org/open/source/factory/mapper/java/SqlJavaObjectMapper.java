package org.open.source.factory.mapper.java;

import com.alibaba.druid.stat.TableStat;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
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
    public List<Line> convert(String source, DBType dbType) {
        log.info("start converting, db type:{}, source:{}", dbType, source);
        dbType = ObjectUtils.defaultIfNull(dbType, DBType.DEFAULT);
        List<TableStat.Column> columns = SqlParser.extractColumns(dbType.getValue(), source);
        if (CollectionUtils.isEmpty(columns)) {
            return Collections.unmodifiableList(new ArrayList<Line>());
        }

        List<Line> lines = new ArrayList<>();
        for (TableStat.Column column : columns) {
            Line line = new Line()
                    .setType(column.getDataType())
                    .setName(column.getName())
                    ;
            lines.add(line);
        }
        return Collections.unmodifiableList(lines);
    }

    @Override
    public List<Line> decorate(List<Line> lines) {
        if (CollectionUtils.isEmpty(lines)) {
            return null;
        }

        for (Line line : lines) {
            formatLine(line);
        }
        return lines;
    }

    private void formatLine(Line line) {
        FormatHelper.formatToJavaLine(line);
    }
}
