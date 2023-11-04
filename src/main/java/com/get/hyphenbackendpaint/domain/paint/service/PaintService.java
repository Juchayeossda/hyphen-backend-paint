package com.get.hyphenbackendpaint.domain.paint.service;

import com.get.hyphenbackendpaint.domain.paint.domain.Paint;
import com.get.hyphenbackendpaint.domain.paint.domain.repository.PaintRepository;
import com.get.hyphenbackendpaint.domain.paint.exception.AlreadyTitleExistsException;
import com.get.hyphenbackendpaint.domain.paint.exception.PaintNotFoundException;
import com.get.hyphenbackendpaint.domain.paint.presentation.dto.request.*;
import com.get.hyphenbackendpaint.domain.paint.presentation.dto.response.PaintResponse;
import com.get.hyphenbackendpaint.domain.paint.presentation.dto.response.PaintsResponse;
import com.get.hyphenbackendpaint.domain.picture.domain.Picture;
import com.get.hyphenbackendpaint.domain.picture.domain.repository.PictureRepository;
import com.get.hyphenbackendpaint.domain.picture.exception.PictureNotFoundException;
import com.get.hyphenbackendpaint.domain.tag.domain.Tag;
import com.get.hyphenbackendpaint.domain.tag.domain.repository.TagRepository;
import com.get.hyphenbackendpaint.domain.tag.exception.TagNotFoundException;
import com.get.hyphenbackendpaint.global.annotation.ServiceWithTransactionalReadOnly;
import com.get.hyphenbackendpaint.global.exception.global.InvalidRoleExeption;
import com.get.hyphenbackendpaint.global.infra.RestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class PaintService {

    private final PaintRepository paintRepository;
    private final PictureRepository pictureRepository;
    private final TagRepository tagRepository;
    private final RestRequest webClient;

    @Value(value = "${webClient.servers.userServer.path}")
    private String userServerPath;

    public Long validate(String token) {
        return webClient.validate(userServerPath + "/api/token/validate", token).getData();
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(String token, PaintRequest request) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        if (paintRepository.existsByTitle(request.getTitle())) {
            throw AlreadyTitleExistsException.EXCEPTION;
        }
        Set<Picture> pictures = new HashSet<>();
        for (Long pictureId : request.getPictures()) {
            pictures.add(pictureRepository.findById(pictureId).orElseThrow(() -> PictureNotFoundException.EXCEPTION));
        }
        Set<Tag> tags = new HashSet<>();
        for (Long tagId : request.getTags()) {
            tags.add(tagRepository.findById(tagId).orElseThrow(() -> TagNotFoundException.EXCEPTION));
        }
        paintRepository.save(request.toEntity(pictures.isEmpty() ? null : pictures, tags.isEmpty() ? null : tags));
    }

    public List<PaintsResponse> readAll() {
        return paintRepository.findAll().stream().map(PaintsResponse::new).collect(Collectors.toList());
    }

    public PaintResponse read(String id) {
        Paint paint = paintRepository.findById(Long.valueOf(id)).orElseThrow(() -> PaintNotFoundException.EXCEPTION);
        return new PaintResponse(paint.getId(), paint.getTitle(), paint.getLikeCount(), paint.getPictures().stream().map(Picture::getId).collect(Collectors.toList()), paint.getTags().stream().map(Tag::getId).collect(Collectors.toList()), paint.getCreatedDateTime(), paint.getModifiedDateTime());
    }

    @Transactional
    public void like(String id, String token) {
        validate(token);
        Paint paint = paintRepository.findById(Long.valueOf(id)).orElseThrow(() -> PaintNotFoundException.EXCEPTION);
        paint.like();
        paintRepository.save(paint);
    }

    @Transactional
    public void changeTitle(String id, ChangeTitleRequest request, String token) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        Paint paint = paintRepository.findById(Long.valueOf(id)).orElseThrow(() -> PaintNotFoundException.EXCEPTION);
        if (!request.getTitle().equals(paint.getTitle()) && paintRepository.existsByTitle(request.getTitle())) {
            throw AlreadyTitleExistsException.EXCEPTION;
        }
        paint.changeTitle(request.getTitle());
        paintRepository.save(paint);
    }

    @Transactional
    public void addPicture(String id, AddPictureRequest request, String token) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        Paint paint = paintRepository.findById(Long.valueOf(id)).orElseThrow(() -> PaintNotFoundException.EXCEPTION);
        paint.addPicture(pictureRepository.findById(request.getPictureId()).orElseThrow(() -> PictureNotFoundException.EXCEPTION));
        paintRepository.save(paint);
    }

    @Transactional
    public void removePicture(String id, String pictureId, String token) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        Paint paint = paintRepository.findById(Long.valueOf(id)).orElseThrow(() -> PaintNotFoundException.EXCEPTION);
        paint.getPictures().remove(pictureRepository.findById(Long.valueOf(pictureId)).orElseThrow(() -> PictureNotFoundException.EXCEPTION));
        paintRepository.save(paint);
    }

    @Transactional
    public void removeAllPicture(String id, String token) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        Paint paint = paintRepository.findById(Long.valueOf(id)).orElseThrow(() -> PaintNotFoundException.EXCEPTION);
        paint.getPictures().clear();
        paintRepository.save(paint);
    }

    @Transactional
    public void addTag(String id, AddTagRequest request, String token) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        Paint paint = paintRepository.findById(Long.valueOf(id)).orElseThrow(() -> PaintNotFoundException.EXCEPTION);
        paint.getTags().add(tagRepository.findById(request.getTagId()).orElseThrow(() -> TagNotFoundException.EXCEPTION));
        paintRepository.save(paint);
    }

    @Transactional
    public void removeTag(String id, String tagId, String token) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        Paint paint = paintRepository.findById(Long.valueOf(id)).orElseThrow(() -> PaintNotFoundException.EXCEPTION);
        paint.getTags().remove(tagRepository.findById(Long.valueOf(tagId)).orElseThrow(() -> TagNotFoundException.EXCEPTION));
        paintRepository.save(paint);
    }

    @Transactional
    public void removeAllTag(String id, String token) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        Paint paint = paintRepository.findById(Long.valueOf(id)).orElseThrow(() -> PaintNotFoundException.EXCEPTION);
        paint.getTags().clear();
        paintRepository.save(paint);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String id, String token) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        Paint paint = paintRepository.findById(Long.valueOf(id)).orElseThrow(() -> PaintNotFoundException.EXCEPTION);
        for (Picture picture : paint.getPictures()) {
            picture.removePaint();
            pictureRepository.save(picture);
        }
        for (Tag tag : paint.getTags()) {
            tag.removePaint(paint);
            tagRepository.save(tag);
        }
        paintRepository.delete(paint);
    }
}
