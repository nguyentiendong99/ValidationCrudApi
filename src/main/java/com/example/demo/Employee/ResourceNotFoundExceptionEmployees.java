package com.example.demo.Employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptionEmployees extends Exception{
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundExceptionEmployees(String message){
        super(message);
    }
}
