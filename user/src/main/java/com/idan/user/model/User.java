package com.idan.user.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {

  @Id
  @Column(nullable = false, unique = true)
  @jakarta.persistence.GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private  String username;
  private String password;
  private String firstname;
  private String lastname;
  private String email;
  private LocalDate dateOfBirth;

  @ManyToOne
  @JoinColumn(name = "user_address_id")
  private UserAddress userAddress;


  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  Role role;

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getId() {
    return id;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }
  @Override
  public boolean isEnabled() {
    return true;
  }
  @Override
  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public UserAddress getUserAddress() {
    return userAddress;
  }


  public User(){
  }

  private User(Builder builder){
  this.id = builder.user.id;
  this.username = builder.user.username;
  this.firstname = builder.user.firstname;
  this.lastname = builder.user.lastname;
  this.email = builder.user.email;
  this.dateOfBirth = builder.user.dateOfBirth;
  this.userAddress = builder.user.userAddress;
  this.role = builder.user.role;
  this.password = builder.user.password;

}
  // Builder class
  public static class Builder {

    private User user;

    public Builder() {
      user = new User();
    }

    public Builder id(String id) {
      user.id = id;
      return this;
    }

    public Builder username(String username) {
      user.username = username;
      return this;
    }

    public Builder firstname(String firstname) {
      user.firstname = firstname;
      return this;
    }
    public Builder lastname(String lastname) {
      user.lastname = lastname;
      return this;
    }

    public Builder email(String email) {
      user.email = email;
      return this;
    }

    public Builder dateOfBirth(LocalDate dateOfBirth) {
      user.dateOfBirth = dateOfBirth;
      return this;
    }
    public Builder userAddress(UserAddress userAddress) {
      user.userAddress = userAddress;
      return this;
    }

    public Builder role(Role role){
      user.role = role;
      return this;
    }
  public Builder password(String password) {
        user.password = password;
        return this;
  }
    public User build() {
       return user;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    User user = (User) o;
    return id.equals(user.id) && username.equals(user.username) && Objects.equals(firstname,
        user.firstname) && Objects.equals(lastname, user.lastname)
        && Objects.equals(email, user.email) && Objects.equals(dateOfBirth,
        user.dateOfBirth) && Objects.equals(userAddress, user.userAddress);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + username.hashCode();
    result = 31 * result + Objects.hashCode(firstname);
    result = 31 * result + Objects.hashCode(lastname);
    result = 31 * result + Objects.hashCode(email);
    result = 31 * result + Objects.hashCode(dateOfBirth);
    result = 31 * result + Objects.hashCode(userAddress);
    return result;
  }


  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", username='" + username + '\'' +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", email='" + email + '\'' +
        ", dateOfBirth=" + dateOfBirth +
        ", userAddress=" + userAddress +
        ", role=" + role +
        '}';
  }

}
