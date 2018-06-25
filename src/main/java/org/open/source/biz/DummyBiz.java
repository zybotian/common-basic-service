package org.open.source.biz;

import org.open.source.model.Dummy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 2018/6/25.
 */
@Service
public class DummyBiz {
    public List<Dummy> list() {
        List<Dummy> results = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Dummy dummy = new Dummy()
                    .setId(i + 1000)
                    .setName("name" + i)
                    .setDesc("desc")
                    .setStatus(i % 3)
                    .setCreateTime(System.currentTimeMillis())
                    .setUpdateTime(System.currentTimeMillis());
            results.add(dummy);
        }
        return results;
    }
}
