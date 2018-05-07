package com.github.mahui53541.graduation.handler;

import com.github.mahui53541.graduation.vo.CustomResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * java类简单作用描述
 *
 * @Description: 全局统一异常处理
 * @Author: MaHui
 * @CreateDate: 2018/5/7 13:42
 * @Version: 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity  usernameNotFoundExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        return CustomResponse.buildResponse(601,"usernameNotFound",null).toResponseEntity();
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity  authenticationException(HttpServletRequest req, Exception e) throws Exception {
        return CustomResponse.buildResponse(602,"password error",null).toResponseEntity();
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity  exception(HttpServletRequest req, Exception e) throws Exception {
        return CustomResponse.buildResponse(603,"password error",null).toResponseEntity();
    }
}
