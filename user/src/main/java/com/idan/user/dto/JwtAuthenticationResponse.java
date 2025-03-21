package com.idan.user.dto;

public class JwtAuthenticationResponse {

  private String token;
  private String userId;

  public JwtAuthenticationResponse(String token, String userId) {
    this.token = token;
    this.userId = userId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
