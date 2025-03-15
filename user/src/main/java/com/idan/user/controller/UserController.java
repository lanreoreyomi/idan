package com.idan.user.controller;

import com.idan.user.dto.CreateAccountRequest;
import com.idan.user.dto.LoginRequest;
import com.idan.user.exceptions.InvalidJwtTokenException;
import com.idan.user.exceptions.UserAlreadyExistsException;
import com.idan.user.exceptions.UserNotFoundException;
import com.idan.user.model.User;

import com.idan.user.dto.VerifyTokenRequest;
import com.idan.user.service.JwtConfigService;
import com.idan.user.service.JwtService;
import com.idan.user.service.UserAddressService;
import com.idan.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/api/user")
public class UserController {

  Logger logger = LoggerFactory.getLogger(UserController.class);

  final UserService userService;
  final UserAddressService userAddressService;
  final JwtService jwtService;
  final JwtConfigService jwtConfigService;

  public UserController(UserService userService, UserAddressService userAddressService,
      JwtService jwtService, JwtConfigService jwtConfigService) {
    this.userService = userService;
    this.userAddressService = userAddressService;
    this.jwtService = jwtService;
    this.jwtConfigService = jwtConfigService;
  }

  @PostMapping("/createaccount")
  public ResponseEntity<String> save(@RequestBody CreateAccountRequest request) {

    if (request == null) {
      logger.error("Request is empty");
      return new ResponseEntity<>("Request is empty", HttpStatus.BAD_REQUEST);
    }

    // Check if email or username exists
    if (userService.findByEmailOrUsername(request.getEmail(), request.getUsername())) {
      User existingUser = userService.findUserByEmail(request.getEmail());
      if (existingUser != null) {
        throw new UserAlreadyExistsException("Email " + request.getEmail() + " already exists");
      } else {
        throw new UserAlreadyExistsException(
            "Username " + request.getUsername() + " already exists");
      }
    }

    try {
       jwtService.createUserAccount(request);
      return new ResponseEntity<>("User Successfully Created", HttpStatus.OK);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      logger.error("Error creating user", e.getMessage());
      return new ResponseEntity<>("Error creating user", HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @PostMapping("/{username}/role")
  public ResponseEntity<String> findUserRole(@PathVariable String username,
      HttpServletRequest request) {

    if (username == null || username.trim().isBlank()) {
      logger.warn("Username is mssing or empty");
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Username is required");
    }
    String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      logger.warn("Authorization header missing or invalid");
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Invalid or missing Authorization header");
    }
    String token = authHeader.substring(7);

      if (!jwtService.isTokenValid(token)) {
        throw new InvalidJwtTokenException("User token not valid");
      }

    final User userByUsername = userService.findUserByUsername(username);
    if (userByUsername==null) {
      throw new UserNotFoundException("User Not Found");
    }

    final String userByRole = jwtService.findUserByRole(username);
    return new ResponseEntity<>(userByRole, HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<String> logIn(@RequestBody LoginRequest loginRequest) {
    logger.info("Log in Request {}", loginRequest.toString());

    if (loginRequest.getUsername() == null || loginRequest.getUsername().trim().isEmpty()
        || loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
      logger.warn("Invalid login request: Username or password missing");
      return new ResponseEntity<String>("Username or password missing", HttpStatus.BAD_REQUEST);
    }

    String token;
    try {
      token = jwtService.authenticate(loginRequest);
      if (token == null && token.isEmpty()) {
        logger.info("Invalid log in request for {}", loginRequest.getUsername());
        return new ResponseEntity<>("Invalid login request", HttpStatus.UNAUTHORIZED);
      }
    } catch (Exception e) {
      logger.warn("Log in failed", e);
      return new ResponseEntity<String>("User log in Failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return ResponseEntity.ok()
        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
        .body(token);
  }

  @PostMapping("/validate-token")
  public ResponseEntity<Boolean> validateToken(@RequestBody VerifyTokenRequest verifyToken) {

    if (verifyToken.token() == null || verifyToken.token().isBlank()) {
      logger.warn("Token is missing or blank");
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token is required");
    }

      if (!jwtService.isTokenValid(verifyToken.token())) {
        logger.warn("Token validation failed");
        return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
      }

      logger.info("Token validated successfully");
      return new ResponseEntity<>(true, HttpStatus.OK);

  }
}
