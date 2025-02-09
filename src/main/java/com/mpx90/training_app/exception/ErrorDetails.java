package com.mpx90.training_app.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private int statusCode;
    private String message;
    private String details;
}
