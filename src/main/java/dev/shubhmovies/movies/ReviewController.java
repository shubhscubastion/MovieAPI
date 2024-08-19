package dev.shubhmovies.movies;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @PostMapping()
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
        String reviewBody = payload.get("reviewBody");
        String imdbId = payload.get("imdbId");
        Review createdReview = service.createReview(reviewBody, imdbId);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = service.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
