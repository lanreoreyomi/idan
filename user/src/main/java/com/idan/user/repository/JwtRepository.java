package com.idan.user.repository;

import com.idan.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtRepository extends JpaRepository<User, String> {

  User findByUsername(String username);

  User findByEmail(String email);

  User save(User user);

  void deleteUserByUsername(String username);
}
