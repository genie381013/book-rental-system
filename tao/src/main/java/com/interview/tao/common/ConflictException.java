package com.interview.tao.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ConflictException extends Exception{

    public ConflictException(String errorMessage) {
        super(errorMessage);
    }

}
