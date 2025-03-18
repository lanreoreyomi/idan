package com.idan.gig.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Gig {
  @Id
  @Column(nullable = false, unique = true)
  @jakarta.persistence.GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(nullable = false)
  private String title;
  private String description;
  private String gigImage;
  private String fileContentType;
  private String username;

  @Column(nullable = false)
  private LocalDate creationDate;
  @Column(nullable = false)
  private String category;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "gig")
  List<Review> reviews;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "gig")
  List<Comment> comments;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "gig")
  private List<GigImage> images;


  public LocalDate getCreationDate() {
    return creationDate;
  }


  public String getCategory() {
    return category;
  }


  public List<GigImage> getImages() {
    return images;
  }

  public String getUsername() {
    return username;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getGigImage() {
    return gigImage;
  }

  public String getFileContentType() {
    return fileContentType;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public String getId() {
    return id;
  }

  public Gig(){}

  public Gig(Builder builder){
    this.id = builder.gig.id;
    this.username = builder.gig.username;
    this.title = builder.gig.title;
    this.description = builder.gig.description;
    this.gigImage = builder.gig.gigImage;
    this.fileContentType = builder.gig.fileContentType;
    this.reviews = builder.gig.reviews;
    this.comments = builder.gig.comments;
    this.creationDate = builder.gig.creationDate;
    this.category = builder.gig.category;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Gig gig = (Gig) o;
    return id.equals(gig.id) && username.equals(gig.username) && Objects.equals(title,
        gig.title) && Objects.equals(description, gig.description)
        && Objects.equals(gigImage, gig.gigImage) && Objects.equals(
        fileContentType, gig.fileContentType) && Objects.equals(reviews, gig.reviews)
        && Objects.equals(comments, gig.comments);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + username.hashCode();
    result = 31 * result + Objects.hashCode(title);
    result = 31 * result + Objects.hashCode(description);
    result = 31 * result + Objects.hashCode(gigImage);
    result = 31 * result + Objects.hashCode(fileContentType);
    result = 31 * result + Objects.hashCode(reviews);
    result = 31 * result + Objects.hashCode(comments);
    return result;
  }

  @Override
  public String toString() {
    return "Gig{" +
        "id='" + id + '\'' +
        ", username='" + username + '\'' +
        ", title='" + title + '\'' +
        ", description='" + description + '\'' +
        ", gigImage='" + gigImage + '\'' +
        ", fileContentType='" + fileContentType + '\'' +
        ", reviews=" + reviews +
        ", comments=" + comments +
        '}';
  }


  public static class Builder{
    private Gig gig;

    public Builder(){
      gig = new Gig();
    }

    public Builder id(String id){
      gig.id = id;
      return this;
    }

    public Builder title(String title){
      gig.title = title;
      return this;
    }

    public Builder description(String description){
      gig.description = description;
      return this;
    }

    public Builder gigImage(String gigImage){
      gig.gigImage = gigImage;
      return this;
    }


    public Builder fileContentType(String fileContentType){
      gig.fileContentType = fileContentType;
      return this;
    }
    public Builder username(String username){
      gig.username = username;
      return this;
    }
    public Builder reviews(List<Review> reviews){
      gig.reviews = reviews;
      return this;
    }
    public Builder comments(List<Comment> comments){
      gig.comments = comments;
      return this;
    }
    public Builder creationDate(LocalDate creationDate){
      gig.creationDate = creationDate;
      return this;
    }
    public Builder category(String category){
      gig.category = category;
      return this;
    }
    public Builder images(List<GigImage> images){
      gig.images = images;
      return this;
    }



    public Gig build(){
      return gig;
    }

  }
}
