package com.mealsocial.mealsocial.service.impl;

import com.mealsocial.mealsocial.domain.Feedback;
import com.mealsocial.mealsocial.domain.Post;
import com.mealsocial.mealsocial.domain.User;
import com.mealsocial.mealsocial.dto.request.PostRequestDto;
import com.mealsocial.mealsocial.dto.response.FeedBackResponseDto;
import com.mealsocial.mealsocial.dto.response.PostResponseDto;
import com.mealsocial.mealsocial.dto.response.ResponseDto;
import com.mealsocial.mealsocial.dto.response.UserResponseDto;
import com.mealsocial.mealsocial.repo.PostRepository;
import com.mealsocial.mealsocial.service.PostService;
import com.mealsocial.mealsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    ResponseService responseService;

    @Override
    public ResponseDto createPost(PostRequestDto posts){

        if(userService.getUserEntityById(posts.getUserId())==null){
            return responseService.validationEvent("Invalid User id");
        }
        Post post = new Post();
        post.setPostContent(posts.getPostContent());
        post.setPostImage(posts.getPostImage());
        post.setFeedbacks(post.getFeedbacks());
        post.setTags(post.getTags());
        post.setUser(userService.getUserEntityById(posts.getUserId()));
        postRepository.save(post);
        
        return responseService.successEvent(getPostResponseDto(post));
    }

    @Override
    public ResponseDto getPostById(Long id) {
        try {
            Optional<Post> byId = postRepository.findById(id);
            return responseService.successEvent(byId.get());
        } catch (InvalidDataAccessApiUsageException | NoSuchElementException e) {
            return responseService.validationEvent("Invalid PostId");
        }
    }

    public ResponseDto getPostResponseById(Long id) {
        try {
            Optional<Post> postOptional = postRepository.findById(id);
            Post post = postOptional.get();
            PostResponseDto postResponseDto = getPostResponseDto(post);
            return responseService.successEvent(postResponseDto);
        } catch (InvalidDataAccessApiUsageException | NoSuchElementException e) {
            return responseService.validationEvent("Invalid PostId");
        }
    }

    private PostResponseDto getPostResponseDto(Post post) {
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setPostContent(post.getPostContent());
        postResponseDto.setPostImage(post.getPostImage());
        postResponseDto.setPostId(post.getPostId());
        postResponseDto.setTags(post.getTags());
        UserResponseDto userById = userService.getUserById(post.getUser().getUserId());
        userById.setPosts(null);
        userById.setFeedbacks(null);
        userById.setMeals(null);
        userById.setRecipes(null);
        userById.setUserId(userById.getUserId());
        postResponseDto.setUserResponseDto(userById);
        List<FeedBackResponseDto> feedBackResponseList = new ArrayList<>();
        for (Feedback feedback : post.getFeedbacks()) {
            FeedBackResponseDto feedBackResponseDto = new FeedBackResponseDto();
            feedBackResponseDto.setFeedbackId(feedback.getFeedbackId());
            feedBackResponseDto.setContent(feedback.getContent());
            userById = userService.getUserById(feedback.getUser().getUserId());
            userById.setPosts(null);
            userById.setFeedbacks(null);
            userById.setMeals(null);
            userById.setRecipes(null);
            feedBackResponseDto.setUserResponseDto(userById);
            feedBackResponseList.add(feedBackResponseDto);
        }
        postResponseDto.setFeedBackResponseDtoList(feedBackResponseList);
        return postResponseDto;
    }


    @Override
    public ResponseDto getAllPosts() {
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for(Post post:postRepository.findAll()){
            PostResponseDto postResponseDto = getPostResponseDto(post);
            postResponseDtoList.add(postResponseDto);
        }
        return responseService.successEvent(postResponseDtoList);
    }

    @Override
    public ResponseDto updatePost(PostRequestDto posts) throws NoSuchObjectException {
        Post post = new Post();
        if(userService.getUserEntityById(posts.getUserId())==null){
            return responseService.validationEvent("Invalid User id");
        }
        if(posts.getPostId()<=0){
            return responseService.validationEvent("Invalid Post id");
        }
        if(getPostById(posts.getPostId()).getObject()==null){
            return responseService.validationEvent("Invalid Post id");
        }
        post.setPostId(posts.getPostId());
        post.setPostContent(posts.getPostContent());
        post.setPostImage(posts.getPostImage());
        post.setFeedbacks(post.getFeedbacks());
        post.setTags(post.getTags());
        post.setUser(userService.getUserEntityById(posts.getUserId()));
        postRepository.save(post);
        return responseService.successEvent(getPostResponseDto(post));
    }

    @Override
    public ResponseDto deletePost(Long id) {
        try {
            Optional<Post> postOptional = postRepository.findById(id);
            if (postOptional.isPresent()) {
                postRepository.deleteById(id);
                return responseService.successEvent("Post deleted successfully");
            } else {
                return responseService.validationEvent("Post not found");
            }
        } catch (Exception e) {
            return responseService.validationEvent("Error while deleting post");
        }
    }

}