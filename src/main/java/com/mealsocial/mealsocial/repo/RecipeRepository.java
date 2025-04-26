package com.mealsocial.mealsocial.repo;

import com.mealsocial.mealsocial.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {}