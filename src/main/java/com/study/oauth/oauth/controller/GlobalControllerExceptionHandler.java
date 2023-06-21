package com.study.oauth.oauth.controller;


import com.study.oauth.oauth.exception.NotFoundWorkerByDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = NotFoundWorkerByDate.class)
    @ResponseBody
    public ResponseEntity<String> handleNotFoundWorkerByDate() {

        return ResponseEntity.badRequest().body("해당 일자에 등록된 정보가 없습니다.");
    }


}
