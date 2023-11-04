package com.get.hyphenbackendpaint.domain.picture.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImageType {

    MAIN("MAIN"), SUB("SUB");
    private final String name;
}
