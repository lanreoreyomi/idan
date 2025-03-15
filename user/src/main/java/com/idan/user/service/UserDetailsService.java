package com.idan.user.service;

import com.idan.user.repository.JwtRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements
    org.springframework.security.core.userdetails.UserDetailsService {

  private final JwtRepository jwtRepository;

  public UserDetailsService(JwtRepository jwtRepository) {
    this.jwtRepository = jwtRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return jwtRepository.findByUsername(username);
  }
}
