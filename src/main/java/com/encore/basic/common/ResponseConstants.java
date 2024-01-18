package com.encore.basic.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseConstants {
    public static ResponseEntity<Map<String, Object>> success(HttpStatus httpStatus, Object o){
        Map<String, Object> body = new HashMap<>();
        body.put("status", String.valueOf(httpStatus.value()));
        body.put("data", o);
        return new ResponseEntity<>(body, httpStatus);
    }

    public static ResponseEntity<Map<String, Object>> fail(HttpStatus httpStatus, String message){
        Map<String, Object> body = new HashMap<>();
        body.put("status: ", String.valueOf(httpStatus.value()));
        body.put("Error Message: ", message);
        return new ResponseEntity<>(body, httpStatus);
    }
}
