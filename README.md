# common-basic-service

### A simple and useful efficiency tool for java developers

> Java + Bootstrap + JQuery 

Currently, the common basic service supported functions as below:

- converting sql(create table sql script) to java POJO class
- converting sql(create table sql script) to paoding rose framework DAO class

This tool does not need to connect to database, you ONLY need to paste your sql script into the input textarea and click 
button, you will get the parsed result!

#### ![operation](https://raw.githubusercontent.com/zybotian/common-basic-service/master/src/imgs/home.png)
#### ![view](https://github.com/zybotian/common-basic-service/blob/master/src/imgs/home.png?raw=true)
#### ![view](https://github.com/zybotian/common-basic-service/master/src/imgs/home.png?raw=true)

For Example, you input:
```mysql
CREATE TABLE IF NOT EXISTS bill (
id              BIGINT                  UNSIGNED NOT NULL AUTO_INCREMENT
COMMENT '自增主键',
user_id         BIGINT                  NOT NULL DEFAULT 0
COMMENT '用户id',
create_time     BIGINT                  NOT NULL DEFAULT 0
COMMENT '创建时间',
update_time     BIGINT                  NOT NULL DEFAULT 0
COMMENT '更新时间',
PRIMARY KEY (id),
KEY idx_bill_uid (user_id)
)  ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_bin;
```

you will get the POJO definition(Xxx.java, you can download it directly):

```java
public class Bill {
    // 自增主键
    private long id;
    // 用户id
    private long userId;
    // 创建时间
    private long createTime;
    // 更新时间
    private long updateTime;
}
```

and also you can get the paoding rose dao class(XxxDAO.java, also you can download it):
```java
import net.paoding.rose.jade.annotation.DAO;

@DAO 
public interface BillDAO {
    String TABLE_NAME = "`bill`";
    String INSERT_COLUMNS = "`id`,`user_id`,`create_time`,`update_time`";
    String SELECT_COLUMNS = "`id`,`user_id`,`create_time`,`update_time`";
    String INSERT_VALUES = ":1.id, :1.userId, :1.createTime, :1.updateTime";
    String UPDATE_COLUMNS = "id=:1.id, user_id=:1.userId, create_time=:1.createTime, update_time=:1.updateTime";
}
```

The core implementation is using alibaba druid ***SQLParserUtils*** to parse sql, as below:
```java
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
```

when we get the column definitions, we can get the column data type, column name, and column comments and so on.
Then we can generate the corresponding java POJO definition and DAO definition.

## Benefits
- as you can see, after handwriting creating table sql statements, we do not need to handwrite POJO definition and 
DAO class, which is a disaster when the table is very complex.
- the tool is very simple to use, we don't need to connect to database(comparision with some other solutions)
