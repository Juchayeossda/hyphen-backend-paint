package com.get.hyphenbackendpaint.global.lib.webflux.dto.response;

import lombok.*;
import lombok.Data;

@Getter
@AllArgsConstructor
public class BaseResponse<T> {

    private int code;
    private String message;
    private T data;
}