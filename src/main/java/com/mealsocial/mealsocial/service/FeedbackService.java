package com.mealsocial.mealsocial.service;

import com.mealsocial.mealsocial.domain.Feedback;
import com.mealsocial.mealsocial.dto.request.FeedBackRequestDto;
import com.mealsocial.mealsocial.dto.response.ResponseDto;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {
    ResponseDto createFeedback(FeedBackRequestDto feedbackRequestDto);

    // Get feedback by ID
    ResponseDto getFeedbackById(Long id);

    // Get all feedback
    ResponseDto getAllFeedback();

    // Update feedback by ID
    ResponseDto updateFeedback(Long id, FeedBackRequestDto feedbackRequestDto);

    // Delete feedback by ID
    ResponseDto deleteFeedback(Long id);
}