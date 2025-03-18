package com.idan.gig.repository;

import com.idan.gig.model.Comment;
import java.util.List;

public interface ICommentRepository {

  void addComment(Comment comment);
  void deleteComment(String commentId);
  List<Comment> getCommentsByGigId(String gigId);
  Comment getComment(String commentId);

}
