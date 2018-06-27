package org.open.source.factory.mapper.java;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 15:12
 */
public class MapperFactoryTest {

    @Test
    public void testProcess() throws Exception {
        MapperFactory mapperFactory = new MapperFactory();
        AbstractMapper mapper = mapperFactory.getMapper(MapperType.SQL_TO_JAVA_OBJ);
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
        List<Line> result = mapper.process(source, DBType.MYSQL);
        Assert.assertEquals(10, result.size());
        Assert.assertEquals("long", result.get(0).getType());
        Assert.assertEquals("id", result.get(0).getName());

        Assert.assertEquals("long", result.get(1).getType());
        Assert.assertEquals("userId", result.get(1).getName());

        Assert.assertEquals("int", result.get(2).getType());
        Assert.assertEquals("channelId", result.get(2).getName());

        Assert.assertEquals("long", result.get(3).getType());
        Assert.assertEquals("createTime", result.get(3).getName());

        Assert.assertEquals("long", result.get(4).getType());
        Assert.assertEquals("updateTime", result.get(4).getName());

        Assert.assertEquals("int", result.get(5).getType());
        Assert.assertEquals("status", result.get(5).getName());

        Assert.assertEquals("String", result.get(6).getType());
        Assert.assertEquals("deductId", result.get(6).getName());

        Assert.assertEquals("String", result.get(7).getType());
        Assert.assertEquals("cardNumber", result.get(7).getName());

        Assert.assertEquals("String", result.get(8).getType());
        Assert.assertEquals("bindId", result.get(8).getName());

        Assert.assertEquals("String", result.get(9).getType());
        Assert.assertEquals("bankName", result.get(9).getName());
    }

}
