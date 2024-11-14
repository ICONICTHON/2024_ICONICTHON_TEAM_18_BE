package org.dongguk.onroad.security.application.usecase;

import org.dongguk.onroad.core.annotation.bean.UseCase;
import org.dongguk.onroad.security.application.dto.response.ReadUserBriefResponseDto;

import java.util.UUID;

@UseCase
public interface ReadUserBriefUseCase {

    /**
     * 2.2 Security에서 사용되는 유스케이스. 사용자의 간단한 정보를 읽어옴
     * @param userId 계정 ID
     * @return ReadUserBriefResponseDto 사용자 간단한 정보
     */
    ReadUserBriefResponseDto execute(UUID userId);
}
