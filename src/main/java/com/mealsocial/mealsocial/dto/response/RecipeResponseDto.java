package com.mealsocial.mealsocial.dto.response;

import com.mealsocial.mealsocial.domain.Category;
import lombok.Data;

@Data
public class RecipeResponseDto {

    private Long recipeId;

    private String recipeContent;

    private String recipeImage;

    private Category category;

    private UserResponseDto userResponseDto;
}
