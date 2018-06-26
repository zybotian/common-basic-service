package org.open.source.util;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Paul on 2018/6/25.
 */
@Slf4j
public class SqlParser {

    /**
     * @param dbType {@link JdbcConstants}
     * @param sql    create table 对应的完整的sql语句
     * @return 表的所有列
     */
    public static List<TableStat.Column> extractColumns(String dbType, String sql) {
        log.info("extract columns, db type:{}, sql:{}", dbType, sql);
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
}
