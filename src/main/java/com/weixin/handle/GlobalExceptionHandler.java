package com.weixin.handle;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author liumingzhong
 * @date 2018-2-6
 */
@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private Map<String,Object> exceptionHandler(HttpServletRequest request,Exception e){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("success",false);
        map.put("errMsg",e.getMessage());
        return map;
    }
}
