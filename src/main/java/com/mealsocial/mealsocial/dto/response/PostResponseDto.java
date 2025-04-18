package com.mealsocial.mealsocial.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class PostResponseDto {

    private Long postId;

    private String postImage;
    private String postContent;

    private List<String> tags;

    private UserResponseDto userResponseDto;

    private List<FeedBackResponseDto> feedBackResponseDtoList;
}
