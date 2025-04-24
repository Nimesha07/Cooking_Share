package com.mealsocial.mealsocial.service;

import com.mealsocial.mealsocial.domain.Meal;
import com.mealsocial.mealsocial.dto.request.MealRequestDto;
import com.mealsocial.mealsocial.dto.response.ResponseDto;

import java.util.List;
import java.util.Optional;

public interface MealService {
    ResponseDto createMeal(MealRequestDto requestDto);
    ResponseDto getMealById(Long id);
    ResponseDto getAllMeals();
    ResponseDto updateMeal(Long id, MealRequestDto requestDto);
    ResponseDto deleteMeal(Long id);
}
