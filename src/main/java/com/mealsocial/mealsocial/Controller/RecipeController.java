package com.mealsocial.mealsocial.Controller;

import com.mealsocial.mealsocial.domain.Recipe;
import com.mealsocial.mealsocial.dto.request.RecipeRequestDto;
import com.mealsocial.mealsocial.dto.response.ResponseDto;
import com.mealsocial.mealsocial.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @PostMapping
    public ResponseDto createRecipe(@RequestBody RecipeRequestDto requestDto) {
        return recipeService.createRecipe(requestDto);
    }

    @GetMapping("/{id}")
    public ResponseDto getRecipe(@PathVariable Long id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping
    public ResponseDto getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @PutMapping
    public ResponseDto updateRecipe(@RequestBody RecipeRequestDto requestDto){
        return recipeService.updateRecipe(requestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteRecipe(@PathVariable Long id) {
        return recipeService.deleteRecipe(id);
    }
}
