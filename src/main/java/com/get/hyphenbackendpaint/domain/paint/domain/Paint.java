package com.get.hyphenbackendpaint.domain.paint.domain;

import com.get.hyphenbackendpaint.domain.picture.domain.Picture;
import com.get.hyphenbackendpaint.domain.tag.domain.Tag;
import com.get.hyphenbackendpaint.global.jpa.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "paint")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Paint extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private Long likeCount;

    @OneToMany(mappedBy = "paint", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Picture> pictures = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "paint_tag",
            joinColumns = @JoinColumn(name = "paint_tag"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.likeCount = this.likeCount == null ? 0 : this.likeCount;
    }

    public void changeTitle(String title) {
        this.title = title == null ? this.title : title;
    }

    public void like() {
        this.likeCount += 1L;
    }

    public void addPicture(Picture picture) {
        picture.setPaint(this);
        this.pictures.add(picture);
    }

    @Builder
    public Paint(String title, Set<Picture> pictures, Set<Tag> tags) {
        this.title = title;
        this.pictures = pictures;
        this.tags = tags;
    }

    public void removePicture(Picture picture) {
        this.pictures.remove(picture);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }
}