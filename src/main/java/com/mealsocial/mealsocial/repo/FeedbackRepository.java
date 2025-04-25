package com.mealsocial.mealsocial.repo;

import com.mealsocial.mealsocial.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {}