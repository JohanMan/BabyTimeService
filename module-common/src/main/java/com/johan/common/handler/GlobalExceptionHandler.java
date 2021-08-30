package com.johan.common.handler;

import com.johan.common.entity.GlobalException;
import com.johan.common.entity.ResultBody;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public ResultBody handleGlobalException(GlobalException exception) {
        return ResultBody.error(exception.getErrorCode(), exception.getErrorMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultBody handleException(Exception exception) {
        return ResultBody.error(exception.getMessage());
    }

}
