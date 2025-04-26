package com.mealsocial.mealsocial.service;

import com.mealsocial.mealsocial.dto.request.PostRequestDto;
import com.mealsocial.mealsocial.dto.response.ResponseDto;

import java.rmi.NoSuchObjectException;

public interface PostService {
    ResponseDto createPost(PostRequestDto post) throws NoSuchObjectException;
    ResponseDto getPostById(Long id);
    ResponseDto getAllPosts();
    ResponseDto updatePost(PostRequestDto post) throws NoSuchObjectException;
    ResponseDto getPostResponseById(Long id);
    ResponseDto deletePost(Long id);
}