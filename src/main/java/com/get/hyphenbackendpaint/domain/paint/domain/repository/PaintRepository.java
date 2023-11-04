package com.get.hyphenbackendpaint.domain.paint.domain.repository;

import com.get.hyphenbackendpaint.domain.paint.domain.Paint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaintRepository extends JpaRepository<Paint, Long> {

    boolean existsByTitle(String title);
}
