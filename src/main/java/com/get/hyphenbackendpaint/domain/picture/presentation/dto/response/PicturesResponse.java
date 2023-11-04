package com.get.hyphenbackendpaint.domain.picture.presentation.dto.response;

import com.get.hyphenbackendpaint.domain.picture.enums.ImageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PicturesResponse {

    private Long id;
    private String title;
    private String content;
    private String image;
    private ImageType type;
    private Long number;
}
