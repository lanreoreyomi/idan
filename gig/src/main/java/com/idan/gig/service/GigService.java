package com.idan.gig.service;

import com.idan.gig.model.Comment;
import com.idan.gig.model.Gig;
import com.idan.gig.model.Review;
import com.idan.gig.repository.CommentRepository;
import com.idan.gig.repository.GigRepository;
import com.idan.gig.repository.ICommentRepository;
import com.idan.gig.repository.IGigRepository;
import com.idan.gig.repository.IReviewRepository;
import com.idan.gig.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GigService implements IGigRepository, ICommentRepository, IReviewRepository {

  private final GigRepository gigRepository;
  private final CommentRepository commentRepository;
  private final ReviewRepository reviewRepository;

  public GigService(GigRepository gigRepository, CommentRepository commentRepository,
      ReviewRepository reviewRepository) {
    this.gigRepository = gigRepository;
    this.commentRepository = commentRepository;
    this.reviewRepository = reviewRepository;
  }

  @Override
  public void save(Gig gig) {
    gigRepository.save(gig);

  }

  @Override
  public void removeGig(String id) {
    gigRepository.deleteById(id);

  }

  @Override
  public Gig getGig(String gigId) {
    return gigRepository.findGigById(gigId).orElse(null);
  }

  @Override
  public List<Gig> getGigs() {
    return gigRepository.findAll();
  }

  @Override
  public List<Gig> getGigsByUsername(String username) {
    return gigRepository.findByUsername(username);
  }

  @Override
  public void addComment(Comment comment) {
    commentRepository.save(comment);
  }

  @Override
  public void deleteComment(String commentId) {
  commentRepository.deleteById(commentId);
  }

  @Override
  public List<Comment> getCommentsByGigId(String gigId) {
    return commentRepository.findByGigId(gigId);
  }

  @Override
  public Comment getComment(String commentId) {
    return commentRepository.findById(commentId).orElse(null);
  }

  @Override
  public void addReview(Review review) {
    reviewRepository.save(review);

  }

  @Override
  public void deleteReview(String commentId) {
    reviewRepository.deleteById(commentId);

  }

  @Override
  public List<Review> getReviewsByGigId(String gigId) {
    return reviewRepository.findReviewsByGigId(gigId);
  }
}
