package com.get.hyphenbackendpaint.domain.tag.domain;

import com.get.hyphenbackendpaint.domain.paint.domain.Paint;
import com.get.hyphenbackendpaint.global.jpa.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Paint> paints = new HashSet<>();

    public void update(String name) {
        this.name = name == null ? this.name : name;
    }

    @Builder
    public Tag(String name, Set<Paint> paints) {
        this.name = name;
        this.paints = paints;
    }

    public void removePaint(Paint paint) {
        this.paints.remove(paint);
    }
}
