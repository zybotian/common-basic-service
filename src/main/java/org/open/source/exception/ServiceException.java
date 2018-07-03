package org.open.source.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author tianbo
 * @date 2018-06-26 Tuesday 17:05
 */
@AllArgsConstructor
@Data
public class ServiceException extends Exception {
    private ErrorCode errorCode;
}
