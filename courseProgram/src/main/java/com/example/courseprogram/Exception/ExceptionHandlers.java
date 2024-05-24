package com.example.courseprogram.Exception;

import com.example.courseprogram.model.DTO.DataResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(RuntimeException.class)
    public DataResponse error(Exception e) {
        System.out.println(e.getMessage());
        return DataResponse.failure(410,"后端报错:"+e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DataResponse validException(MethodArgumentNotValidException e){
        System.out.println(e.getMessage());
        return DataResponse.failure(401,"数据不合法:"+e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public DataResponse validException(ConstraintViolationException e){
        System.out.println(e.getMessage());
        return DataResponse.failure(401,"数据不合法:"+e.getMessage());
    }
}
