package com.get.hyphenbackendpaint.domain.paint.presentation;

import com.get.hyphenbackendpaint.domain.paint.presentation.dto.request.*;
import com.get.hyphenbackendpaint.domain.paint.presentation.dto.response.PaintResponse;
import com.get.hyphenbackendpaint.domain.paint.presentation.dto.response.PaintsResponse;
import com.get.hyphenbackendpaint.domain.paint.service.PaintService;
import com.get.hyphenbackendpaint.global.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.get.hyphenbackendpaint.global.statics.response.ResponseMessageConstants.SUCCESSFUL_CREATED;
import static com.get.hyphenbackendpaint.global.statics.response.ResponseMessageConstants.SUCCESSFUL_OK;

@RestController
@RequestMapping("/api/paint")
@RequiredArgsConstructor
public class PaintController {

    private final PaintService paintService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Object> create(@RequestBody PaintRequest request, @RequestHeader("Authorization") String token) {
        paintService.create(token, request);
        return ResponseData.of(HttpStatus.CREATED.value(), SUCCESSFUL_CREATED);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<List<PaintsResponse>> readAll() {
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK, paintService.readAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<PaintResponse> read(@PathVariable("id") String id) {
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK, paintService.read(id));
    }

    @PatchMapping("/{id}/like")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> like(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        paintService.like(id, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }

    @PatchMapping("/{id}/title")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> changeTitle(@PathVariable("id") String id, @RequestBody ChangeTitleRequest request, @RequestHeader("Authorization") String token) {
        paintService.changeTitle(id, request, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }

    @PatchMapping("/{id}/picture")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> addPicture(@PathVariable("id") String id, @RequestBody AddPictureRequest request, @RequestHeader("Authorization") String token) {
        paintService.addPicture(id, request, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }

    @PatchMapping("/{id}/tag")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> addTag(@PathVariable("id") String id, @RequestBody AddTagRequest request, @RequestHeader("Authorization") String token) {
        paintService.addTag(id, request, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }

    @DeleteMapping("/{id}/picture/{pictureId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> removePicture(@PathVariable("id") String id, @PathVariable("pictureId") String pictureId, @RequestHeader("Authorization") String token) {
        paintService.removePicture(id, pictureId, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }

    @DeleteMapping("/{id}/picture/clear")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> removeAllPicture(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        paintService.removeAllPicture(id, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }

    @DeleteMapping("/{id}/tag/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> removeTag(@PathVariable("id") String id, @PathVariable("tagId") String tagId, @RequestHeader("Authorization") String token) {
        paintService.removeTag(id, tagId, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }

    @DeleteMapping("/{id}/tag/clear")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> removeAllTag(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        paintService.removeAllTag(id, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Object> delete(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {
        paintService.delete(id, token);
        return ResponseData.of(HttpStatus.OK.value(), SUCCESSFUL_OK);
    }
}
