package com.mealsocial.mealsocial.dto.request;

import lombok.Data;

@Data
public class RecipeRequestDto {

    private Long recipeId;

    private String recipeImage;

    private String recipeContent;

    private CategoryRequestDto categoryRequestDto;

    private Long userId;
}
