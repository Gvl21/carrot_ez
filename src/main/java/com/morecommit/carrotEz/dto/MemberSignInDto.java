package com.morecommit.carrotEz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSignInDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    }

