package org.open.source.util;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Paul on 2018/6/26.
 */
public class SqlParserTest {

    @Test
    public void test() {
        String sql = "CREATE TABLE `user`\n" +
                "(\n" +
                "id           BIGINT NOT NULL DEFAULT 0\n" +
                "COMMENT '自增主键'," +
                "last_name    VARCHAR(256) NOT NULL DEFAULT ''\n" +
                "COMMENT '名字'," +
                "first_name   VARCHAR(256) NOT NULL DEFAULT ''\n" +
                "COMMENT '姓氏'," +
                "address      VARCHAR(256) NOT NULL DEFAULT ''\n" +
                "COMMENT '地址'," +
                "city         VARCHAR(256) NOT NULL DEFAULT ''\n" +
                "COMMENT '城市'," +
                "PRIMARY KEY (id)" +
                ")";
        String dbType = JdbcConstants.MYSQL;

        // 格式化输出,缺省大写格式
        String result = SQLUtils.format(sql, dbType);
        Assert.assertEquals("CREATE TABLE `user` (\n" +
                "\tid BIGINT NOT NULL DEFAULT 0 COMMENT '自增主键',\n" +
                "\tlast_name VARCHAR(256) NOT NULL DEFAULT '' COMMENT '名字',\n" +
                "\tfirst_name VARCHAR(256) NOT NULL DEFAULT '' COMMENT '姓氏',\n" +
                "\taddress VARCHAR(256) NOT NULL DEFAULT '' COMMENT '地址',\n" +
                "\tcity VARCHAR(256) NOT NULL DEFAULT '' COMMENT '城市',\n" +
                "\tPRIMARY KEY (id)\n" +
                ")", result);
        List<TableStat.Column> columns = SqlParser.extractColumns(JdbcConstants.MYSQL, sql);
        Assert.assertEquals("BIGINT", columns.get(0).getDataType());
        Assert.assertEquals("id", columns.get(0).getName());

        Assert.assertEquals("VARCHAR", columns.get(1).getDataType());
        Assert.assertEquals("last_name", columns.get(1).getName());

        Assert.assertEquals("VARCHAR", columns.get(2).getDataType());
        Assert.assertEquals("first_name", columns.get(2).getName());

        Assert.assertEquals("VARCHAR", columns.get(3).getDataType());
        Assert.assertEquals("address", columns.get(3).getName());

        Assert.assertEquals("VARCHAR", columns.get(4).getDataType());
        Assert.assertEquals("city", columns.get(4).getName());
    }
}
