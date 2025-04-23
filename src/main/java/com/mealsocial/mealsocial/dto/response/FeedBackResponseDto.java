package com.mealsocial.mealsocial.dto.response;
import lombok.Data;

@Data
public class FeedBackResponseDto{

    private Long feedbackId;

    private String content;

    private UserResponseDto userResponseDto;
}
