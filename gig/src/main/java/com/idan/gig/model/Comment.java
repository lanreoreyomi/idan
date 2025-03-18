package com.idan.gig.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {

  @Id
  @Column(nullable = false, unique = true)
  @jakarta.persistence.GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String comment;
  private String username;
  @ManyToOne
  @JoinColumn(name = "gig_id")
  private Gig gig;


  Comment(Builder builder) {
    this.id = builder.id;
    this.comment = builder.comment;
    this.username = builder.username;
    this.gig = builder.gig;

  }

  public Comment() {
  }

  public static class Builder {

    private String id;
    private String comment;
    private String username;
    private Gig gig;


    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder comment(String comment) {
      this.comment = comment;
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


    public Comment build() {

      return new Comment(this);

    }

  }

  @Override
  public String toString() {
    return "Comment{" +
        "id='" + id + '\'' +
        ", comment='" + comment + '\'' +
        ", username='" + username + '\'' +
        ", gig=" + gig +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Comment comment = (Comment) o;
    return id.equals(comment.id) && this.comment.equals(comment.comment) && username.equals(
        comment.username) && gig.equals(comment.gig);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + comment.hashCode();
    result = 31 * result + username.hashCode();
    result = 31 * result + gig.hashCode();
    return result;
  }

  public String getId() {
    return id;
  }

  public String getComment() {
    return comment;
  }

  public String getUsername() {
    return username;
  }


  public Gig getGig() {
    return gig;
  }

  public void setGig(Gig gig) {
    this.gig = gig;
  }
}