package com.app.review.service;

import com.app.review.model.ReviewEntity;
import com.app.review.repository.RestaurantRepository;
import com.app.review.repository.ReviewRepository;
import com.app.review.service.dto.ReviewDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void createReview(Long restaurantId, String content, Double score) {

        restaurantRepository.findById(restaurantId).orElseThrow(); // 없는 맛집이면 에러

        ReviewEntity review = ReviewEntity.builder()
                .restaurantId(restaurantId)
                .content(content)
                .score(score)
                .createdAt(ZonedDateTime.now())
                .build();

        reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        ReviewEntity review = reviewRepository.findById(reviewId).orElseThrow();

        reviewRepository.delete(review);
    }

    public ReviewDTO getRestaurantReview(Long restaurantId, Pageable page) {
        Double avgScore = reviewRepository.getAvgScoreByRestaurantId(restaurantId);
        Slice<ReviewEntity> reviews = reviewRepository.findSliceByRestaurantId(restaurantId, page);

        return ReviewDTO.builder()
                .avgScore(avgScore)
                .reviews(reviews.getContent())
                .page(
                        ReviewDTO.ReviewDTOPage.builder()
                                .offset(page.getPageNumber() * page.getPageSize())
                                .limit(page.getPageSize())
                                .build()
                )
                .build();
    }
}
