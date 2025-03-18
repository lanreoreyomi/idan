package com.idan.gig.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

  @Id
  @Column(nullable = false, unique = true)
  @jakarta.persistence.GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private int rating;
  private String review;
  private String username;
  @ManyToOne
  @JoinColumn(name = "gig_id")
  private Gig gig;

  public static class Builder{
    private String id;
    private int rating;
    private String review;
    private String username;
    private Gig gig;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder rating(int rating) {
      this.rating = rating;
      return this;
    }

    public Builder review(String review) {
      this.review = review;
      return this;
    }

    public Builder username(String username) {
      this.username = username;
      return this;
    }

    public Builder gig(Gig gig) {
      this.gig = gig;
      return this;
    }

    public Review build() {
      return new Review(this);
    }
  }

    public Review() {
    }

  public Review(Builder builder) {
    this.id = builder.id;
    this.rating = builder.rating;
    this.review = builder.review;
    this.username = builder.username;
    this.gig = builder.gig;
  }

  public void setId(String id) {
    this.id = id;
  }
  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  @Override
  public String toString() {
    return "Review{" +
        "id='" + id + '\'' +
        ", review='" + review + '\'' +
        ", username='" + username + '\'' +
        ", gig=" + gig +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Review review = (Review) o;
    return id.equals(review.id) && this.review.equals(review.review) && username.equals(
        review.username)
        && gig.equals(review.gig);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + review.hashCode();
    result = 31 * result + username.hashCode();
    result = 31 * result + gig.hashCode();
    return result;
  }

  public String getId() {
    return id;
  }


  public String getReview() {
    return review;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public Gig getGig() {
    return gig;
  }

  public void setGig(Gig gig) {
    this.gig = gig;
  }
}