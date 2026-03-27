package com.demo.crud.Exception;

import com.demo.crud.Entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorMessage> employeeNotFoundException(EmployeeNotFoundException em, WebRequest webRequest){

        String causeMessage;

        if (em.getCause() != null) {
            causeMessage = em.getCause().getMessage();
        } else {
            causeMessage = "No additional details";
        }

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND,
                em.getMessage(), causeMessage);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

    }
}
