package com.idan.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "user_address")
public class UserAddress {

  @Id
  @Column(nullable = false, unique = true)
  @jakarta.persistence.GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String houseNumber;
  private String city;
  private String zipCode;
  private String province;
  private String country;
  private String countryCode;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  private UserAddress(Builder builder) {
  this.id = builder.userAddress.id;
  this.houseNumber = builder.userAddress.houseNumber;
  this.city = builder.userAddress.city;
  this.zipCode = builder.userAddress.zipCode;
  this.province = builder.userAddress.province;
  this.country = builder.userAddress.country;
  this.countryCode = builder.userAddress.countryCode;
}

public UserAddress() {}
public static class Builder{

  private UserAddress userAddress;

  public Builder(){
    userAddress = new UserAddress();
  }

  public Builder id(String id){
    userAddress.id = id;
    return this;

  }
  public Builder houseNumber(String houseNumber){
    userAddress.houseNumber = houseNumber;
    return this;
  }
  public Builder city(String city){
    userAddress.city = city;
    return this;
  }
  public Builder zipCode(String zipCode){
    userAddress.zipCode = zipCode;
    return this;
  }
  public Builder province(String province){
    userAddress.province = province;
    return this;
  }
  public Builder country(String country){
    userAddress.country = country;
    return this;
  }
  public Builder countryCode(String countryCode){
    userAddress.countryCode = countryCode;
    return this;
  }
  public UserAddress build(){
    return userAddress;
  }
}

public static Builder builder(){
  return new Builder();

  }

}

