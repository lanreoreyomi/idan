package com.idan.gig.repository;

import com.idan.gig.model.Gig;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GigRepository extends JpaRepository<Gig, String> {

  List<Gig> findByUsername(String username);
  Optional<Gig> findGigById(String id);
}
