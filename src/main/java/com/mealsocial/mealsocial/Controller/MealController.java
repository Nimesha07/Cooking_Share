package com.mealsocial.mealsocial.Controller;

import com.mealsocial.mealsocial.dto.request.MealRequestDto;
import com.mealsocial.mealsocial.dto.response.ResponseDto;
import com.mealsocial.mealsocial.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    // Create Meal
    @PostMapping
    public ResponseDto createMeal(@RequestBody MealRequestDto requestDto) {
        return mealService.createMeal(requestDto);
    }

    // Get Meal by ID
    @GetMapping("/{id}")
    public ResponseDto getMeal(@PathVariable Long id) {
        return mealService.getMealById(id);
    }

    // Get All Meals
    @GetMapping
    public ResponseDto getAllMeals() {
        return mealService.getAllMeals();
    }

    // Update Meal
    @PutMapping("/{id}")
    public ResponseDto updateMeal(@PathVariable Long id, @RequestBody MealRequestDto requestDto) {
        return mealService.updateMeal(id, requestDto);
    }

    // Delete Meal
    @DeleteMapping("/{id}")
    public ResponseDto deleteMeal(@PathVariable Long id) {
        return mealService.deleteMeal(id);
    }
}
