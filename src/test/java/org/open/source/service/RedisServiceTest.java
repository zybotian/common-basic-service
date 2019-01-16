package org.open.source.service;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author tianbo
 * @date 2019-01-15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-test.xml")
@Ignore
public class RedisServiceTest {

    @Autowired
    RedisService redisService;

    @Test
    public void testGet() throws Exception {
        String result = redisService.get("A6FAB31946AE46C5A17D13460670E4A5");
        Assert.assertTrue(result != null);

        String result2 = redisService.get("74BD374688E144C89681BF93523D4588");
        Assert.assertTrue(result2 != null);
    }
}
