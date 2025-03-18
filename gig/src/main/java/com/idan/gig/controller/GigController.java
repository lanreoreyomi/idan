package com.idan.gig.controller;

import com.idan.gig.dto.ApiResponse;
import com.idan.gig.dto.DataResponse;
import com.idan.gig.dto.GigRequest;
import com.idan.gig.exceptions.GigNotFoundException;
import com.idan.gig.model.Gig;
import com.idan.gig.service.GigService;
import com.idan.gig.service.VerificationService;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.crypto.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/gig")
public class GigController {

  Logger logger = LoggerFactory.getLogger(GigController.class);

  private final GigService gigService;
  private final VerificationService verificationService;

  public GigController(GigService gigService, VerificationService verificationService) {
    this.gigService = gigService;
    this.verificationService = verificationService;
  }

  @PostMapping("/{username}/add")
  public ResponseEntity<ApiResponse> addGig(HttpServletRequest request,
      @PathVariable String username,
      @RequestBody GigRequest gigRequest) {

    if (request == null || gigRequest == null) {
      logger.error("Request is empty");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(new ApiResponse("Request is empty"));
    }

    if (!isValidToken(request, username)) {
      logger.info("Invalid log in request for {}", username);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(new ApiResponse("Invalid login request"));
    }

    Gig gig = new Gig.Builder()
        .title(gigRequest.getTitle())
        .description(gigRequest.getDescription())
        .gigImage(gigRequest.getGigImage())
        .fileContentType(gigRequest.getFileContentType())
        .username(username)
        .category(gigRequest.getCategory())
        .creationDate(LocalDate.now())
        .build();

    gigService.save(gig);
    logger.info("Gig added successfully for user: {}, gig title: {}", username, gig.getTitle());

    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Gig added successfully"));

  }

  @GetMapping("/all")
  public ResponseEntity<List<DataResponse>> getAllGigs(HttpServletRequest request) {

    if (request == null) {
      logger.error("Request is empty");
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    if (!isValidToken(request, null)) {
      logger.info("Invalid log in request");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    List<Gig> gigs = gigService.getGigs();

    List<DataResponse> dataResponseList = gigs.stream()
        .map(gig -> new DataResponse(
            gig.getTitle(),
            gig.getDescription(),
            gig.getGigImage(),
            gig.getFileContentType(),
            gig.getUsername(),
            gig.getReviews(),
            gig.getComments()

        ))
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(dataResponseList);
  }

  @GetMapping("/{username}/all")
  public ResponseEntity<List<DataResponse>> getAllGigs(HttpServletRequest request,
      @PathVariable String username) {

    if (request == null) {
      logger.error("Request is empty");
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    if (!isValidToken(request, null)) {
      logger.info("Invalid log in request");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    List<Gig> gigs = gigService.getGigsByUsername(username);

    List<DataResponse> gigDto = gigs.stream()
        .map(gig -> new DataResponse(
            gig.getTitle(),
            gig.getDescription(),
            gig.getGigImage(),
            gig.getFileContentType(),
            gig.getUsername(),
            gig.getReviews(),
            gig.getComments()

        ))
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(gigDto);
  }

  @GetMapping("/{gigId}")
  public ResponseEntity<DataResponse> getGigById(HttpServletRequest request,
      @PathVariable String gigId) {

    if (request == null) {
      logger.error("Request is empty");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(null);
    }

    if (!isValidToken(request, null)) {
      logger.info("Invalid log in request");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    try {
      Gig gig = gigService.getGig(gigId);

      if (gig == null) {
        logger.warn("Gig not found with id: {}", gigId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }

      return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(gig));

    } catch (GigNotFoundException e) {
      logger.warn("Gig not found with id: {}", gigId);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (Exception e) {
      logger.error("Error retrieving gig with id: {}", gigId, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

  }

  private boolean isValidToken(HttpServletRequest request, String username) {
    try {
      ResponseEntity<Boolean> tokenResponse = verificationService.verifyToken(request, username);
      return tokenResponse != null && tokenResponse.getStatusCode() == HttpStatus.OK;
    } catch (Exception e) {
      logger.warn("Login failed for user: {}", username, e);
      return false;
    }
  }
}
