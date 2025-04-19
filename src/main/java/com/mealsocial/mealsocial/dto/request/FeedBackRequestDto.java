package com.mealsocial.mealsocial.dto.request;

import lombok.Data;

@Data
public class FeedBackRequestDto {

     private Long feedbackId;

     private String content;

     private Long userId;

     private Long postId;
}
