package com.get.hyphenbackendpaint.domain.paint.exception;

import com.get.hyphenbackendpaint.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class PaintNotFoundException extends CustomException {


    public static final CustomException EXCEPTION = new PaintNotFoundException();

    private PaintNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 그림을 찾지 못했습니다.");
    }
}
