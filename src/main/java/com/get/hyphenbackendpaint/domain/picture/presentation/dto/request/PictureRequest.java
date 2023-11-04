package com.get.hyphenbackendpaint.domain.picture.presentation.dto.request;

import com.get.hyphenbackendpaint.domain.paint.domain.Paint;
import com.get.hyphenbackendpaint.domain.picture.domain.Picture;
import com.get.hyphenbackendpaint.domain.picture.enums.ImageType;
import com.get.hyphenbackendpaint.global.annotation.EnumValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.get.hyphenbackendpaint.global.statics.ValidMessageConstants.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PictureRequest {

    @NotBlank(message = PICTURE_TITLE_NOT_BLANK)
    private String title;

    @NotBlank(message = PICTURE_CONTENT_NOT_BLANK)
    private String content;

    @NotBlank(message = PICTURE_IMAGE_NOT_BLANK)
    private String image;

    @NotNull(message = PICTURE_TYPE_NOT_NULL)
    @EnumValid(enumClass = ImageType.class, message = PICTURE_TYPE_ENUM_VALUE)
    private ImageType type;

    @NotBlank(message = PICTURE_NUMBER_NOT_BLANK)
    private Long number;

    public Picture toEntity() {
         return Picture.builder()
            .title(this.title)
            .content(this.content)
            .image(this.image)
            .type(this.type)
            .number(this.number)
            .build();
    }
}
