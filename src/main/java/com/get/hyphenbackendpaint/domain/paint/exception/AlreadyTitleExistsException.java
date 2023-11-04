package com.get.hyphenbackendpaint.domain.paint.exception;

import com.get.hyphenbackendpaint.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class AlreadyTitleExistsException extends CustomException {

    public static final CustomException EXCEPTION = new AlreadyTitleExistsException();

    private AlreadyTitleExistsException() {
        super(HttpStatus.BAD_REQUEST, "이미 해당 제목의 그림이 존재합니다.");
    }

}
