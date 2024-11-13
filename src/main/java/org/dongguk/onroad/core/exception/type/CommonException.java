package org.dongguk.onroad.core.exception.type;

import org.dongguk.onroad.core.exception.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommonException extends RuntimeException {

    private final ErrorCode errorCode;

    @Override
    public String getMessage() {
        return errorCode.getMessage();
    }
}
