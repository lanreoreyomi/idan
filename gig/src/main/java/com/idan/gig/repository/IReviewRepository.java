package com.idan.gig.repository;

import com.idan.gig.model.Comment;
import com.idan.gig.model.Review;
import java.util.List;

public interface IReviewRepository {
  void addReview(Review review);
  void deleteReview(String reviewId);
  List<Review> getReviewsByGigId(String reviewId);

}
