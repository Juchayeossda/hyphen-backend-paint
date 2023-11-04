package com.get.hyphenbackendpaint.global.statics;

public class ValidMessageConstants {

    private ValidMessageConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PAINT_TITLE_NOT_BLANK = "그림 제목은 Null, 공백, 띄어쓰기를 허용하지 않습니다.";

    public static final String TAG_NAME_NOT_BLANK = "태그 이름은 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String TAG_ID_NOT_BLANK = "태그 아이디는 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String TAG_NOT_NULL = "태그 아이디는 Null을 허용하지 않습니다.";

    public static final String PICTURE_TITLE_NOT_BLANK = "이미지 제목은 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String PICTURE_NOT_NULL = "이미지 제목은 Null을 허용하지 않습니다.";
    public static final String PICTURE_CONTENT_NOT_BLANK = "이미지 내용은 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String PICTURE_IMAGE_NOT_BLANK = "이미지는 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String PICTURE_NUMBER_NOT_BLANK = "이미지 번호는 Null, 공백, 띄어쓰기를 허용하지 않습니다.";
    public static final String PICTURE_ID_NOT_BLANK = "이미지 아이디는 Null, 공백, 띄어쓰기를 허용하지 않습니다.";

    public static final String PICTURE_TYPE_NOT_NULL = "이미지 클래스는 Null을 허용하지 않습니다.";
    public static final String PICTURE_TYPE_ENUM_VALUE = "이미지 클래스는 Enum 값 중 하나여야 합니다.";
}
