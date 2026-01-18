package com.darksoft.noteflow.backend.api.exceptions;

import com.darksoft.noteflow.backend.domain.exceptions.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler({Exception.class, DomainException.class})
    public ResponseEntity<Object> handler(Exception e){
        log.error("Problem handling the request.", e.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
