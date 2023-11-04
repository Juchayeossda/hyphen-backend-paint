package com.get.hyphenbackendpaint.global.lib.webflux.dto.response;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ValidateResponse {

    private int code;
    private String message;
    private Long data;
}