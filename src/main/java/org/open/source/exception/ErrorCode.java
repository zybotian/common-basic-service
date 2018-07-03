package org.open.source.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 17:07
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ErrorCode {
    public static final ErrorCode SUCCESS = new ErrorCode(100000000, "成功", true);

    /**
     * 服务相关的错误
     */
    public static final ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(100010001, "内部服务错误", false);
    public static final ErrorCode UNSUPPORTED_OPERATION_ERROR = new ErrorCode(100010002, "暂不支持该操作", false);

    /**
     * 参数相关的错误
     */
    public static final ErrorCode INVALID_PARAM_ERROR = new ErrorCode(100020001, "无效的请求参数", false);


    private int code;
    private String msg;
    private boolean success;
}
