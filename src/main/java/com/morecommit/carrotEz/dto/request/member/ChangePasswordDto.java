package com.morecommit.carrotEz.dto.request.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {
    private String email;
    private String exPassword;
    private String newPassword;
}
