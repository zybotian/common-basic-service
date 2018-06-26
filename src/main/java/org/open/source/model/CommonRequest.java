package org.open.source.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 16:48
 */
@Data
@Accessors(chain = true)
public class CommonRequest {
    private String content;
    private String operation;
}
