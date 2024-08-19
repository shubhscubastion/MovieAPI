package dev.shubhmovies.movies;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {
        Review review = new Review(reviewBody, LocalDateTime.now(), LocalDateTime.now());
        // Review savedReview = repository.save(review);
        Review savedReview = repository.save(review);

        mongoTemplate.update(Movie.class)
            .matching(Criteria.where("imdbId").is(imdbId))
            .apply(new Update().push("reviewIds").value(savedReview.getId()))
            .first();

        return savedReview;
    }

    public List<Review> getAllReviews() {
        List<Review> reviews = repository.findAll();
        return reviews;
    }
}

