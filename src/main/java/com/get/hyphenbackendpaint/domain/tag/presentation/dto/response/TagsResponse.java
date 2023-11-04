package com.get.hyphenbackendpaint.domain.tag.presentation.dto.response;

import com.get.hyphenbackendpaint.domain.tag.domain.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TagsResponse {

    private Long id;
    private String name;

    public TagsResponse(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }
}
