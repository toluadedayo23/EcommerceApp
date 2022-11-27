package com.tutorials.ecommerceapp.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
public class ErrorResponse {
    private Date timestamp;
    private Integer code;
    private String status;
    private String message;

    public ErrorResponse(){
        this.timestamp = new Date();
    }

    public ErrorResponse(HttpStatus httpStatus, String message){
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }
}
