package org.open.source.factory.mapper.java;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 15:12
 */
public class MapperFactoryTest {

    @Test
    public void testProcess() throws Exception {
        MapperFactory mapperFactory = new MapperFactory();
        AbstractMapper mapper = mapperFactory.getMapper(MapperType.SQL_TO_JAVA);
        String source = "CREATE TABLE IF NOT EXISTS deduct_info (\n" +
                "  `id`               BIGINT UNSIGNED NOT NULL AUTO_INCREMENT\n" +
                "  COMMENT '自增主键',\n" +
                "  `user_id`          BIGINT          NOT NULL DEFAULT 0\n" +
                "  COMMENT '代扣签约用户的小米id',\n" +
                "  `channel_id`   TINYINT             NOT NULL DEFAULT 0\n" +
                "  COMMENT '支付渠道id，小米钱包为1',\n" +
                "  `create_time`  BIGINT              NOT NULL DEFAULT 0\n" +
                "  COMMENT '创建时间',\n" +
                "  `update_time`  BIGINT              NOT NULL DEFAULT 0\n" +
                "  COMMENT '更新时间',\n" +
                "  `status`       TINYINT             NOT NULL DEFAULT 0\n" +
                "  COMMENT '状态',\n" +
                "  `deduct_id`    VARCHAR(64)        NOT NULL DEFAULT ''\n" +
                "  COMMENT '协议号',\n" +
                "  `card_number`  VARCHAR(16)        NOT NULL DEFAULT ''\n" +
                "  COMMENT '卡号',\n" +
                "  `bind_id`       VARCHAR(16)       NOT NULL DEFAULT ''\n" +
                "  COMMENT '卡片绑定id',\n" +
                "  `bank_name`    VARCHAR(16)        NOT NULL DEFAULT ''\n" +
                "  COMMENT '银行名称',\n" +
                "\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE KEY uk_ddi_dc (`deduct_id`, `channel_id`),\n" +
                "  KEY `idx_ddi_ids` (`user_id`, `deduct_id`, `status`, `create_time`),\n" +
                "  KEY `idx_ddi_ctut` (`create_time`, `update_time`)\n" +
                ")\n" +
                "  ENGINE = InnoDB\n" +
                "  AUTO_INCREMENT = 1\n" +
                "  DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_bin;";
        String result = mapper.process(source, DBType.MYSQL);
        Assert.assertEquals("long\tid;\n" +
                "long\tuserId;\n" +
                "int\tchannelId;\n" +
                "long\tcreateTime;\n" +
                "long\tupdateTime;\n" +
                "int\tstatus;\n" +
                "String\tdeductId;\n" +
                "String\tcardNumber;\n" +
                "String\tbindId;\n" +
                "String\tbankName;\n", result);
    }

}
