package com.idan.user.repository;

import com.idan.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
  User findByEmail(String email);
  @Query("Select u from User u where u.email = :email or u.username = :username")
  User findByEmailOrUsername(String email, String username);

}
