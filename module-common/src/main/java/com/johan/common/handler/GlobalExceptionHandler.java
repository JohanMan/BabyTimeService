package com.johan.common.handler;

import com.johan.common.entity.GlobalException;
import com.johan.common.entity.ResultBody;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(GlobalException.class)
    public ResultBody handleGlobalException(GlobalException exception) {
        return ResultBody.error(exception.getErrorCode(), exception.getErrorMessage());
    }

}
