package com.mealsocial.mealsocial.service.impl;

import com.mealsocial.mealsocial.domain.Category;
import com.mealsocial.mealsocial.domain.Recipe;
import com.mealsocial.mealsocial.dto.request.RecipeRequestDto;
import com.mealsocial.mealsocial.dto.response.RecipeResponseDto;
import com.mealsocial.mealsocial.dto.response.ResponseDto;
import com.mealsocial.mealsocial.repo.CategoryRepository;
import com.mealsocial.mealsocial.repo.RecipeRepository;
import com.mealsocial.mealsocial.service.RecipeService;
import com.mealsocial.mealsocial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserService userService;

    @Autowired
    ResponseService responseService;

    @Override
    public ResponseDto createRecipe(RecipeRequestDto requestDto) {
        Category category =null;
        if(userService.getUserEntityById(requestDto.getUserId())==null){
            return responseService.validationEvent("Invalid User id");
        }
        Recipe recipe = new Recipe();
        recipe.setRecipeContent(requestDto.getRecipeContent());
        recipe.setRecipeImage(requestDto.getRecipeImage());
        recipe.setUser(userService.getUserEntityById(requestDto.getUserId()));
        if(!categoryRepository.existsById(requestDto.getCategoryRequestDto().getCategoryId())){
            category = new Category();
            category.setCategoryName(requestDto.getCategoryRequestDto().getCategoryName());
            category = categoryRepository.save(category);
        }else{
            category = categoryRepository.findById(requestDto.getCategoryRequestDto().getCategoryId()).get();
        }
        recipe.setCategory(category);
        return responseService.successEvent(getRecipeResponseDto(recipeRepository.save(recipe)));
    }

    @Override
    public ResponseDto getRecipeById(Long id) {
        if(recipeRepository.existsById(id)){
            RecipeResponseDto responseDto = getRecipeResponseDto(id);
            return responseService.successEvent(responseDto);
        }else{
            return responseService.validationEvent("invalid Recipe Id");
        }
    }

    private RecipeResponseDto getRecipeResponseDto(Long id) {
        Recipe recipe = recipeRepository.findById(id).get();
        return getRecipeResponseDto(recipe);
    }

    private RecipeResponseDto getRecipeResponseDto(Recipe recipe) {
        RecipeResponseDto responseDto = new RecipeResponseDto();
        responseDto.setRecipeId(recipe.getRecipeId());
        responseDto.setRecipeContent(recipe.getRecipeContent());
        responseDto.setRecipeImage(recipe.getRecipeImage());
        responseDto.setCategory(recipe.getCategory());
        responseDto.setUserResponseDto(userService.getUserResponseById(recipe.getUser().getUserId()));
        return responseDto;
    }

    @Override
    public ResponseDto getAllRecipes() {
        List<RecipeResponseDto> recipeResponseDtoList = new ArrayList<>();
        for(Recipe recipe:recipeRepository.findAll()){
            recipeResponseDtoList.add(getRecipeResponseDto(recipe));
        }
        return responseService.successEvent(recipeResponseDtoList);
    }

    @Override
    public ResponseDto updateRecipe(RecipeRequestDto requestDto) {

        if (!recipeRepository.existsById(requestDto.getRecipeId())) {
            return responseService.validationEvent("Invalid Recipe ID");
        }

        if (userService.getUserEntityById(requestDto.getUserId()) == null) {
            return responseService.validationEvent("Invalid User ID");
        }
        Optional<Recipe> recipeOptional = recipeRepository.findById(requestDto.getRecipeId());
        Recipe recipe = recipeOptional.get();
        recipe.setRecipeContent(requestDto.getRecipeContent());
        recipe.setRecipeImage(requestDto.getRecipeImage());
        recipe.setUser(userService.getUserEntityById(requestDto.getUserId()));

        Category category;
        if (!categoryRepository.existsById(requestDto.getCategoryRequestDto().getCategoryId())) {
            category = new Category();
            category.setCategoryName(requestDto.getCategoryRequestDto().getCategoryName());
            category = categoryRepository.save(category);
        } else {
            category = categoryRepository.findById(requestDto.getCategoryRequestDto().getCategoryId()).get();
        }
        recipe.setCategory(category);

        return responseService.successEvent(getRecipeResponseDto(recipeRepository.save(recipe)));
    }

    @Override
    public ResponseDto deleteRecipe(Long recipeId) {
        if (!recipeRepository.existsById(recipeId)) {
            return responseService.validationEvent("Invalid Recipe ID");
        }

        recipeRepository.deleteById(recipeId);
        return responseService.successEvent("Recipe deleted successfully");
    }
}