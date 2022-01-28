package br.com.felipe.foo.exception.handler;

import br.com.felipe.foo.exception.ExceptionResponse;
import br.com.felipe.foo.exception.UnsupportedMethodOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handlerAllExceptions(Exception exp, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exp.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(UnsupportedMethodOperationException.class)
    public final ResponseEntity<ExceptionResponse> handlerBadRequestExceptions(Exception exp, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exp.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
