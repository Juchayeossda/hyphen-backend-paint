package com.get.hyphenbackendpaint.domain.tag.presentation.dto.request;

import com.get.hyphenbackendpaint.domain.tag.domain.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.get.hyphenbackendpaint.global.statics.ValidMessageConstants.TAG_NAME_NOT_BLANK;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagRequest {

    @NotBlank(message = TAG_NAME_NOT_BLANK)
    private String name;

    public Tag toEntity() {
        return Tag.builder()
                .name(this.name)
                .build();
    }
}
