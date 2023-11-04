package com.get.hyphenbackendpaint.domain.paint.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class PaintResponse {

    private Long id;
    private String title;
    private Long likeCount;
    private List<Long> pictures;
    private List<Long> tags;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

}
