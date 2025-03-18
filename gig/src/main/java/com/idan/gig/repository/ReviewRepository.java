package com.idan.gig.repository;

import com.idan.gig.model.Comment;
import com.idan.gig.model.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

  List<Review> findReviewsByGigId(String l);
}
