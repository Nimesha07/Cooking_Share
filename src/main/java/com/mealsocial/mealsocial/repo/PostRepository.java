package com.mealsocial.mealsocial.repo;

import com.mealsocial.mealsocial.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}
