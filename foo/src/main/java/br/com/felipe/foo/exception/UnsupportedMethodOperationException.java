package br.com.felipe.foo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMethodOperationException extends  RuntimeException{
    private static final long serialVersionUID = 1L;

    public UnsupportedMethodOperationException(String message) {
        super(message);
    }
}
