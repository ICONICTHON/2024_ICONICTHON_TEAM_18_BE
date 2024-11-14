package org.dongguk.onroad.roadmap.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EQuizType {
    MULTIPLE_CHOICE("객관식", "MULTIPLE_CHOICE"),
    DESCRIPTIVE("서술형", "DESCRIPTIVE");

    private final String koName;
    private final String enName;

    public static EQuizType of(String type) {
        for (EQuizType eQuizType : values()) {
            if (eQuizType.getEnName().equals(type)) {
                return eQuizType;
            }
        }
        throw new IllegalArgumentException("Invalid Quiz Type: " + type);
    }
}
