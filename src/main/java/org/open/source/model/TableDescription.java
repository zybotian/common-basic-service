package org.open.source.model;

import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tianbo
 * @date 2018-06-29 Friday 14:09
 */
@Data
@Accessors(chain = true)
public class TableDescription {
    private String tableName;
    private List<SQLColumnDefinition> sqlColumnDefinitionList;
}
