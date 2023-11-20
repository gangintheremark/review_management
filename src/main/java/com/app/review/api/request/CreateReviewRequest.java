package com.app.review.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CreateReviewRequest {
    private final Long restaurantId;
    private final String content;
    private final Double score;
}
