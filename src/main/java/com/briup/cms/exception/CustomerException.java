package com.briup.cms.exception;

import com.briup.cms.utils.ResultCode;
import lombok.Data;

/**
 * @Auther: vanse
 * @Date: 2021/12/12-12-12-17:26
 * @Description：com.briup.cms.exception
 * @version：1.0
 */
@Data
public class CustomerException extends RuntimeException {
    private String message;
    private ResultCode resultCode;

    public CustomerException(String message) {
        super(message);
    }

    public CustomerException(ResultCode resultCode) {
        message = resultCode.message();
        this.resultCode = resultCode;
    }
}
