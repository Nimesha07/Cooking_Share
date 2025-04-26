package com.mealsocial.mealsocial.service.impl;

import com.mealsocial.mealsocial.domain.Meal;
import com.mealsocial.mealsocial.domain.Recipe;
import com.mealsocial.mealsocial.domain.User;
import com.mealsocial.mealsocial.dto.request.MealRequestDto;
import com.mealsocial.mealsocial.dto.response.MealResponseDto;
import com.mealsocial.mealsocial.dto.response.RecipeResponseDto;
import com.mealsocial.mealsocial.dto.response.ResponseDto;
import com.mealsocial.mealsocial.repo.MealRepository;
import com.mealsocial.mealsocial.repo.RecipeRepository;
import com.mealsocial.mealsocial.service.MealService;
import com.mealsocial.mealsocial.service.UserService;
import com.mealsocial.mealsocial.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final UserService userService;
    private final RecipeRepository recipeRepository;
    private final ResponseService responseService;
    private final RecipeService recipeService;

    @Override
    public ResponseDto createMeal(MealRequestDto requestDto) {
        User user = userService.getUserEntityById(requestDto.getUserId());
        if (user == null) {
            return responseService.validationEvent("Invalid User ID");
        }

        Optional<Recipe> recipeOptional = recipeRepository.findById(requestDto.getRecipeId());
        if (recipeOptional.isEmpty()) {
            return responseService.validationEvent("Invalid Recipe ID");
        }

        Meal meal = new Meal();
        meal.setUser(user);
        meal.setRecipe(recipeOptional.get());
        meal.setDateTime(requestDto.getDateTime());

        return responseService.successEvent(getMealResponseDto(mealRepository.save(meal)));
    }

    @Override
    public ResponseDto getMealById(Long id) {
        Optional<Meal> meal = mealRepository.findById(id);
        return meal.map(value -> responseService.successEvent(getMealResponseDto(value)))
                .orElseGet(() -> responseService.validationEvent("Meal not found"));
    }

    @Override
    public ResponseDto getAllMeals() {
        List<MealResponseDto> meals = mealRepository.findAll().stream()
                .map(this::getMealResponseDto)
                .collect(Collectors.toList());
        return responseService.successEvent(meals);
    }

    @Override
    public ResponseDto updateMeal(Long id, MealRequestDto requestDto) {
        Optional<Meal> mealOptional = mealRepository.findById(id);
        if (mealOptional.isEmpty()) {
            return responseService.validationEvent("Invalid Meal ID");
        }

        Meal meal = mealOptional.get();

        User user = userService.getUserEntityById(requestDto.getUserId());
        if (user == null) {
            return responseService.validationEvent("Invalid User ID");
        }

        Optional<Recipe> recipeOptional = recipeRepository.findById(requestDto.getRecipeId());
        if (recipeOptional.isEmpty()) {
            return responseService.validationEvent("Invalid Recipe ID");
        }

        meal.setUser(user);
        meal.setRecipe(recipeOptional.get());
        meal.setDateTime(requestDto.getDateTime());

        return responseService.successEvent(getMealResponseDto(mealRepository.save(meal)));
    }

    @Override
    public ResponseDto deleteMeal(Long id) {
        if (!mealRepository.existsById(id)) {
            return responseService.validationEvent("Invalid Meal ID");
        }
        mealRepository.deleteById(id);
        return responseService.successEvent("Meal deleted successfully");
    }

    // Helper
    private MealResponseDto getMealResponseDto(Meal meal) {
        MealResponseDto dto = new MealResponseDto();
        dto.setMealId(meal.getMealId());
        dto.setDateTime(meal.getDateTime());
        dto.setUser(userService.getUserResponseById(meal.getUser().getUserId()));
        dto.setRecipe((RecipeResponseDto) recipeService.getRecipeById(meal.getRecipe().getRecipeId()).getObject()); // Add this method in RecipeService
        return dto;
    }
}
