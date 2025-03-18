package com.idan.user.service;

import com.idan.user.Interface.IUserRepository;
import com.idan.user.model.User;
import com.idan.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.sql.SQLOutput;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements IUserRepository {

  final UserRepository userRepository;

  public UserService(UserRepository userRepository){
    this.userRepository = userRepository;
  }


  @Override
  public User findUserByUsername(String username) {
    return userRepository.findByUsername((username));
  }

  @Override
  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public boolean findByEmailOrUsername(String email, String username) {

    System.out.println("in service: " + userRepository.findByEmailOrUsername(email, username));
    return userRepository.findByEmailOrUsername(email, username)!=null;
  }
}