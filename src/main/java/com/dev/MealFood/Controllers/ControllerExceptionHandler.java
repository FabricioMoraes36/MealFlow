package com.dev.MealFood.Controllers;


import com.dev.MealFood.Exceptions.GlobalMealException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(GlobalMealException.class)
    public ProblemDetail handleMinhaException(GlobalMealException  e){
        return e.toProblemDetail();
    }
}
