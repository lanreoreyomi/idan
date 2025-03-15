package com.idan.user.repository;

import com.idan.user.model.Token;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

  Token save(Token token);
  Token findByToken(String token);
  Token findByUsername(String username);
  List<Token> findAllByUsername(String username);

}