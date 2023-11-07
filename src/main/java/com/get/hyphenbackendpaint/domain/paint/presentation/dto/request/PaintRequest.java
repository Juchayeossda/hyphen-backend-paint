package com.get.hyphenbackendpaint.domain.paint.presentation.dto.request;

import com.get.hyphenbackendpaint.domain.paint.domain.Paint;
import com.get.hyphenbackendpaint.domain.picture.domain.Picture;
import com.get.hyphenbackendpaint.domain.tag.domain.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

import static com.get.hyphenbackendpaint.global.statics.ValidMessageConstants.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaintRequest {

    @NotBlank(message = PAINT_TITLE_NOT_BLANK)
    private String title;

    @NotNull(message = PICTURE_NOT_NULL)
    private List<Long> pictures;

    @NotNull(message = TAG_NOT_NULL)
    private List<Long> tags;

    public Paint toEntity(Set<Tag> tags) {
        return Paint.builder()
            .title(title)
            .tags(tags)
            .build();
    }
}
