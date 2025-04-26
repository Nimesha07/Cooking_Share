package com.mealsocial.mealsocial.dto.request;

import lombok.Data;
@Data
public class UserRequestDto {

    private Long userId;

    private String userName;

    private String email;

    private String password;

    private String profilePicture;
}
