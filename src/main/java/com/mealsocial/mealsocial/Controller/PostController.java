package com.mealsocial.mealsocial.Controller;

import com.mealsocial.mealsocial.domain.Post;
import com.mealsocial.mealsocial.dto.request.PostRequestDto;
import com.mealsocial.mealsocial.dto.response.PostResponseDto;
import com.mealsocial.mealsocial.dto.response.ResponseDto;
import com.mealsocial.mealsocial.service.PostService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping
    public ResponseDto createPost(@RequestBody PostRequestDto post) throws NoSuchObjectException {
        return postService.createPost(post);
    }

    @PutMapping
    public ResponseDto updatePost(@RequestBody PostRequestDto post) throws NoSuchObjectException {
        return postService.updatePost(post);
    }

    @GetMapping("/{id}")
    public ResponseDto getPost(@PathVariable @Positive(message = "Post ID must be a positive number.") Long id) {
        return postService.getPostById(id);
    }

    @GetMapping("/response/{id}")
    public ResponseDto getPostResponse(@PathVariable @Positive(message = "Post ID must be a positive number.") Long id) {
        return postService.getPostResponseById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseDto deletePost(@PathVariable @Positive(message = "Post ID must be positive") Long id) {
        return postService.deletePost(id);
    }

    @GetMapping
    public ResponseDto getAllPosts() {
        return postService.getAllPosts();
    }
}