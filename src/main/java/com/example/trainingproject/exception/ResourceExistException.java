package com.example.trainingproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceExistException extends RuntimeException{

    public static final long serialVersionUID=1L;
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceExistException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s is found with %s : '%s'", resourceName,fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


}
