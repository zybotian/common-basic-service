## common-basic-service

<h2>A simple and useful efficiency tool for java developers</h1>

Currently, the common basic service supported functions as below:

<h3>(1) converting sql to java POJO class;</h3>
<h3>(2) converting sql to paoding rose framework DAO class;</h3>

This tool does not need to connect to database, you ONLY need paste your sql script into the textarea and click button, you will get the parsed result!

For Example, you input:
<div>
<code>CREATE TABLE IF NOT EXISTS `bill` (</code>
<code> `id`              BIGINT                  UNSIGNED NOT NULL AUTO_INCREMENT</code>
<code>  COMMENT '自增主键',</code>
<code>  `user_id`         BIGINT                  NOT NULL DEFAULT 0</code>
<code>  COMMENT '用户id',</code>
<code>  `create_time`     BIGINT                  NOT NULL DEFAULT 0</code>
<code>  COMMENT '创建时间',</code>
<code>  `update_time`     BIGINT                  NOT NULL DEFAULT 0</code>
<code>  COMMENT '更新时间',</code>
<code>  PRIMARY KEY (`id`),</code>
<code>  KEY `idx_bill_uid` (`user_id`)</code>
<code>)  ENGINE = InnoDB</code>
<code>  AUTO_INCREMENT = 1</code>
<code>  DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_bin;</code>
</div>
you will get:
<div>
<code>public class Bill {</code>
  <code>    // 自增主键</code>
<code>    private long id;</code>
<code>    // 用户id</code>
<code>    private long userId;</code>
<code>    // 创建时间</code>
<code>    private long createTime;</code>
<code>    // 更新时间</code>
<code>    private long updateTime;</code>
<code>}</code>
</div>
