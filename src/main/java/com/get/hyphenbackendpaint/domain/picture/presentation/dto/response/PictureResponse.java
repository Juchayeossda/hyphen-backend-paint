package com.get.hyphenbackendpaint.domain.picture.presentation.dto.response;

import com.get.hyphenbackendpaint.domain.picture.enums.ImageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PictureResponse {

    private Long id;
    private String title;
    private String content;
    private String image;
    private ImageType type;
    private Long number;
    private Long paintId;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
}
