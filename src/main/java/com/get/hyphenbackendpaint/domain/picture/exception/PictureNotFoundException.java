package com.get.hyphenbackendpaint.domain.picture.exception;

import com.get.hyphenbackendpaint.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class PictureNotFoundException extends CustomException {


    public static final CustomException EXCEPTION = new PictureNotFoundException();

    private PictureNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 사진을 찾지 못했습니다.");
    }
}
