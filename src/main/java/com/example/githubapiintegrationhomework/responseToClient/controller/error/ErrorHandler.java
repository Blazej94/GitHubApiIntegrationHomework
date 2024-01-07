package com.example.githubapiintegrationhomework.responseToClient.controller.error;

import com.example.githubapiintegrationhomework.responseToClient.controller.RepositoryRestController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = RepositoryRestController.class)
@Log4j2
public class ErrorHandler {

    @ExceptionHandler({MissingRequestHeaderException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    ErrorClientResponseDto handleMissingRequestHeaderException() {
        log.warn("Client did not give header contains Accept key");
        return new ErrorClientResponseDto(HttpStatus.NOT_ACCEPTABLE.value(), "Accept: application/json is require in header");
    }
}
