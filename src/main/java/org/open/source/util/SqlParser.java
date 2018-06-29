package org.open.source.util;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.open.source.model.TableDescription;

import java.util.*;

/**
 * Created by Paul on 2018/6/25.
 */
public class SqlParser {

    /**
     * @param dbType {@link JdbcConstants}
     * @param sql    create table 对应的完整的sql语句
     * @return 表的所有列
     */
    public static List<TableStat.Column> extractColumns(String dbType, String sql) {
        if (StringUtils.isEmpty(sql)) {
            return Collections.unmodifiableList(new ArrayList<TableStat.Column>());
        }
        // 默认值设置为mysql
        dbType = StringUtils.defaultIfEmpty(dbType, JdbcConstants.MYSQL);

        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);
        if (CollectionUtils.isEmpty(stmtList)) {
            return Collections.unmodifiableList(new ArrayList<TableStat.Column>());
        }

        // 目前仅仅用于create table语句的解析
        SQLStatement stmt = stmtList.get(0);
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        stmt.accept(visitor);

        return Collections.unmodifiableList(new ArrayList<>(visitor.getColumns()));
    }

    public static TableDescription extractTableDescription(String dbType, String sql) {
        SQLStatementParser sqlStatementParser = SQLParserUtils.createSQLStatementParser(sql, dbType);
        SQLCreateTableStatement sqlCreateTableStatement = sqlStatementParser.parseCreateTable();
        List<SQLTableElement> tableElementList = sqlCreateTableStatement.getTableElementList();

        List<SQLColumnDefinition> columnDefinitions = new ArrayList<>();
        for (SQLTableElement tableElement : tableElementList) {
            if (!(tableElement instanceof SQLColumnDefinition)) {
                // primary key, key, unique key等等过滤掉, 只保留列定义
                continue;
            }
            columnDefinitions.add((SQLColumnDefinition) tableElement);
        }

        SQLExprTableSource tableSource = sqlCreateTableStatement.getTableSource();
        TableDescription tableDescription = new TableDescription()
                .setSqlColumnDefinitionList(columnDefinitions)
                .setTableName(tableSource.getName().getSimpleName())
                ;
        return tableDescription;
    }
}
