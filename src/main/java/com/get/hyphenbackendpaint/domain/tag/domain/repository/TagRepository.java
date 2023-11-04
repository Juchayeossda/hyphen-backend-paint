package com.get.hyphenbackendpaint.domain.tag.domain.repository;

import com.get.hyphenbackendpaint.domain.tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    boolean existsByName(String name);
}
