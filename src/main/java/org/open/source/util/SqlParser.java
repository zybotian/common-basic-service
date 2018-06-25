package org.open.source.util;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Paul on 2018/6/25.
 */
public class SqlParser {

    private static final int ONE = 1;

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
        Assert.state(stmtList != null && stmtList.size() == ONE);

        SQLStatement stmt = stmtList.get(0);
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        stmt.accept(visitor);

        return Collections.unmodifiableList(new ArrayList<>(visitor.getColumns()));
    }
}
