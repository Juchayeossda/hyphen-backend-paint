package com.get.hyphenbackendpaint.domain.tag.service;

import com.get.hyphenbackendpaint.domain.paint.domain.Paint;
import com.get.hyphenbackendpaint.domain.tag.presentation.dto.response.TagResponse;
import com.get.hyphenbackendpaint.domain.tag.presentation.dto.response.TagsResponse;
import com.get.hyphenbackendpaint.domain.tag.domain.Tag;
import com.get.hyphenbackendpaint.domain.tag.domain.repository.TagRepository;
import com.get.hyphenbackendpaint.domain.tag.exception.AlreadyNameExistsException;
import com.get.hyphenbackendpaint.domain.tag.exception.TagNotFoundException;
import com.get.hyphenbackendpaint.domain.tag.presentation.dto.request.TagRequest;
import com.get.hyphenbackendpaint.global.annotation.ServiceWithTransactionalReadOnly;
import com.get.hyphenbackendpaint.global.exception.global.InvalidRoleExeption;
import com.get.hyphenbackendpaint.global.infra.RestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final RestRequest webClient;

    @Value(value = "${webClient.servers.userServer.path}")
    private String userServerPath;

    public Long validate(String token) {
        return webClient.validate(userServerPath + "/api/token/validate", token).getData();
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(String token, TagRequest request) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        if (tagRepository.existsByName(request.getName())) {
            throw AlreadyNameExistsException.EXCEPTION;
        }
        tagRepository.save(request.toEntity());
    }

    public List<TagsResponse> readAll() {
        return tagRepository.findAll().stream().map(TagsResponse::new).collect(Collectors.toList());
    }

    public TagResponse read(String id) {
        Tag tag = tagRepository.findById(Long.valueOf(id)).orElseThrow(() -> TagNotFoundException.EXCEPTION);
        return new TagResponse(tag.getId(), tag.getName(), tag.getPaints().stream().map(Paint::getId).collect(Collectors.toList()), tag.getCreatedDateTime(), tag.getModifiedDateTime());
    }

    @Transactional
    public void update(String id, TagRequest request, String token) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        Tag tag = tagRepository.findById(Long.valueOf(id)).orElseThrow(() -> TagNotFoundException.EXCEPTION);
        if (!request.getName().equals(tag.getName()) && tagRepository.existsByName(request.getName())) {
            throw AlreadyNameExistsException.EXCEPTION;
        }
        tag.update(request.getName());
        tagRepository.save(tag);
    }


    @Transactional(rollbackFor = Exception.class)
    public void delete(String id, String token) {
        if (validate(token) != 1L) {
            throw InvalidRoleExeption.EXCEPTION;
        }
        Tag tag = tagRepository.findById(Long.valueOf(id)).orElseThrow(() -> TagNotFoundException.EXCEPTION);
        for (Paint paint : tag.getPaints()) {
            paint.removeTag(tag);
        }
        tagRepository.delete(tag);
    }
}
