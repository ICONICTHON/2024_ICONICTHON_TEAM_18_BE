package org.dongguk.onroad.loadmap.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EStatus {

    PENDING("대기중"),
    COMPLETED("완료"),

    ;

    private final String koName;

    public static EStatus fromString(String status) {
        return switch (status) {
            case "대기중" -> PENDING;
            case "완료" -> COMPLETED;
            default -> throw new IllegalArgumentException("Invalid Status: " + status);
        };
    }
}
