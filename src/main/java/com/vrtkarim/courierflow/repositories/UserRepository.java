package com.vrtkarim.courierflow.repositories;

import com.vrtkarim.courierflow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    User save(User user);

}
