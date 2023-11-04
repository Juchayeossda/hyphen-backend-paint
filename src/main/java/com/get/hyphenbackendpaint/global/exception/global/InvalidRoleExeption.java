package com.get.hyphenbackendpaint.global.exception.global;

import com.get.hyphenbackendpaint.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class InvalidRoleExeption extends CustomException {


    public static final CustomException EXCEPTION = new InvalidRoleExeption();

    private InvalidRoleExeption() {
        super(HttpStatus.FORBIDDEN, "해당 기능에 접근할 수 없습니다.");
    }
}
