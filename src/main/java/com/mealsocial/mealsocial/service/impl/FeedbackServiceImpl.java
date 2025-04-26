package com.mealsocial.mealsocial.service.impl;

import com.mealsocial.mealsocial.domain.Feedback;
import com.mealsocial.mealsocial.domain.Post;
import com.mealsocial.mealsocial.dto.request.FeedBackRequestDto;
import com.mealsocial.mealsocial.dto.response.FeedBackResponseDto;
import com.mealsocial.mealsocial.dto.response.ResponseDto;
import com.mealsocial.mealsocial.repo.FeedbackRepository;
import com.mealsocial.mealsocial.repo.PostRepository;
import com.mealsocial.mealsocial.service.FeedbackService;

import com.mealsocial.mealsocial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    ResponseService responseService;

    @Override
    public ResponseDto createFeedback(FeedBackRequestDto feedbackRequestDto) {
        // Validate if user exists
        if (userService.getUserEntityById(feedbackRequestDto.getUserId()) == null) {
            return responseService.validationEvent("Invalid User ID");
        }

        // Validate if the post exists
        if (!postRepository.existsById(feedbackRequestDto.getPostId())) {
            return responseService.validationEvent("Invalid Post ID");
        }

        // Mapping DTO to entity
        Feedback feedback = new Feedback();
        feedback.setContent(feedbackRequestDto.getContent());
        feedback.setUser(userService.getUserEntityById(feedbackRequestDto.getUserId()));
        Post post = postRepository.findById(feedbackRequestDto.getPostId()).get();  // Since we validated the existence
        feedback.setPost(post);

        // Save feedback and return the response
        Feedback savedFeedback = feedbackRepository.save(feedback);
        return responseService.successEvent(getFeedbackResponseDto(savedFeedback));
    }

    @Override
    public ResponseDto getFeedbackById(Long id) {
        // Find the feedback by ID
        Optional<Feedback> feedbackOptional = feedbackRepository.findById(id);
        if (feedbackOptional.isPresent()) {
            return responseService.successEvent(getFeedbackResponseDto(feedbackOptional.get()));
        } else {
            return responseService.validationEvent("Feedback not found");
        }
    }

    @Override
    public ResponseDto getAllFeedback() {
        // Get all feedbacks
        List<Feedback> allFeedback = feedbackRepository.findAll();
        if (allFeedback.isEmpty()) {
            return responseService.validationEvent("No feedback available");
        }

        // Map the entities to response DTOs
        List<FeedBackResponseDto> responseDtos = allFeedback.stream()
                .map(this::getFeedbackResponseDto)
                .collect(Collectors.toList());
        return responseService.successEvent(responseDtos);
    }

    @Override
    public ResponseDto deleteFeedback(Long id) {
        // Check if the feedback exists
        if (!feedbackRepository.existsById(id)) {
            return responseService.validationEvent("Invalid Feedback ID");
        }

        // Proceed to delete the feedback
        feedbackRepository.deleteById(id);
        return responseService.successEvent("Feedback deleted successfully");
    }

    @Override
    public ResponseDto updateFeedback(Long id, FeedBackRequestDto feedbackRequestDto) {
        // Validate if user exists


        if (userService.getUserEntityById(feedbackRequestDto.getUserId()) == null) {
            return responseService.validationEvent("Invalid User ID");
        }

        // Validate if the post exists
        if (!postRepository.existsById(feedbackRequestDto.getPostId())) {
            return responseService.validationEvent("Invalid Post ID");
        }

        // Fetch existing feedback
        Optional<Feedback> feedbackOptional = feedbackRepository.findById(id);
        if (!feedbackOptional.isPresent()) {
            return responseService.validationEvent("Feedback not found");
        }

        Long userId = feedbackOptional.get().getUser().getUserId();
        if(!feedbackRequestDto.getUserId().equals(userId)){
            return responseService.validationEvent("The user who posted the feedback needs to edit it");
        }

        Feedback feedback = feedbackOptional.get();
        feedback.setContent(feedbackRequestDto.getContent());
        feedback.setUser(userService.getUserEntityById(feedbackRequestDto.getUserId()));
        Post post = postRepository.findById(feedbackRequestDto.getPostId()).get();
        feedback.setPost(post);

        // Save updated feedback
        Feedback updatedFeedback = feedbackRepository.save(feedback);
        return responseService.successEvent(getFeedbackResponseDto(updatedFeedback));
    }

    // Helper method to convert Feedback entity to FeedbackResponseDto
    private FeedBackResponseDto getFeedbackResponseDto(Feedback feedback) {
        FeedBackResponseDto responseDto = new FeedBackResponseDto();
        responseDto.setFeedbackId(feedback.getFeedbackId());
        responseDto.setContent(feedback.getContent());
        responseDto.setUserResponseDto(userService.getUserResponseById(feedback.getUser().getUserId()));
        return responseDto;
    }
}
