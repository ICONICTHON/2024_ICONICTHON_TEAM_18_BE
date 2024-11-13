package org.dongguk.onroad.loadmap.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ESemester {
    SPRING("1학기", "SPRING"),
    SUMMER("여름학기", "SUMMER"),
    FALL("2학기", "FALL"),
    WINTER("겨울학기", "WINTER")
    ;

    private final String koName;
    private final String enName;

    public static ESemester fromString(String value) {
        return switch (value.toUpperCase()) {
            case "SPRING" -> SPRING;
            case "SUMMER" -> SUMMER;
            case "FALL" -> FALL;
            case "WINTER" -> WINTER;
            default -> throw new IllegalArgumentException("Semester가 잘못되었습니다.");
        };
    }
}
