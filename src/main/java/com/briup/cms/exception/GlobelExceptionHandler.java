package com.briup.cms.exception;

import com.briup.cms.utils.Result;
import com.briup.cms.utils.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auther: vanse
 * @Date: 2021/12/12-12-12-17:19
 * @Description：com.briup.cms.exception
 * @version：1.0
 */
@RestControllerAdvice
public class GlobelExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){
        e.printStackTrace();
        if(e instanceof CustomerException){
            CustomerException customerException = (CustomerException) e;
            return Result.failure(customerException.getResultCode());
        }
        return Result.failure(500,"后台接口异常");
    }
}
