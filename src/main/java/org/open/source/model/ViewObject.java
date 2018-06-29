package org.open.source.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tianbo
 * @date 2018-06-29 Friday 14:22
 */
@Data
@Accessors(chain = true)
public class ViewObject {
    private String objectName;
    private List<Line> lines;
}
