package com.mealsocial.mealsocial.service;

import com.mealsocial.mealsocial.domain.Recipe;
import com.mealsocial.mealsocial.dto.request.RecipeRequestDto;
import com.mealsocial.mealsocial.dto.response.ResponseDto;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    ResponseDto createRecipe(RecipeRequestDto recipe);
    ResponseDto getRecipeById(Long id);
    ResponseDto getAllRecipes();
    ResponseDto updateRecipe(RecipeRequestDto requestDto);
    ResponseDto deleteRecipe(Long recipeId);
}