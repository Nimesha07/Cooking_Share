
package com.mealsocial.mealsocial.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MealResponseDto {
    private Long mealId;
    private LocalDateTime dateTime;
    private RecipeResponseDto recipe;
    private UserResponseDto user;
}