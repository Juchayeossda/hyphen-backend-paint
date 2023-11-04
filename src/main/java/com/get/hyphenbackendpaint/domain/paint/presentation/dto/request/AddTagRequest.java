package com.get.hyphenbackendpaint.domain.paint.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.get.hyphenbackendpaint.global.statics.ValidMessageConstants.TAG_ID_NOT_BLANK;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddTagRequest {

    @NotBlank(message = TAG_ID_NOT_BLANK)
    private Long tagId;
}
