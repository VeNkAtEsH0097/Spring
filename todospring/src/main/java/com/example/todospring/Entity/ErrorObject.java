package com.example.todospring.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {

    private Integer statuscode;
    private String message;
    private Date timestamp;
}
