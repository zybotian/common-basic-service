package org.open.source.util;

import org.open.source.Exception.ErrorCode;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 17:15
 */
@Data
@Accessors(chain = true)
public class JsonResult<T> {
    private Integer code;
    private String msg;
    private Boolean success;
    private T data;

    public JsonResult(Integer code, String msg, Boolean success, T data) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.data = data;
    }

    public JsonResult(ErrorCode errorCode, T data) {
        this(errorCode);
        this.data = data;
    }

    public JsonResult(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.success = errorCode.isSuccess();
    }

    public JsonResult() {
        this(ErrorCode.SUCCESS);
    }
}
