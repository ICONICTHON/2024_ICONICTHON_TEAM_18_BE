package org.dongguk.onroad.roadmap.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EStatus {

    EMPTY("파일이 존재하지 않음"),
    LOADING("파일 업로드중"),
    COMPLETED("조회가능상태"),

    ;

    private final String koName;

    public static EStatus fromString(String status) {
        return switch (status) {
            case "EMPTY" -> EMPTY;
            case "LOADING" -> LOADING;
            case "COMPLETED" -> COMPLETED;
            default -> throw new IllegalArgumentException("Invalid Status: " + status);
        };
    }
}
