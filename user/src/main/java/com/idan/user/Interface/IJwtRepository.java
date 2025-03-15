package com.idan.user.Interface;

import com.idan.user.dto.CreateAccountRequest;
import com.idan.user.dto.JwtAuthenticationResponse;

public interface IJwtRepository {

  JwtAuthenticationResponse createUserAccount(CreateAccountRequest user);
  Boolean isTokenValid(String token);
  String findUserByRole(String username);
}
