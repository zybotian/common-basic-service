package org.open.source.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.*;

import javax.annotation.Resource;

/**
 * @author tianbo
 * @date 2019-01-15
 */
@Service
public class RedisService {

    private static final String CODE_NAME_MAP_KEY = "code_name_map";

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    public String get(String field) {
        RedisScript<String> script = new DefaultRedisScript<>("return redis.call('hget',KEYS[1],KEYS[2])", String.class);
        return redisTemplate.execute(script, Arrays.asList(CODE_NAME_MAP_KEY, field));
    }
}
