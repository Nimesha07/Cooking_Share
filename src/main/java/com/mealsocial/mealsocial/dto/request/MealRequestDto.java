package com.mealsocial.mealsocial.dto.request;

import com.mealsocial.mealsocial.domain.Recipe;
import com.mealsocial.mealsocial.domain.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MealRequestDto {

    private Long mealId;

    private LocalDateTime dateTime;

    private Long recipeId;

    private Long userId;
}
