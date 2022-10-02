package com.github.klate.rps.exception;

import com.github.klate.rps.globals.ExceptionGlobals;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidParameterException;

/**
 * class that contains exception handling advices for all controllers
 * on how to handle different exceptions
 * */
@ControllerAdvice
public class GlobalExceptionHandler {

    // TODO: add logger here

    /**
     * handles the processing of EntityNotFoundException
     * @return the HTTP response body for this Exception
     * */
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    String entityNotFoundHandler(HttpServletRequest request, HttpServletResponse response, Exception ex){
        return null;
    }

    /**
     * handles the processing of InvalidParameterException
     * @return the HTTP response body for this Exception
     * */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParameterException.class)
    String invalidParameterHandler(HttpServletRequest request, HttpServletResponse response, Exception ex){
        return ex.getMessage();
    }

    /**
     * handles the processing of IllegalStateException
     * @return the HTTP response body for this Exception
     * */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IllegalStateException.class)
    String illegalStateHandler(HttpServletRequest request, HttpServletResponse response, Exception ex){
        return ex.getMessage();
    }

}