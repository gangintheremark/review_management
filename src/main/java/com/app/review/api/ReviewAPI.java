package com.app.review.api;

import com.app.review.api.request.CreateReviewRequest;
import com.app.review.service.ReviewService;
import com.app.review.service.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReviewAPI {
    private final ReviewService reviewService;

    @PostMapping("/review")
    public void createReview(@RequestBody CreateReviewRequest request) {
        reviewService.createReview(request.getRestaurantId(), request.getContent(), request.getScore());
    }

    @DeleteMapping("/review/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

    @GetMapping("/restaurant/{restaurantId}/reviews")
    public ReviewDTO getRestaurantReviews(@PathVariable("restaurantId") Long restaurantId, @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return reviewService.getRestaurantReview(restaurantId, PageRequest.of(offset/limit, limit));
    }
}
