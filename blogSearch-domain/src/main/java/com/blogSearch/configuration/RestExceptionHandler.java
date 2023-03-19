package com.blogSearch.configuration;

import com.blogSearch.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { CustomException.class })
    public final ResponseEntity<Object> handleBadRequest(RuntimeException ex) {
        log.warn("[{}] : {}", BAD_REQUEST.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<>(BlogSearchErrorResponse.from("요청을 처리하지 못했습니다.", ex.getMessage()), BAD_REQUEST);
    }
    @ExceptionHandler(value = { Exception.class })
    public final ResponseEntity<Object> handleInternalServerError(RuntimeException ex) {
        log.warn("[{}] : {}", INTERNAL_SERVER_ERROR.getReasonPhrase(), getExceptionTrace(ex));
        return new ResponseEntity<>(BlogSearchErrorResponse.from("예기치 못한 서버 에러가 발생했습니다.", ex.getMessage()), INTERNAL_SERVER_ERROR);
    }

    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(e -> e.getDefaultMessage() + "(" + e.getField() + ")").orElse("No Message");
        return new ResponseEntity<>(BlogSearchErrorResponse.from(errorMessage, null), BAD_REQUEST);
    }

    private String getExceptionTrace(RuntimeException ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
