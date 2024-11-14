package org.dongguk.onroad.security.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.dongguk.onroad.core.dto.SelfValidating;
import org.dongguk.onroad.security.domain.mysql.User;

public class ReadUserBriefResponseDto extends SelfValidating<ReadUserBriefResponseDto> {

    @JsonProperty("name")
    @NotNull
    private final String name;

    @JsonProperty("role")
    @NotNull
    private final String role;

    @Builder
    public ReadUserBriefResponseDto(String name, String role) {
        this.name = name;
        this.role = role;
        this.validateSelf();
    }

    public static ReadUserBriefResponseDto fromEntity(User user) {
        return ReadUserBriefResponseDto.builder()
                .name(user.getName())
                .role(user.getRole().toString())
                .build();
    }

}
