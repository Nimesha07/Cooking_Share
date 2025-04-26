package com.mealsocial.mealsocial.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mealsocial.mealsocial.domain.Feedback;
import com.mealsocial.mealsocial.domain.Meal;
import com.mealsocial.mealsocial.domain.Post;
import com.mealsocial.mealsocial.domain.Recipe;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto{

    private Long userId;

    private String userName;

    private List<Post> posts;

    private List<Recipe> recipes;

    private List<Feedback> feedbacks;

    private List<Meal> meals;
}
