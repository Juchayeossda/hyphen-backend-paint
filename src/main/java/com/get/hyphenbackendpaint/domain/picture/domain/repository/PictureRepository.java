package com.get.hyphenbackendpaint.domain.picture.domain.repository;

import com.get.hyphenbackendpaint.domain.picture.domain.Picture;
import com.get.hyphenbackendpaint.domain.picture.enums.ImageType;
import com.get.hyphenbackendpaint.domain.picture.presentation.dto.response.PicturesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    boolean existsByTitle(String title);
    Optional<List<Picture>> findAllByType(ImageType type);
}
