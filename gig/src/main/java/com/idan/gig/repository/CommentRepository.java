package com.idan.gig.repository;

import com.idan.gig.model.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

  List<Comment> findByGigId(String id);
}
