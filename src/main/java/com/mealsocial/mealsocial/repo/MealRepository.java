package com.mealsocial.mealsocial.repo;

import com.mealsocial.mealsocial.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {}