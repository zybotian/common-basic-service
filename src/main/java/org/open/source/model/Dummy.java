package org.open.source.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by Paul on 2018/6/25.
 */
@Data
@Accessors(chain = true)
public class Dummy {
    private long id;
    private String name;
    private int status;
    private String desc;
    private long createTime;
    private long updateTime;
}
