package com.morecommit.carrotEz.dto.request.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSignInRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    }

