package com.example.demo.handler;

import com.example.demo.dto.ErrorDTO;
import com.example.demo.exceptions.NaoAutorizadoException;
import com.example.demo.exceptions.NaoEncontradoException;
import com.example.demo.exceptions.NaoExisteException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(NaoExisteException.class)
    public ErrorDTO naoExisteException(NaoExisteException e) {
        return new ErrorDTO(
                e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(NaoEncontradoException.class)
    public ErrorDTO naoEncontradoException(NaoEncontradoException e) {
        return new ErrorDTO(
                e.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    @ExceptionHandler(NaoAutorizadoException.class)
    public ErrorDTO naoAutorizadoException(NaoAutorizadoException e) {
        return new ErrorDTO(
                e.getMessage()
        );
    }

}
