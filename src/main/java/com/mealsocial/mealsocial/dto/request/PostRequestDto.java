package com.mealsocial.mealsocial.dto.request;

import com.mealsocial.mealsocial.dto.response.FeedBackResponseDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class PostRequestDto {

    private Long postId;

    private String postImage;
    @NotNull(message = "post content can't be null")
    private String postContent;

    private List<String> tags;

    @NotNull(message = "UserId can't be empty")
    @NotEmpty(message = "UserId can't be empty")
    private Long userId;

    private FeedBackResponseDto feedBackResponseDto;

}