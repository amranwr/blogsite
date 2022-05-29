package com.mentor.blogStie.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends AuthenticationException {
    public UserAlreadyExistsException(String explanation) {
        super(explanation);
    }
}
