package com.get.hyphenbackendpaint.domain.paint.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.get.hyphenbackendpaint.global.statics.ValidMessageConstants.PAINT_TITLE_NOT_BLANK;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeTitleRequest {

    @NotBlank(message = PAINT_TITLE_NOT_BLANK)
    private String title;
}
