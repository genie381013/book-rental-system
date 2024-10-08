package com.interview.tao.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends Exception{

    public BookNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
