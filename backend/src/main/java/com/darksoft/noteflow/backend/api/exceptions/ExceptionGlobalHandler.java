package com.darksoft.noteflow.backend.api.exceptions;

import com.darksoft.noteflow.backend.application.exceptions.ApplicationException;
import com.darksoft.noteflow.backend.domain.exceptions.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler({DomainException.class, ApplicationException.class})
    public ResponseEntity<Object> domainExceptionHandler(RuntimeException e){
        log.warn("Problem handling the request.", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> generalExceptionHandler(Exception e){
        log.error("Internal error.", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("message", e.getMessage()));
    }
}
