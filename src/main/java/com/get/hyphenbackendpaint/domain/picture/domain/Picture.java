package com.get.hyphenbackendpaint.domain.picture.domain;

import com.get.hyphenbackendpaint.domain.paint.domain.Paint;
import com.get.hyphenbackendpaint.domain.picture.enums.ImageType;
import com.get.hyphenbackendpaint.global.jpa.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "picture")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Picture extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private ImageType type;

    @Column(nullable = false)
    private Long number;

    @ManyToOne
    @JoinColumn(name = "paint_id")
    private Paint paint;

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public void update(String title, String content, String image, ImageType type, Long number) {
        this.title = title == null ? this.title : title;
        this.content = content == null ? this.content : content;
        this.image = image == null ? this.image : image;
        this.type = type == null ? this.type : type;
        this.number = number == null ? this.number : number;
    }

    public void removePaint() {
        this.paint = null;
    }
}