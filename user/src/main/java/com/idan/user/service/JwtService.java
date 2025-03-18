package com.idan.user.service;

import com.idan.user.Interface.IJwtRepository;
import com.idan.user.dto.CreateAccountRequest;
import com.idan.user.dto.JwtAuthenticationResponse;
import com.idan.user.dto.LoginRequest;
import com.idan.user.model.Role;
import com.idan.user.model.Token;
import com.idan.user.model.User;
import com.idan.user.model.UserAddress;
import com.idan.user.repository.JwtRepository;
import com.idan.user.repository.TokenRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class JwtService implements IJwtRepository {

  private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
  private final JwtRepository jwtRepository;
  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailService;
  private final TokenRepository tokenRepository;
  private final UserAddressService userAddressService;
  private final PasswordEncoder passwordEncoder;
  private final JwtConfigService jwtConfigService;

  public JwtService(JwtRepository jwtRepository, AuthenticationManager authenticationManager,
      UserDetailsService userDetailService, TokenRepository tokenRepository,
      UserAddressService userAddressService, PasswordEncoder passwordEncoder, JwtConfigService jwtConfigService) {
    this.jwtRepository = jwtRepository;
    this.authenticationManager = authenticationManager;
    this.userDetailService = userDetailService;
    this.tokenRepository = tokenRepository;
    this.userAddressService = userAddressService;
    this.passwordEncoder = passwordEncoder;
    this.jwtConfigService = jwtConfigService;
  }

  @Override
  public JwtAuthenticationResponse createUserAccount(CreateAccountRequest request) {

    UserAddress userAddress = new UserAddress.Builder()
        .houseNumber(request.getHouseNumber())
        .city(request.getCity())
        .zipCode(request.getZipCode())
        .province(request.getProvince())
        .country(request.getCountry())
        .countryCode(request.getCountryCode())
        .build();
    userAddressService.save(userAddress);

     User user = new User.Builder()
        .username(request.getUsername())
        .firstname(request.getFirstname())
        .password(passwordEncoder.encode(request.getPassword()))
        .lastname(request.getLastname())
        .email(request.getEmail())
        .dateOfBirth(java.time.LocalDate.parse(request.getDateOfBirth()))
        .userAddress(userAddress)
        .build();


    if (request.getRole() == null) {
      user.setRole(Role.USER);
    } else {
      user.setRole(request.getRole());
    }
    user = jwtRepository.save(user);

    String jwtToken = jwtConfigService.generateToken(user);

    logger.info("User registered successfully with username: {}", user.getUsername());

    Token token = new Token();
    token.setToken(jwtToken);
    token.setLoggedOut(false);
    token.setUsername(user.getUsername());

    tokenRepository.save(token);
    return new JwtAuthenticationResponse(jwtToken, user.getId());

  }

  public String authenticate(LoginRequest request) {

    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(
            request.getUsername(), request.getPassword()));
    // Generate new token
    User user = jwtRepository.findByUsername(request.getUsername());

    final String jwtToken = jwtConfigService.generateToken(user);

    //gets existing tokens for user
    final List<Token> existingTokensByUsername = tokenRepository.findAllByUsername(
        user.getUsername());

    //sets token to logout
    existingTokensByUsername.forEach(token -> {
      token.setLoggedOut(true);
    });
    tokenRepository.saveAll(existingTokensByUsername);

    Token token = new Token();
    token.setToken(jwtToken);
    token.setLoggedOut(false);
    token.setUsername(user.getUsername());

    //saves token
    tokenRepository.save(token);
    return jwtToken;

  }

  @Override
  public Boolean isTokenValid(String token) {
    try {
      final String username = jwtConfigService.extractUsername(token);
       if (username != null) {
        final UserDetails userDetails = userDetailService.loadUserByUsername(username);

         logger.info("loadUserByUsername: {}", userDetails.getUsername());
        return jwtConfigService.isTokenValid(token, userDetails);
      }
    } catch (Exception e) {
      logger.error("Error while checking if token is valid", e);
    }
    return false;
  }

  @Override
  public String findUserByRole(String username) {
    return jwtRepository.findByUsername(username).getRole().toString();
  }


}
