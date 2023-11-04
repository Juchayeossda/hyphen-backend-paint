package com.get.hyphenbackendpaint.domain.picture.presentation;

import com.get.hyphenbackendpaint.domain.picture.domain.Picture;
import com.get.hyphenbackendpaint.domain.picture.presentation.dto.request.PictureRequest;
import com.get.hyphenbackendpaint.domain.picture.presentation.dto.response.PictureResponse;
import com.get.hyphenbackendpaint.domain.picture.presentation.dto.response.PicturesResponse;
import com.get.hyphenbackendpaint.domain.picture.service.PictureService;
import com.get.hyphenbackendpaint.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.get.hyphenbackendpaint.global.statics.response.ResponseMessageConstants.SUCCESSFUL_CREATED;
import static com.get.hyphenbackendpaint.global.statics.response.ResponseMessageConstants.SUCCESSFUL_OK;

@RestController
@RequestMapping("/api/picture")
@RequiredArgsConstructor
public class PictureController {

    private final PictureService pictureService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Object> create(@RequestBody PictureRequest request, @RequestHeader("Authorization") String token) {
        pictureService.create(token, request);
        return ResponseData.of(HttpStatus.CREATED.value(), SUCCESSFUL_CREATED);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<List<PicturesResponse>> readAll() {
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK, pictureService.readAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<PictureResponse> read(@PathVariable("id") String id) {
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK, pictureService.read(id));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> update(@PathVariable("id") String id, @RequestBody PictureRequest request, @RequestHeader("Authorization") String token) {
        pictureService.update(id, request, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> delete(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        pictureService.delete(id, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }
}
