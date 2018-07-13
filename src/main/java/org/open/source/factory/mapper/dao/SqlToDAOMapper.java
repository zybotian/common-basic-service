package org.open.source.factory.mapper.dao;

import org.open.source.factory.mapper.AbstractMapper;
import org.open.source.model.Line;
import org.open.source.model.ViewObject;
import org.open.source.util.FormatHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 10:28 根据create table生成空的DAO类
 */
public class SqlToDAOMapper extends AbstractMapper {

    @Override
    public ViewObject decorate(ViewObject viewObject) {
        ViewObject result = new ViewObject();
        List<Line> lines = new ArrayList<>();

        // TABLE NAME定义
        lines.add(new Line().setType("String").setName("TABLE_NAME = \"" + FormatHelper.formatToTableName
                (viewObject.getObjectName()) + "\""));
        // INSERT COLUMNS
        lines.add(new Line().setType("String").setName("INSERT_COLUMNS = \"" + FormatHelper.formatToInsertColumns
                (viewObject.getLines()) + "\""));
        // SELECT COLUMNS
        lines.add(new Line().setType("String").setName("SELECT_COLUMNS = \"" + FormatHelper.formatToSelectColumns
                (viewObject.getLines()) + "\""));
        // INSERT VALUES
        lines.add(new Line().setType("String").setName("INSERT_VALUES = \"" + FormatHelper.formatToInsertValues
                (viewObject.getLines()) + "\""));
        // UPDATE COLUMNS
        lines.add(new Line().setType("String").setName("UPDATE_COLUMNS = \"" + FormatHelper.formatToUpdateColumns
                (viewObject.getLines()) + "\""));
        result.setObjectName(FormatHelper.convert2UpperCamel(viewObject.getObjectName()) + "DAO");
        result.setLines(lines);
        return result;
    }
}
