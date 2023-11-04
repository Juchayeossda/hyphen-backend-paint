package com.get.hyphenbackendpaint.domain.tag.exception;

import com.get.hyphenbackendpaint.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AlreadyNameExistsException extends CustomException {

    public static final CustomException EXCEPTION = new AlreadyNameExistsException();

    private AlreadyNameExistsException() {
        super(HttpStatus.BAD_REQUEST, "이미 해당 이름의 태그가 존재합니다.");
    }

}
