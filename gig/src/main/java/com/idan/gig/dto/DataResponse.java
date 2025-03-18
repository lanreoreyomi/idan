package com.idan.gig.dto;

import com.idan.gig.model.Comment;
import com.idan.gig.model.Gig;
import com.idan.gig.model.Review;
import java.util.List;

public class DataResponse  {

  private String response;
  private String title;
  private String description;
  private String gigImage;
  private String fileContentType;
  private String username;
  private List<Review> reviews;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getGigImage() {
    return gigImage;
  }

  public void setGigImage(String gigImage) {
    this.gigImage = gigImage;
  }

  public String getFileContentType() {
    return fileContentType;
  }

  public void setFileContentType(String fileContentType) {
    this.fileContentType = fileContentType;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  private List<Comment> comments;

  public DataResponse(Gig gig) {
    this.title = gig.getTitle();
    this.description = gig.getDescription();
    this.gigImage = gig.getGigImage();
    this.fileContentType = gig.getFileContentType();
    this.username = gig.getUsername();
    this.reviews = gig.getReviews();
    this.comments = gig.getComments();

  }
  public DataResponse(String response) {
    this.response = response;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public DataResponse(String title, String description, String gigImage, String fileContentType,
      String username, List<Review> reviews, List<Comment> comments) {
    this.title = title;
    this.description = description;
    this.gigImage = gigImage;
    this.fileContentType = fileContentType;
    this.username = username;
    this.reviews = reviews;
    this.comments = comments;
  }
}
