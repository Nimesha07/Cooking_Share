package com.mealsocial.mealsocial.Controller;


import com.mealsocial.mealsocial.dto.request.FeedBackRequestDto;
import com.mealsocial.mealsocial.dto.response.ResponseDto;
import com.mealsocial.mealsocial.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    @Autowired
    private final FeedbackService feedbackService;

    // Create a new feedback
    @PostMapping
    public ResponseDto createFeedback(@RequestBody FeedBackRequestDto feedbackRequestDto) {
        return feedbackService.createFeedback(feedbackRequestDto);

    }

    // Get feedback by ID
    @GetMapping("/{id}")
    public ResponseDto getFeedback(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id);
    }

    // Get all feedback
    @GetMapping
    public ResponseDto getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    // Update feedback by ID
    @PutMapping("/{id}")
    public ResponseDto updateFeedback(@PathVariable Long id, @RequestBody FeedBackRequestDto feedbackRequestDto) {
        return feedbackService.updateFeedback(id, feedbackRequestDto);
    }

    // Delete feedback by ID
    @DeleteMapping("/{id}")
    public ResponseDto deleteFeedback(@PathVariable Long id) {
        return feedbackService.deleteFeedback(id);

    }
}
