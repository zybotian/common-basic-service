package org.open.source.biz;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.open.source.model.Dummy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 2018/6/25.
 */
@Service
public class DummyBiz {
    private static final int COUNT = 10;

    public List<Dummy> list() {
        List<Dummy> results = new ArrayList<>();
        for (int i = 1; i <= COUNT; i++) {
            Dummy dummy = new Dummy()
                    .setId(RandomUtils.nextLong(1000000, 2000000))
                    .setName(RandomStringUtils.randomAlphabetic(8))
                    .setDesc(RandomStringUtils.randomAlphanumeric(16))
                    .setStatus(i % 3)
                    .setCreateTime(System.currentTimeMillis())
                    .setUpdateTime(System.currentTimeMillis());
            results.add(dummy);
        }
        return results;
    }
}
