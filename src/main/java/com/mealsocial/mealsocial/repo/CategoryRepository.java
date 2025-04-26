package com.mealsocial.mealsocial.repo;

import com.mealsocial.mealsocial.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
