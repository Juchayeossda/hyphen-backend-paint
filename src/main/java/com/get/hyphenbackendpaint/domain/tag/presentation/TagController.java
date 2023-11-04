package com.get.hyphenbackendpaint.domain.tag.presentation;

import com.get.hyphenbackendpaint.domain.tag.presentation.dto.response.TagResponse;
import com.get.hyphenbackendpaint.domain.tag.presentation.dto.response.TagsResponse;
import com.get.hyphenbackendpaint.domain.tag.presentation.dto.request.TagRequest;
import com.get.hyphenbackendpaint.domain.tag.service.TagService;
import com.get.hyphenbackendpaint.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.get.hyphenbackendpaint.global.statics.response.ResponseMessageConstants.SUCCESSFUL_CREATED;
import static com.get.hyphenbackendpaint.global.statics.response.ResponseMessageConstants.SUCCESSFUL_OK;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Object> create(@RequestBody TagRequest request, @RequestHeader("Authorization") String token) {
        tagService.create(token, request);
        return ResponseData.of(HttpStatus.CREATED.value(), SUCCESSFUL_CREATED);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<List<TagsResponse>> readAll() {
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK, tagService.readAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<TagResponse> read(@PathVariable("id") String id) {
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK, tagService.read(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> update(@PathVariable("id") String id, @RequestBody TagRequest request, @RequestHeader("Authorization") String token) {
        tagService.update(id, request, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> delete(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        tagService.delete(id, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }
}
