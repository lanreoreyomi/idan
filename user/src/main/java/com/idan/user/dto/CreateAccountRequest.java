package com.idan.user.dto;

import com.idan.user.model.Role;
import java.util.Objects;

public class CreateAccountRequest {
  private String username;
  private String password;
  private String firstname;
  private String lastname;
  private String email;
  private String dateOfBirth;
  private String houseNumber;
  private String city;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  private String zipCode;
  private String province;
  private String country;
  private String countryCode;
  private Role role;

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getHouseNumber() {
    return houseNumber;
  }

  public void setHouseNumber(String houseNumber) {
    this.houseNumber = houseNumber;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CreateAccountRequest that = (CreateAccountRequest) o;
    return username.equals(that.username) && password.equals(that.password) && firstname.equals(
        that.firstname) && lastname.equals(that.lastname) && email.equals(that.email)
        && dateOfBirth.equals(that.dateOfBirth) && houseNumber.equals(that.houseNumber)
        && city.equals(that.city) && Objects.equals(zipCode, that.zipCode)
        && Objects.equals(province, that.province) && country.equals(that.country)
        && countryCode.equals(that.countryCode) && role == that.role;
  }

  @Override
  public int hashCode() {
    int result = username.hashCode();
    result = 31 * result + password.hashCode();
    result = 31 * result + firstname.hashCode();
    result = 31 * result + lastname.hashCode();
    result = 31 * result + email.hashCode();
    result = 31 * result + dateOfBirth.hashCode();
    result = 31 * result + houseNumber.hashCode();
    result = 31 * result + city.hashCode();
    result = 31 * result + Objects.hashCode(zipCode);
    result = 31 * result + Objects.hashCode(province);
    result = 31 * result + country.hashCode();
    result = 31 * result + countryCode.hashCode();
    result = 31 * result + role.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "CreateAccountRequest{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", email='" + email + '\'' +
        ", dateOfBirth='" + dateOfBirth + '\'' +
        ", houseNumber='" + houseNumber + '\'' +
        ", city='" + city + '\'' +
        ", zipCode='" + zipCode + '\'' +
        ", province='" + province + '\'' +
        ", country='" + country + '\'' +
        ", countryCode='" + countryCode + '\'' +
        '}';
  }
}
