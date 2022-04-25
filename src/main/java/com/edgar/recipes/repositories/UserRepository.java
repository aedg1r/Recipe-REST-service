package com.edgar.recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import com.edgar.recipes.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByEmail(String email);
}
