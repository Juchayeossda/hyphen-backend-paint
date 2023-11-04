package com.get.hyphenbackendpaint.domain.picture.service;

import com.get.hyphenbackendpaint.domain.paint.domain.Paint;
import com.get.hyphenbackendpaint.domain.picture.domain.Picture;
import com.get.hyphenbackendpaint.domain.picture.domain.repository.PictureRepository;
import com.get.hyphenbackendpaint.domain.picture.exception.AlreadyTitleExistsException;
import com.get.hyphenbackendpaint.domain.picture.exception.PictureNotFoundException;
import com.get.hyphenbackendpaint.domain.picture.presentation.dto.request.PictureRequest;
import com.get.hyphenbackendpaint.domain.picture.presentation.dto.response.PictureResponse;
import com.get.hyphenbackendpaint.domain.picture.presentation.dto.response.PicturesResponse;
import com.get.hyphenbackendpaint.global.annotation.ServiceWithTransactionalReadOnly;
import com.get.hyphenbackendpaint.global.exception.global.InvalidRoleExeption;
import com.get.hyphenbackendpaint.global.infra.RestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class PictureService {

    private final PictureRepository pictureRepository;
    private final RestRequest webClient;

    @Value(value = "${webClient.servers.userServer.path}")
    private String userServerPath;

    public Long validate(String token) {
        return webClient.validate(userServerPath + "/api/token/validate", token).getData();
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(String token, PictureRequest request) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        if (pictureRepository.existsByTitle(request.getTitle())) {
            throw AlreadyTitleExistsException.EXCEPTION;
        }
        pictureRepository.save(request.toEntity());
    }

    public List<PicturesResponse> readAll() {
        List<PicturesResponse> pictures = new ArrayList<>();
        for (Picture picture : pictureRepository.findAll()) {
            pictures.add(new PicturesResponse(picture.getId(), picture.getTitle(), picture.getContent(), picture.getImage(), picture.getType(), picture.getNumber()));
        }
        return pictures;
    }

    public PictureResponse read(String id) {
        Picture picture = pictureRepository.findById(Long.valueOf(id)).orElseThrow(() -> PictureNotFoundException.EXCEPTION);
        return new PictureResponse(picture.getId(), picture.getTitle(), picture.getContent(), picture.getImage(), picture.getType(), picture.getNumber(), picture.getPaint() == null ? null : picture.getPaint().getId(), picture.getCreatedDateTime(), picture.getModifiedDateTime());
    }

    @Transactional
    public void update(String id, PictureRequest request, String token) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        Picture picture = pictureRepository.findById(Long.valueOf(id)).orElseThrow(() -> PictureNotFoundException.EXCEPTION);
        if (!request.getTitle().equals(picture.getTitle()) && pictureRepository.existsByTitle(request.getTitle())) {
            throw AlreadyTitleExistsException.EXCEPTION;
        }
        picture.update(request.getTitle(), request.getContent(), request.getImage(), request.getType(), request.getNumber());
        pictureRepository.save(picture);
    }


    @Transactional(rollbackFor = Exception.class)
    public void delete(String id, String token) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        Picture picture = pictureRepository.findById(Long.valueOf(id)).orElseThrow(() -> PictureNotFoundException.EXCEPTION);
        Paint paint = picture.getPaint();
        if (paint != null) {
            paint.removePicture(picture);
        }
        pictureRepository.deleteById(Long.valueOf(id));
    }
}
