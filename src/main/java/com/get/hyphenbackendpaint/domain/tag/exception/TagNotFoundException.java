package com.get.hyphenbackendpaint.domain.tag.exception;

import com.get.hyphenbackendpaint.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class TagNotFoundException extends CustomException {


    public static final CustomException EXCEPTION = new TagNotFoundException();

    private TagNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 태그를 찾지 못했습니다.");
    }
}
