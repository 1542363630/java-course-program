package com.example.courseprogram.Exception;

import com.example.courseprogram.model.DTO.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(RuntimeException.class)
    public DataResponse error(Exception e) {
        System.out.println(e.getMessage());
        return DataResponse.failure(410,e.getMessage());
    }
}
