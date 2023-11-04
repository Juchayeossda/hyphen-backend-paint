package com.get.hyphenbackendpaint.domain.tag.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class TagResponse {

    private Long id;
    private String name;
    private List<Long> paints;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;
}
