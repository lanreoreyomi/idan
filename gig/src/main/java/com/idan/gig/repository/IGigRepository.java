package com.idan.gig.repository;

import com.idan.gig.model.Gig;
import java.util.List;

public interface IGigRepository {

  void save(Gig gig);
  void removeGig(String id);
  Gig getGig(String gigId);
  List<Gig> getGigs();
  List<Gig> getGigsByUsername(String username);

}
