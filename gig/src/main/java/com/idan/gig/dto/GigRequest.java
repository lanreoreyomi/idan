package com.idan.gig.dto;

import java.time.LocalDate;
import java.util.Objects;

public class GigRequest {
  private String title;
  private String description;
  private String gigImage;
  private String fileContentType;
  private LocalDate creationDate;
  private String category;

  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public GigRequest() {
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    GigRequest that = (GigRequest) o;
    return title.equals(that.title) && Objects.equals(description, that.description)
        && Objects.equals(gigImage, that.gigImage) && Objects.equals(
        fileContentType, that.fileContentType);
  }

  @Override
  public int hashCode() {
    int result = title.hashCode();
    result = 31 * result + Objects.hashCode(description);
    result = 31 * result + Objects.hashCode(gigImage);
    result = 31 * result + Objects.hashCode(fileContentType);
    return result;
  }

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
}
