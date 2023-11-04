package com.get.hyphenbackendpaint.domain.paint.presentation.dto.response;

import com.get.hyphenbackendpaint.domain.paint.domain.Paint;
import com.get.hyphenbackendpaint.domain.tag.domain.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaintsResponse {

    private Long id;
    private String title;
    private Long likeCount;

    public PaintsResponse(Paint paint) {
        this.id = paint.getId();
        this.title = paint.getTitle();
        this.likeCount = paint.getLikeCount();
    }
}
