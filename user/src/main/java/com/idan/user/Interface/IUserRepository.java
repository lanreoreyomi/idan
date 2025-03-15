package com.idan.user.Interface;


import com.idan.user.model.User;

public interface IUserRepository {

  User findUserByUsername(String username);
  User findUserByEmail(String email);
  boolean findByEmailOrUsername(String email, String username);
}

