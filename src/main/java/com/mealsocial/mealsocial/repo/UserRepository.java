package com.mealsocial.mealsocial.repo;

import com.mealsocial.mealsocial.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
